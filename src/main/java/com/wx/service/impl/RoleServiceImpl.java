package com.wx.service.impl;

import com.github.pagehelper.PageHelper;
import com.wx.dao.RoleDao;
import com.wx.entity.Permission;
import com.wx.entity.Role;
import com.wx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /*查询角色*/
    @Override
    public List<Role> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    /*添加角色*/
    @Override
    public void addrole(Role role) {
        roleDao.addrole(role);
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findOtherPermission(Integer id) {
        return roleDao.findOtherPermission(id);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for (Integer permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public Role findById2(Integer id) {
        return roleDao.findById2(id);
    }

    @Override
    public void deleteRole(Integer id) {
        roleDao.deleteRole(id);
        roleDao.deleteOR(id);
    }
}
