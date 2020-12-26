package com.dao;

import com.pojo.Student;
import com.util.DBUtil;

import java.util.List;

/**
 * 1.该类是通过操作DBUtil中的方法对数据库中student这个表进行操作
 *
 * @date 2020/10/25 12:47
 */
public class StudentDao {
    //通过学生id查询学生
    public Student selectById(String stuId) {
        Student student = null;
        String sql = "select * from student where stuId = ?";
        List list = DBUtil.query(sql, Student.class, stuId);
        if (list.isEmpty()) {
            return student;
        }
        student = (Student) list.get(0);
        return student;
    }

    //查询所有的学生
    public List<Student> selectAllStu() {
        String sql = "select * from student";
        List list = DBUtil.query(sql, Student.class);
        return list;
    }

    //查询有导师的学
    public int selectTutNotNull() {
        String sql = "select count(*) from student where tutor is not null and tutor != ''";
        double v = DBUtil.uniqueQuery(sql);
        return ((int) v);
    }

    //查询没有导师的学生
    public int selectTutIsNull() {
        String sql = "select count(*) from student where tutor is null or tutor = ''";
        double v = DBUtil.uniqueQuery(sql);
        return ((int) v);
    }

    //查询学生的总数
    public int selectAllCount() {
        String sql = "select count(*) from student";
        double v = DBUtil.uniqueQuery(sql);
        return ((int) v);
    }

    //查询学生的班级类型
    public List<Student> selectClassType() {
        String sql = "select stuClass from student group by stuClass";
        List<Student> list = DBUtil.query(sql, Student.class);
        return list;
    }

    //更新学生信息
    public int update(Student student) {
        String sql = "update student set stuName = ? ,gender = ? ,stuClass = ? ,tutor = ? where stuId = ?";
        int i = DBUtil.addDeleteUpdate(sql, student.getStuName(), student.getGender(), student.getStuClass(), student.getTutor(), student.getStuId());
        return i;
    }

    //添加学生信息
    public int insert(Student student) {
        String sql = "insert into student(stuId,stuName,gender,stuClass,tutor) values(?,?,?,?,?)";
        int i = DBUtil.addDeleteUpdate(sql, student.getStuId(), student.getStuName(), student.getGender(), student.getStuClass(), student.getTutor());
        return i;
    }

    //多条件查询学生信息 由控制层传来SQL语句
    public List<Student> select(String sql) {
        List list = DBUtil.query(sql, Student.class);
        return list;
    }

}
