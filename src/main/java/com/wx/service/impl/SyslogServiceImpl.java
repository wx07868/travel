package com.wx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.dao.SyslogDao;
import com.wx.entity.SysLog;
import com.wx.service.SyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyslogServiceImpl implements SyslogService {

    @Autowired
    private SyslogDao syslogDao;

    @Override
    public PageInfo<SysLog> findSyslog(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<SysLog> sysLogList = syslogDao.findSyslog();
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogList);
        return pageInfo;
    }
}
