package com.tp.soft.security.service;

import com.tp.soft.security.entity.SysUser;

import java.util.List;

public interface SysUserSvc {
    public List<SysUser> findAllUser();

    public SysUser findById(int userid);
}
