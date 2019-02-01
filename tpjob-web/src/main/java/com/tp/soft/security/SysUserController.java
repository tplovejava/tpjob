package com.tp.soft.security;

import com.tp.soft.security.entity.SysUser;
import com.tp.soft.security.service.SysUserSvc;
import com.tp.soft.util.redis.RedisService;
import com.tp.soft.util.web.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SysUserController {

    @Resource
    private SysUserSvc sysUserSvc;

    @Resource
    private RedisService redisService;

    @GetMapping("queryList")
    public ApiResponse<List<SysUser>> queryList(){
        redisService.set("zs", "12345");
        return ApiResponse.success(sysUserSvc.findAllUser());
    }
}
