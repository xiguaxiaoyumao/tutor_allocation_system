package com.service;

import com.dao.LogDao;
import com.pojo.Log;
import com.util.ObUtil;

import java.util.List;

/**
 * 1.日志的相关服务
 *
 * @date 2020/10/26 14:20
 */
public class LogService {
    private LogDao logDao = ObUtil.getLogDao();

    //查询日志
    public List<Log> select(String type) {
        List<Log> logs = logDao.select(type);
        return logs;
    }
}
