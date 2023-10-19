package com.wx.service;

import com.github.pagehelper.PageInfo;
import com.wx.entity.SysLog;

public interface SyslogService {
    PageInfo<SysLog> findSyslog(Integer page, Integer size);
}
