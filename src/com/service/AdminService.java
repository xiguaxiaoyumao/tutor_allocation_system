package com.service;

import com.dao.AdminDao;
import com.dao.LogDao;
import com.pojo.Admin;
import com.pojo.Log;
import com.util.ObUtil;

import java.sql.SQLException;

/**
 * 1.管理员服务类执行管理员相关的操作
 *
 * @date 2020/10/25 10:50
 */
public class AdminService {
    private AdminDao adminDao = ObUtil.getAdminDao();
    private LogDao logDao = ObUtil.getLogDao();

    //登录 1.插入一条登录的日志 2.调用adminDao中的login方法登录
    public Admin login(String adName, String adPassword) throws SQLException {
        Log log = new Log("查询", adName + " 登录");
        //插入日志
        logDao.insertLog(log);
        return adminDao.login(adName, adPassword);
    }

    //修改密码 1.插入一条修改密码的日志 2.调用adminDao中的resetPassword方法修改密码
    public int resetPassword(Admin admin) {
        Log log = new Log("修改", admin.getAdName() + " 修改密码");
        logDao.insertLog(log);
        return adminDao.resetPassword(admin);
    }

}
