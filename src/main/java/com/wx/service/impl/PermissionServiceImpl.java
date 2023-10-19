package com.wx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.dao.PermissionDao;
import com.wx.entity.Permission;
import com.wx.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public PageInfo findAllPm(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Permission> permissionList = permissionDao.findAllPm(page, size);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissionList);
        return pageInfo;
    }

    @Override
    public void addpermission(Permission permission) {
        permissionDao.addpermission(permission);
    }

    @Override
    public void deletePermission(int id) {
        permissionDao.deletePermission(id);
        permissionDao.deleteRP(id);
    }
}
