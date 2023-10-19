package com.wx.dao;

import com.wx.entity.Permission;
import com.wx.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RoleDao {

    @Select("select r.* from role r,users_role ur where r.id = ur.roleId and ur.userId=#{userId}")
    List<Role> findRolesByUserid(int userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role values(null,#{rolename},#{roledesc})")
    void addrole(Role role);


    Role findById(Integer id);


    List<Permission> findOtherPermission(Integer id);

    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    @Select("select * from role where id = #{id}")
    Role findById2(Integer id);

    @Delete("delete from role where id = #{id}")
    void deleteRole(Integer id);

    @Delete("delete from role_permission where roleId = #{id}")
    void deleteOR(Integer id);
}
