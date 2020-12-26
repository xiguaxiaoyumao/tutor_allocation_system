package com.dao;

import com.pojo.Admin;
import com.util.DBUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * 1.该类是通过操作DBUtil中的方法对数据库中admin这个表进行操作
 *
 * @date 2020/10/25 10:43
 */
public class AdminDao {
    //管理员登录输入账号密码查询是否有该管理员
    public Admin login(String adName, String adPassword) throws SQLException {
        Admin admin = null;
        String sql = "select adId,adName,adPassword from admin where adName = ? and adPassword = ?";
        List list = DBUtil.query(sql, Admin.class, adName, adPassword);
        if (list.isEmpty()) {
            return admin;
        }
        admin = (Admin) list.get(0);
        return admin;
    }

    //修改密码
    public int resetPassword(Admin admin) {
        String sql = "update admin set adPassword = ? where adId = ?";
        int i = DBUtil.addDeleteUpdate(sql, admin.getAdPassword(), admin.getAdId());
        return i;
    }

}
