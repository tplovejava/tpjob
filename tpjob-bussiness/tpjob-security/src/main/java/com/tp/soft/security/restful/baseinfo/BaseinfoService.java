package com.tp.soft.security.restful.baseinfo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: taop
 * @Date: 2019/3/10 下午4:17
 * @Version 1.0
 */
@FeignClient(value = "TPJOB-BASEINFO",fallback = BaseinfoServiceFallback.class)
public interface BaseinfoService {
    @PostMapping("/baseinfo/ta/add")
    String add();
}
