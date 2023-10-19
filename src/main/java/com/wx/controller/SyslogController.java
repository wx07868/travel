package com.wx.controller;

import com.github.pagehelper.PageInfo;
import com.wx.entity.SysLog;
import com.wx.service.SyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/syslog/")
public class SyslogController {

    @Autowired
    private SyslogService syslogService;


    @RequestMapping("findSyslog")
    public ModelAndView findSyslog(@RequestParam(name="page",defaultValue = "1")Integer page,
                                   @RequestParam(name="size",defaultValue = "5")Integer size){
        ModelAndView mv = new ModelAndView();

        PageInfo<SysLog> sysLogPageInfo = syslogService.findSyslog(page,size);

        mv.addObject("pageInfo",sysLogPageInfo);
        mv.setViewName("admin/syslog-list");
        return mv;
    }


}
