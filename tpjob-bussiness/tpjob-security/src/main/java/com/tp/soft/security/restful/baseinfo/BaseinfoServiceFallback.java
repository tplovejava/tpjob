package com.tp.soft.security.restful.baseinfo;

/**
 * @Author: taop
 * @Date: 2019/3/10 下午4:20
 * @Version 1.0
 */
public class BaseinfoServiceFallback implements BaseinfoService{
    @Override
    public String add() {
        return "error";
    }
}
