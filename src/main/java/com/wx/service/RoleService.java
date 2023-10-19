package com.wx.service;

import com.wx.entity.Permission;
import com.wx.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll(Integer page, Integer size);

    void addrole(Role role);

    Role findById(Integer id);

    List<Permission> findOtherPermission(Integer id);

    void addPermissionToRole(Integer roleId, Integer[] permissionIds);

    Role findById2(Integer id);

    void deleteRole(Integer id);
}
