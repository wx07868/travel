package com.wx.dao;

import com.wx.entity.Role;
import com.wx.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户持久层
 */
public interface UserDao {

    /*查询用户登录权限*/
    @Select("select * from users where username=#{username}")
    @Results(id="accountMapper",value = {
            @Result(property = "roles",javaType = List.class,column = "id",many = @Many(select = "com.wx.dao.RoleDao.findRolesByUserid"))
    })
    Users findAccountByUsername(String username);

    /*查询所有用户*/
    @Select("select * from users")
    List<Users> findAllUser();

    /*添加用户信息*/
    @Insert("insert into users values(null,#{username},#{email},#{phoneNum},#{password},#{status})")
    void addUser(Users user);


    /*查询单个用户信息详情包括权限等*/
    Users findById(int id);

    /*查询用户信息*/
    Users findUserByID(int id);

    /*查询用户还没有的权限*/
    List<Role> findOtherRoles(int id);

    /*添加权限*/
    void addRoleToUser(@Param("userId") Integer userId,@Param("roleid") Integer roleid);

    @Delete("delete from users where id = #{id}")
    void deleteUser(int id);

    @Delete("delete from users_role where userId = #{id}")
    void deleteGXUser(int id);
}
