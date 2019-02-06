package com.tp.soft.security.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tp.soft.security.dao.SysUserMapper;
import com.tp.soft.security.entity.SysUser;
import com.tp.soft.security.service.SysUserSvc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserSvcImpl implements SysUserSvc {

    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public List<SysUser> findAllUser() {
        return sysUserMapper.findAll();
    }

    @Override
    public SysUser findById(int userid) {
        return sysUserMapper.findById(userid);
    }

    @Override
    public Page<SysUser> selectPageSysUserByQuery(SysUser sysUser) {
        PageHelper.startPage(sysUser.getPageNumber(),sysUser.getPageSize(), Boolean.TRUE);
        return (Page<SysUser>) sysUserMapper.findAll();
    }

}
