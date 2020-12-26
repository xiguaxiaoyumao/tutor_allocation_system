package com.pojo;

/**
 * 1.对应数据库中的tutor表
 *
 * @date 2020/10/25 16:51
 */
public class Tutor {
    String tutId;
    String tutName;
    String gender;
    String post;
    String degree;
    String edu;

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    Integer stuCount;

    public Integer getStuCount() {
        return stuCount;
    }

    public void setStuCount(Integer stuCount) {
        this.stuCount = stuCount;
    }

    public String getTutId() {
        return tutId;
    }

    public void setTutId(String tutId) {
        this.tutId = tutId;
    }

    public String getTutName() {
        return tutName;
    }

    public void setTutName(String tutName) {
        this.tutName = tutName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
