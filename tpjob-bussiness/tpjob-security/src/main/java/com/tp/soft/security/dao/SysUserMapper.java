package com.tp.soft.security.dao;

import com.tp.soft.security.entity.SysUser;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface SysUserMapper {

    public List<SysUser> findAll() throws DataAccessException;

    public SysUser findById(int userid) throws DataAccessException;

    public int insertUser(SysUser sysUser) throws DataAccessException;
}
