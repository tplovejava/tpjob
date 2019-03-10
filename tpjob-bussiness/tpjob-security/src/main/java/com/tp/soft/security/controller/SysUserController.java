package com.tp.soft.security.controller;

import com.github.pagehelper.Page;
import com.tp.soft.security.entity.SysUser;
import com.tp.soft.security.service.SysUserSvc;
import com.tp.soft.util.model.PageResult;
import com.tp.soft.util.web.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api("用户管理")
public class SysUserController {

    @Resource
    private SysUserSvc sysUserSvc;

    @ApiOperation(value = "查询用户", notes = "查询用户")
    @GetMapping("queryList")
    public ApiResponse<List<SysUser>> queryList(){
        //System.out.println(keyname);
        return ApiResponse.success(sysUserSvc.findAllUser());
    }

    @ApiOperation(value = "按分页查询用户", notes = "按分页查询用户")
    @PostMapping("selectPageSysUserByQuery")
    public ApiResponse<PageResult<SysUser>> selectPageSysUserByQuery(@RequestBody SysUser sysUser){
        Page<SysUser> sysUsers = sysUserSvc.selectPageSysUserByQuery(sysUser);
        PageResult<SysUser> result = new PageResult<SysUser>();
        result.autowire(sysUsers);
        return ApiResponse.success(result);
    }

    @PostMapping("/addUser")
    public ApiResponse addUser(@RequestBody SysUser sysUser){
        return sysUserSvc.addUser(sysUser);
    }


}
