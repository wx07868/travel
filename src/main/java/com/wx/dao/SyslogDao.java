package com.wx.dao;

import com.github.pagehelper.PageInfo;
import com.wx.entity.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SyslogDao {

    @Insert("insert into syslog values(null,#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void addLog(SysLog syslog);

    @Select("select * from syslog")
    List<SysLog> findSyslog();
}
