package com.tp.soft.aspect;

import com.alibaba.fastjson.JSONObject;
import com.tp.soft.annotation.RedisLock;
import com.tp.soft.support.RedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: taop
 * @Date: 2019/3/12 下午11:07
 * @Version 1.0
 */
@Aspect
@Component
public class RedisLockProcessor {

    private static final Logger log = LoggerFactory.getLogger(RedisLockProcessor.class);

    private static final String LOCK_NAME = "lockName";
    private static final String RETRY_TIMES = "retryTimes";
    private static final String RETRY_WAIT = "retryWait";
    private static final String KEEP_MIN_TIME = "keepMinTime";

    @Resource
    private RedisService redisService;

    @Pointcut("@annotation(com.tp.soft.annotation.RedisLock)")
    public void redisLockAspect() {
    }

    @Around("redisLockAspect()")
    public Object lockAroundAction(ProceedingJoinPoint pjp) throws Throwable {
        Method method = returnMethod(pjp);

        Map<String, Object> annotationArgs = this.getAnnotationArgs(pjp);
        String lockPrefix = (String) annotationArgs.get(LOCK_NAME);
        Assert.notNull(lockPrefix, "分布式,锁名不能为空");

        Integer retryTimes = (Integer) annotationArgs.get(RETRY_TIMES);
        Long retryWait = (Long) annotationArgs.get(RETRY_WAIT);
        Integer keepMinTime = (Integer) annotationArgs.get(KEEP_MIN_TIME);
        String keyName = parseKey(lockPrefix, method, pjp.getArgs());
        JSONObject redisObj = new JSONObject();
        redisObj.put("keyName",keyName);
        redisObj.put("keepMinTime",keepMinTime);
        // 获取redis锁,防止死锁
        if(redisService.keyLock(keyName, keepMinTime)){
            //执行主程序
            return pjp.proceed();
        }else{
            if(retryTimes <= 0){
                log.info(String.format("{%s}已经被锁, 不重试", keyName));
                throw new RuntimeException(String.format("{%s}已经被锁, 不重试", keyName));
            }

            int failCount = 1;
            while (failCount <= retryTimes) {
                // 等待指定时间ms
                try {
                    Thread.sleep(retryWait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("200".equals(redisService.keyLock(keyName, keepMinTime))) {
                    // 执行主逻辑
                    return  pjp.proceed();
                } else {
                    log.info(String.format("{%s}已经被锁, 正在重试[ %s/%s ],重试间隔{%s}毫秒", keyName, failCount, retryTimes, retryWait));
                    failCount++;
                }
            }

            throw new RuntimeException("系统繁忙, 请稍等再试");

        }


    }


    /**
     * 获取锁参数
     *
     * @param proceeding
     * @return
     */
    private Map<String, Object> getAnnotationArgs(ProceedingJoinPoint proceeding) {
        Class target = proceeding.getTarget().getClass();
        Method[] methods = target.getMethods();
        String methodName = proceeding.getSignature().getName();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Map<String, Object> result = new HashMap<String, Object>();
                RedisLock redisLock = method.getAnnotation(RedisLock.class);
                result.put(LOCK_NAME, redisLock.lockName());
                result.put(RETRY_TIMES, redisLock.retryTimes());
                result.put(RETRY_WAIT, redisLock.retryWait());
                result.put(KEEP_MIN_TIME, redisLock.keeyMinTime());
                return result;
            }
        }
        return null;
    }

    private Method returnMethod(ProceedingJoinPoint pjp)
            throws NoSuchMethodException {
        Signature signature = pjp.getSignature();
        Class<? extends Object> cls = pjp.getTarget().getClass();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Method method = cls.getDeclaredMethod(signature.getName(),
                targetMethod.getParameterTypes());
        return method;
    }

    /**
     * 获取缓存的key key 定义在注解上，支持SPEL表达式
     *
     * @param
     * @return
     */
    private String parseKey(String key, Method method, Object[] args) {

        // 获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        // 使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        // SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }
}
