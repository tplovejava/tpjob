package com.tp.soft.util.redis.impl;

import com.tp.soft.util.redis.RedisService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public boolean set(final String key, final String value) {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
                redisConnection.set(key.getBytes(), value.getBytes());
                return true;
            }
        });
        return true;
    }
}
