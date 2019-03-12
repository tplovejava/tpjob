package com.tp.soft.support;

/**
 * @Author: taop
 * @Date: 2019/3/12 下午11:37
 * @Version 1.0
 */
public interface RedisService {

    boolean keyLock(final String key, final long keepMin);
}
