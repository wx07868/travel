package com.wx.service;

import com.github.pagehelper.PageInfo;
import com.wx.entity.Role;
import com.wx.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    PageInfo<Users> findAllUser(int page, int size);

    void addUser(Users user);

    Users findById(int id);

    Users findUserByID(int id);

    List<Role> findOtherRoles(int id);

    void addRoleToUser(Integer userId, Integer[] ids);

    void deleteUser(int id);
}
