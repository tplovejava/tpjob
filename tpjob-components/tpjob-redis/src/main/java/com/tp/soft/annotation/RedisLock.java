package com.tp.soft.annotation;


import java.lang.annotation.*;

/**
 * @Author: taop
 * @Date: 2019/3/12 下午9:30
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface RedisLock {
    String lockName() default ""; // 锁名
    int retryTimes() default 0; // 重试次数
    long retryWait() default 200; // 重试等待时间,单位 : ms
    int keeyMinTime() default 1; //锁自动失效时间 1秒
}
