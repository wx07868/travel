package com.wx.dao;

import com.wx.entity.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("select * from permission")
    List<Permission> findAllPm(Integer page, Integer size);

    @Insert("insert into permission values(null,#{permissionName},#{url})")
    void addpermission(Permission permission);

    @Delete("delete from permission where id = #{id}")
    void deletePermission(int id);

    @Delete("delete from role_permission where permissionId = #{id}")
    void deleteRP(int id);
}
