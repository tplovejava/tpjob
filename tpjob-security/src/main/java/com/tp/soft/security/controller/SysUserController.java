package com.tp.soft.security.controller;

import com.github.pagehelper.Page;
import com.tp.soft.security.entity.SysUser;
import com.tp.soft.security.service.SysUserSvc;
import com.tp.soft.util.model.PageResult;
import com.tp.soft.util.web.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SysUserController {

    @Resource
    private SysUserSvc sysUserSvc;

    @GetMapping("queryList")
    public ApiResponse<List<SysUser>> queryList(){
        //System.out.println(keyname);
        return ApiResponse.success(sysUserSvc.findAllUser());
    }

    @PostMapping("selectPageSysUserByQuery")
    public ApiResponse<PageResult<SysUser>> selectPageSysUserByQuery(@RequestBody SysUser sysUser){
        Page<SysUser> sysUsers = sysUserSvc.selectPageSysUserByQuery(sysUser);
        PageResult<SysUser> result = new PageResult<SysUser>();
        result.autowire(sysUsers);
        return ApiResponse.success(result);
    }
}
