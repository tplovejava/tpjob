package com.tp.soft.baseinfo.controller;

import com.tp.soft.baseinfo.service.TaService;
import com.tp.soft.util.web.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: taop
 * @Date: 2019/3/10 下午3:54
 * @Version 1.0
 */
@RestController
@Api("基础服务")
@RequestMapping("baseinfo/ta")
public class TaController {
    @Resource
    private TaService taService;

    @PostMapping("/add")
    public ApiResponse add(){
        return taService.add();
    }

}
