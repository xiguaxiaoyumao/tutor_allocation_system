package com.service;

import com.dao.LogDao;
import com.dao.StudentDao;
import com.dao.TutorDao;
import com.pojo.*;
import com.util.DBUtil;
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
 * 1.导师相关的服务方法
 *
 * @date 2020/10/25 17:11
 */
public class TutorService {
    private TutorDao tutorDao = ObUtil.getTutorDao();
    private StudentDao studentDao = ObUtil.getStudentDao();
    private LogDao logDao = ObUtil.getLogDao();
    //从Excel表中获取导师信息
    private List<Tutor> getAllFromExcel(File file) {
        List<Tutor> list = new ArrayList<Tutor>();
        try {
            Workbook rwb = Workbook.getWorkbook(file);
            Sheet rs = rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos = rs.getColumns();//得到所有的列
            int rows = rs.getRows();//得到所有的行
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    Tutor tutor = new Tutor();
                    tutor.setTutId(rs.getCell(j++, i).getContents());
                    tutor.setTutName(rs.getCell(j++, i).getContents());
                    tutor.setGender(rs.getCell(j++, i).getContents());
                    tutor.setPost(rs.getCell(j++, i).getContents());
                    tutor.setDegree(rs.getCell(j++, i).getContents());
                    tutor.setEdu(rs.getCell(j++, i).getContents());
                    tutor.setStuCount(new Integer(rs.getCell(j++, i).getContents()));
                    list.add(tutor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private boolean isExist(String stuId) {
        Tutor tutor = tutorDao.selectById(stuId);
        if (tutor != null) {
            return true;
        }
        return false;
    }

    public int insertFromExcel(File file) {
        int i = 0;
        int j = 0;
        List<Tutor> tutors = this.getAllFromExcel(file);
        for (Tutor tutor : tutors) {
            if (isExist(tutor.getTutId())) {
                tutorDao.update(tutor);
                i++;
            } else {
                tutorDao.insert(tutor);
                j++;
            }
        }

        Admin admin = (Admin) SesUtil.getObject("admin");
        Log log = new Log("添加", admin.getAdName() + " 导入导师Excel表");
        logDao.insertLog(log);
        return i + j;
    }
    //1.多条件查询导师信息 2.添加一个日志
    public List<Tutor> select(String sql) {
        List<Tutor> tutors = tutorDao.select(sql);
        Admin admin = (Admin) SesUtil.getObject("admin");
        Log log = new Log("查询", admin.getAdName() + " 查询导师信息");
        logDao.insertLog(log);
        return tutors;
    }
    //1.查询导师的基本信息 刷新调用该方法 2.添加日志
    public Map<String, Integer> selectInfo() {
        int distributed = 0;
        int undistributed = studentDao.selectTutNotNull();
        int total = tutorDao.selectAll();
        int a = tutorDao.selecta();
        int b = tutorDao.selectb();
        int c = tutorDao.selectc();
        int d = tutorDao.selectd();
        List<Post> posts = (List<Post>) SesUtil.getObject("posts");
        for (Post post : posts) {
            if ("助教".equals(post.getPostType())) {
                distributed += a * new Integer(post.getCount());
            }
            if ("讲师".equals(post.getPostType())) {
                distributed += b * new Integer(post.getCount());
            }
            if ("副教授".equals(post.getPostType())) {
                distributed += c * new Integer(post.getCount());
            }
            if ("教授".equals(post.getPostType())) {
                distributed += d * new Integer(post.getCount());
            }
        }
        Map<String, Integer> tutInfo = new HashMap<>();
        tutInfo.put("total", total);
        tutInfo.put("distributed", distributed);
        tutInfo.put("undistributed", undistributed);


        Admin admin = (Admin) SesUtil.getObject("admin");
        Log log = new Log("查询", admin.getAdName() + " 刷新导师信息");
        logDao.insertLog(log);

        return tutInfo;
    }
}
