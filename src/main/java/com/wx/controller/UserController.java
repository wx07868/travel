package com.wx.controller;

import com.github.pagehelper.PageInfo;
import com.wx.entity.Role;
import com.wx.entity.Users;
import com.wx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/user/")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("login")
    public String login(){
        return "admin/index.html";
    }


    @RequestMapping("loginout")
    public String loginout(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session!=null){
            session.invalidate();
        }
        return "login.html";
    }


    /*查询所有用户*/

    @RequestMapping("findAllUser")
    public ModelAndView findAllUser(@RequestParam(name="page",defaultValue = "1")Integer page,
                                    @RequestParam(name="size",defaultValue = "5")Integer size){
        ModelAndView mv = new ModelAndView();
        PageInfo<Users> users = userService.findAllUser(page,size);
        mv.addObject("pageInfo",users);
        mv.setViewName("admin/user-list");
        return mv;
    }


    /*添加用户*/

    @RequestMapping("addUser")
    public String addUser(Users user, HttpServletRequest req){

        //调用业务层添加方法
        userService.addUser(user);

        //响应信息
        req.setAttribute("message","添加成功");

        return "admin/user-add";

    }


    /*查询用户详细信息*/

    @RequestMapping("findById")
    public ModelAndView findById(@RequestParam(name="id")Integer id){
        ModelAndView mv = new ModelAndView();
        Users user = userService.findById(id);
        System.out.println(user);
        mv.addObject("user", user);
        mv.setViewName("admin/user-show");
        return mv;
    }


    /*添加用户角色*/
    @RequestMapping("findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") Integer id){
        //添加用户前，得知道你添加的用户是谁
        Users users =  userService.findUserByID(id);

        //根据用户查询哪些角色是可以添加（除了本身以经有的角色以外的才可以添加）
        List<Role> otherRoles = userService.findOtherRoles(id);

        ModelAndView mv = new ModelAndView();
        mv.addObject("user",users);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("admin/user-role-add");
        return mv;
    }

    @RequestMapping("addRoleToUser")
    public String addRoleToUser(@RequestParam("userId") Integer userId,
                                @RequestParam("ids") Integer[] ids){
        System.out.println(userId);
        for (int id : ids) {
            System.out.println(id);
        }
        userService.addRoleToUser(userId,ids);
        return "admin/user-list";

    }

    /*删除用户*/
    @RequestMapping("deleteUser")
    public String deleteUser(@RequestParam(name="id")Integer id){
        userService.deleteUser(id);
        return "redirect:findAllUser.do?page=1&size=5";
    }

}
