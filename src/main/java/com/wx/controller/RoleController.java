package com.wx.controller;

import com.github.pagehelper.PageInfo;
import com.wx.entity.Permission;
import com.wx.entity.Role;
import com.wx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/admin/role/")
@RolesAllowed("ROLE_BOSS")
public class RoleController {


    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    /*查询所有角色信息*/
    @RequestMapping("findAll")
    public ModelAndView findAll(@RequestParam(name="page",defaultValue = "1")Integer page,
                                @RequestParam(name="size",defaultValue = "5")Integer size){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(roleList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("admin/role-list");
        return mv;
    }

    /*添加角色信息*/
    @RequestMapping("addrole")
    public String addrole(Role role){
        roleService.addrole(role);
        return "redirect:findAll.do?page=1&size=5";
    }

    /*查询角色*/
    @RequestMapping("findById")
    public ModelAndView findById(@RequestParam("id") Integer id){
        Role role = roleService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.setViewName("admin/role-show");
        return mv;
    }

    /*查询角色的信息及其权限*/
    @RequestMapping("findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam("id") Integer id){

        Role role = roleService.findById2(id);

        List<Permission> permissionList = roleService.findOtherPermission(id);

        ModelAndView mv = new ModelAndView();

        mv.addObject("role" ,role);
        mv.addObject("permissionList" ,permissionList);
        mv.setViewName("admin/role-permission-add");

        return mv;
    }

    /*给角色添加权限*/
    @RequestMapping("addPermissionToRole")
    public String addPermissionToRole(@RequestParam("roleId") Integer roleId,@RequestParam("ids") Integer[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "admin/role-permission-add";
    }



    @RequestMapping("deleteRole")
    public String deleteRole(@RequestParam(name = "id") Integer id){
        roleService.deleteRole(id);
        return "redirect:findAll.do?page=1&size=5";
    }



}
