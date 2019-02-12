package com.tp.soft.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SysUserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("queryList")
    public String queryList(){
        return restTemplate.getForObject("http://TPJOB-SECURITY/queryList", String.class);
    }
}
