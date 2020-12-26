package com.pojo;

/**
 * 1.对应数据库中的admin表
 * 2.一个类对应一张表 一个字段对应一个属性 一个查询结果对应一个对象 对象可以通过get和set方法获取属性值
 * @date 2020/10/25 10:42
 */
public class Admin {
    private int adId;
    private String adName;
    private String adPassword;

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdPassword() {
        return adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword;
    }
}
