package com.tp.soft.security.service;

import com.github.pagehelper.Page;
import com.tp.soft.security.entity.SysUser;

import java.util.List;

public interface SysUserSvc {
    public List<SysUser> findAllUser();

    public SysUser findById(int userid);

    public Page<SysUser> selectPageSysUserByQuery(SysUser sysUser);
}
