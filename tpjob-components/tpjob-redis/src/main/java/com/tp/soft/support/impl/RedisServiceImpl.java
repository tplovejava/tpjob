package com.tp.soft.support.impl;

import com.tp.soft.support.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: taop
 * @Date: 2019/3/12 下午11:38
 * @Version 1.0
 */
@Service
public class RedisServiceImpl implements RedisService {


    @Autowired
    @Qualifier("customStringTemplate")
    private StringRedisTemplate stingRedisTemplate;


    @Autowired
    @Qualifier("customRedisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public boolean keyLock(String key, long keepMin) {
        boolean execute = (boolean) redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    try{
                        Long incr = connection.incr(key.getBytes());
                        if(incr == 1){
                            connection.setEx(key.getBytes(), keepMin, incr.toString().getBytes());
                            return true;
                        }else{
                            Long ttl = connection.ttl(key.getBytes());
                            if(ttl == -1){
                                //设置失败,重新设置过期时间
                                connection.setEx(key.getBytes(), keepMin, incr.toString().getBytes());
                                return true;
                            }
                        }
                    }catch (Exception e) {
                        connection.del(key.getBytes());
                        return false;
                    }
                    return false;
                }
            });

        return execute;

    }
}
