package com.dao;

import com.pojo.Tutor;
import com.util.DBUtil;

import java.util.List;

/**
 * 1.该类是通过操作DBUtil中的方法对数据库对tutor这个表进行操作
 *
 * @date 2020/10/25 17:04
 */
public class TutorDao {
    //通过导师的编号查询导师信息
    public Tutor selectById(String tutId) {
        Tutor tutor = null;
        String sql = "select * from tutor where tutId = ?";
        List list = DBUtil.query(sql, Tutor.class, tutId);
        if (list.isEmpty()) {
            return tutor;
        }
        tutor = (Tutor) list.get(0);
        return tutor;
    }

    //通过导师的姓名查询导师信息
    public String selectByName(String name) {
        Tutor tutor = null;
        String sql = "select tutId from tutor where tutName = ?";
        List<Tutor> list = DBUtil.query(sql, Tutor.class, name);
        if (list.isEmpty()) {
            return "";
        }
        return list.get(0).getTutId();
    }

    //查询所有的导师信息
    public List<Tutor> selectAllTutor() {
        String sql = "select * from tutor";
        List list = DBUtil.query(sql, Tutor.class);
        return list;
    }

    //添加导师信息
    public int insert(Tutor tutor) {
        String sql = "insert into tutor(tutId,tutName,gender,post,degree,edu,stuCount) values(?,?,?,?,?,?,?)";
        int i = DBUtil.addDeleteUpdate(sql, tutor.getTutId(), tutor.getTutName(), tutor.getGender(), tutor.getPost(), tutor.getDegree(), tutor.getEdu(), tutor.getStuCount());
        return i;
    }

    //更新导师信息
    public int update(Tutor tutor) {
        String sql = "update tutor set tutName = ?, gender = ?,post = ?,degree = ? , edu = ? ,stuCount = ? where tutId = ?";
        int i = DBUtil.addDeleteUpdate(sql, tutor.getTutName(), tutor.getGender(), tutor.getPost(), tutor.getDegree(), tutor.getEdu(), tutor.getStuCount(), tutor.getTutId());
        return i;
    }

    //多条件查询导师信息
    public List<Tutor> select(String sql) {
        List<Tutor> list = DBUtil.query(sql, Tutor.class);
        return list;
    }

    //查询导师的数量
    public int selectAll() {
        String sql = "select count(*) from tutor";
        double v = DBUtil.uniqueQuery(sql);
        return ((int) v);
    }

    //助教数量
    public int selecta() {
        String sql = "select count(*) from tutor where post = ?";
        double v = DBUtil.uniqueQuery(sql, "助教");
        return ((int) v);
    }

    //讲师数量
    public int selectb() {
        String sql = "select count(*) from tutor where post = ?";
        double v = DBUtil.uniqueQuery(sql, "讲师");
        return ((int) v);
    }

    //副教授数量
    public int selectc() {
        String sql = "select count(*) from tutor where post = ?";
        double v = DBUtil.uniqueQuery(sql, "副教授");
        return ((int) v);
    }

    //教授数量
    public int selectd() {
        String sql = "select count(*) from tutor where post = ?";
        double v = DBUtil.uniqueQuery(sql, "教授");
        return ((int) v);
    }
}
