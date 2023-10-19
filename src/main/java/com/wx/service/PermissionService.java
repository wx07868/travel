package com.wx.service;

import com.github.pagehelper.PageInfo;
import com.wx.entity.Permission;

import java.util.List;

public interface PermissionService {
    PageInfo findAllPm(Integer page, Integer size);

    void addpermission(Permission permission);

    void deletePermission(int id);

}
