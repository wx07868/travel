package com.wx.controller;

import com.github.pagehelper.PageInfo;
import com.wx.entity.Permission;
import com.wx.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/admin/permission/")
@RolesAllowed("ROLE_BOSS")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /*查询所有权限*/
    @RequestMapping("findAllPm")
    public ModelAndView findAllPm(@RequestParam(name="page",defaultValue = "1")Integer page,
                                   @RequestParam(name="size",defaultValue = "5")Integer size){

        PageInfo pageInfo = permissionService.findAllPm(page,size);

        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("admin/permission-list");
        return mv;

    }

    /*添加权限*/
    @RequestMapping("addpermission")
    public String addpermission(Permission permission){
        permissionService.addpermission(permission);
        return "redirect:findAllPm.do?page=1&size=5";
    }

    /*删除权限*/
    @RequestMapping("deletePermission")
    public String deletePermission(@RequestParam(name = "id") Integer id){
        permissionService.deletePermission(id);
        return "redirect:findAllPm.do?page=1&size=5";
    }

}
