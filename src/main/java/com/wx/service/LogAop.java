package com.wx.service;


import com.wx.dao.SyslogDao;
import com.wx.entity.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Service
@Aspect
public class LogAop {


    @Autowired
    private HttpServletRequest request;

    //日志对象
    private SysLog syslog;

    //持久层对象
    @Autowired
    private SyslogDao syslogDao;



    @Before("execution(* com.wx.controller.*.*(..))")
    public void before(JoinPoint point){
        //获取一个开始时间
        Date startTime = new Date();


        /********************获取方法的controller类上的共有路径*********************/
        //获取当前切入的controller类的反射对象
        Class controllerClass = point.getTarget().getClass();



        //获取类之上的RequestMapping
        RequestMapping annotation =(RequestMapping) controllerClass.getAnnotation(RequestMapping.class);
        String[] value = annotation.value();
        String path = value[0];
        System.out.println("类之上的访问路径"+path);


        /*************************获取controller方法上的访问路径*************************/

        /*//获取切入的controller方法名
        String methodName = point.getSignature().getName();

        //获取方法的参数
        Object[] args = point.getArgs();

        //获取方法参数类型
        Class[] argsCls = new Class[args.length];

        for (int i = 0; i < args.length; i++) {

            Object arg = args[i];

            argsCls[i] = arg.getClass();

        }*/

        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method1 = ms.getMethod();
        //获取方法对象



            //获取方法上的RequestMapping
            RequestMapping rm1 = method1.getAnnotation(RequestMapping.class);

            String[] value1 = rm1.value();

            String methodUrl = value1[0];

            //方法的请求总路径
            String url = path+methodUrl;

            /*************************获取当前登录的用户*************************/
            //获取springSecurity上下文对象
            SecurityContext context = SecurityContextHolder.getContext();

            //获取当前登录用户
            User user =(User) context.getAuthentication().getPrincipal();

            //获取用户名
            String username = user.getUsername();

            //获取ip地址
            String address = request.getRemoteAddr();

            address = address.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":address;


            //创建一个日志对象
            syslog = new SysLog();
            syslog.setUrl(url);
            syslog.setMethod(method1.getName());
            syslog.setIp(address);
            syslog.setVisitTime(startTime);
            syslog.setUsername(username);





    }


    @After("execution(* com.wx.controller.*.*(..))")
    public void after(){

        Date endDate = new Date();

        //计算执行时间
        long excuteTime = endDate.getTime() - syslog.getVisitTime().getTime();

        syslog.setExecutionTime(excuteTime);

        //添加到数据库
        syslogDao.addLog(syslog);
    }

}
