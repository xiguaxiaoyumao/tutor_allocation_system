package com.pojo;

import java.sql.Timestamp;

/**
 * 1.对应数据库中的log表
 *
 * @date 2020/10/26 14:02
 */
public class Log {
    Integer logId;
    String opType;
    String opDetail;
    Timestamp opTime;

    public Log(String opType, String opDetail) {
        this.opType = opType;
        this.opDetail = opDetail;
    }

    public Log() {
    }


    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getOpDetail() {
        return opDetail;
    }

    public void setOpDetail(String opDetail) {
        this.opDetail = opDetail;
    }

    public Timestamp getOpTime() {
        return opTime;
    }

    public void setOpTime(Timestamp opTime) {
        this.opTime = opTime;
    }
}
