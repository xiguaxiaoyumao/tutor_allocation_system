package com.service;

import com.dao.LogDao;
import com.dao.StudentDao;
import com.pojo.Admin;
import com.pojo.Log;
import com.pojo.Student;
import com.util.ObUtil;
import com.util.SesUtil;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.学生鲜相关的服务
 *
 * @date 2020/10/25 12:53
 */
public class StudentService {
    private StudentDao studentDao = ObUtil.getStudentDao();
    private LogDao logDao = ObUtil.getLogDao();

    //从Excel表中获取数据
    private List<Student> getAllFromExcel(File file) {
        List<Student> list = new ArrayList<Student>();
        try {
            Workbook rwb = Workbook.getWorkbook(file);
            Sheet rs = rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos = rs.getColumns();//得到所有的列
            int rows = rs.getRows();//得到所有的行
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    Student student = new Student();
                    student.setStuId(rs.getCell(j++, i).getContents());
                    student.setStuName(rs.getCell(j++, i).getContents());
                    student.setGender(rs.getCell(j++, i).getContents());
                    student.setStuClass(rs.getCell(j++, i).getContents());
                    student.setTutor(rs.getCell(j++, i).getContents());
                    list.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //判断数据库中是否有该条数据 如果有就更新 如果没有就添加
    private boolean isExist(String stuId) {
        Student student = studentDao.selectById(stuId);
        if (student != null) {
            return true;
        }
        return false;
    }

    //1.将从Excel表中获取的数据导入数据库，如果已有有就更新 如果没有就添加
    //2.添加一条日志
    public int insertFromExcel(File file) {
        int i = 0;
        int j = 0;
        List<Student> students = this.getAllFromExcel(file);
        for (Student student : students) {
            if (isExist(student.getStuId())) {
                studentDao.update(student);
                i++;
            } else {
                studentDao.insert(student);
                j++;
            }
        }
        Admin admin = (Admin) SesUtil.getObject("admin");
        Log log = new Log("添加", admin.getAdName() + " 导入学生Excel表");
        logDao.insertLog(log);
        return i + j;
    }

    //1.获取学生相关的基本信息 2.添加日志
    public Map<String, Integer> getBaseInfo() {
        //获取所有的学生数
        int total = studentDao.selectAllCount();
        //获取没有导师的学生数
        int undistributed = studentDao.selectTutIsNull();
        //获取已有导师的学生数
        int distributed = studentDao.selectTutNotNull();
        //存入集合
        Map<String, Integer> map = new HashMap<>();
        map.put("total", total);
        map.put("distributed", distributed);
        map.put("undistributed", undistributed);
        Admin admin = (Admin) SesUtil.getObject("admin");
        Log log = new Log("查询", admin.getAdName() + " 刷新学生信息");
        logDao.insertLog(log);
        return map;
    }

    //查询班级的类型
    public List<Student> selectClassType() {
        List<Student> students = studentDao.selectClassType();
        return students;
    }

    //1.多条件查询学生信息 控制层传来SQL 语句 2.添加日志
    public List<Student> select(String sql) {
        List<Student> students = studentDao.select(sql);
        Admin admin = (Admin) SesUtil.getObject("admin");
        Log log = new Log("查询", admin.getAdName() + " 查询学生信息");
        logDao.insertLog(log);
        return students;
    }
}
