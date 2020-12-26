package com.dao;

import com.pojo.Log;
import com.util.DBUtil;

import java.util.List;

/**
 * 1.该类是通过操作DBUtil中的方法对数据库中log这个表进行操作
 *
 * @date 2020/10/26 14:05
 */
public class LogDao {
    //添加日志
    public void insertLog(Log log) {
        String sql = "insert into log(opType,opDetail,opTime) values(?,?,NOW())";
        DBUtil.addDeleteUpdate(sql, log.getOpType(), log.getOpDetail());
    }

    //根据日志类型查询日志
    public List<Log> select(String type) {
        String sql = "";
        List<Log> list = null;
        if (type == "") {
            sql = "select * from log";
            list = DBUtil.query(sql, Log.class);
        } else {
            sql = "select * from log where opType like ?";
            list = DBUtil.query(sql, Log.class, "%" + type + "%");
        }
        return list;
    }
}
