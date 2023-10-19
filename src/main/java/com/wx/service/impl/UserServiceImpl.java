package com.wx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.dao.UserDao;
import com.wx.entity.Role;
import com.wx.entity.Users;
import com.wx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("Service11")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 登录认证业务方法
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //调用持久层方法，根据账号或用户的信息
        Users users = userDao.findAccountByUsername(username);

        //创建一个认证对象
        User user = new User(users.getUsername(), "{noop}" + users.getPassword(), users.getStatus() == 1, true, true, true, getRoles(users.getRoles()));


        System.out.println(user);
        return user;
    }


    /**
     * 提取用户的角色权限名称
     * @param roles
     * @return
     */
    private Collection<? extends GrantedAuthority> getRoles(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {

            SimpleGrantedAuthority sa = new SimpleGrantedAuthority("ROLE_"+role.getRolename());

            list.add(sa);
        }

        return list;
    }


    /*查询所用用户*/
    @Override
    public PageInfo<Users> findAllUser(int page, int size) {

        PageHelper.startPage(page,size);

        List<Users> users = userDao.findAllUser();

        PageInfo<Users> pageInfo = new PageInfo<Users>(users);

        return pageInfo;
    }

    /*添加用户*/
    @Override
    public void addUser(Users user) {
        userDao.addUser(user);
    }

    /*查询用户详细信息包括权限等信息*/
    @Override
    public Users findById(int id) {
        return userDao.findById(id);
    }

    /*查询用户的详细信息*/
    @Override
    public Users findUserByID(int id) {
        return userDao.findUserByID(id);
    }

    /*查询用户没有的权限*/
    @Override
    public List<Role> findOtherRoles(int id) {
        return userDao.findOtherRoles(id);
    }

    /*添加用户还没有的权限*/
    @Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        for(Integer roleid : ids){
            userDao.addRoleToUser(userId,roleid);
        }
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
        userDao.deleteGXUser(id);
    }

}
