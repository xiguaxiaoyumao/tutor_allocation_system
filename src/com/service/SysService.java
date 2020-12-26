package com.service;

import com.dao.LogDao;
import com.dao.PostDao;
import com.dao.StudentDao;
import com.dao.TutorDao;
import com.pojo.*;
import com.util.ObUtil;
import com.util.SesUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 1.系统服务 所有的dao共同来完成
 *
 * @date 2020/10/26 11:06
 */
public class SysService {
    private PostDao postDao = ObUtil.getPostDao();
    private TutorDao tutorDao = ObUtil.getTutorDao();
    private StudentDao studentDao = ObUtil.getStudentDao();
    private LogDao logDao = ObUtil.getLogDao();

    //学生分配导师方法
    public boolean distribute() {
        //获取职称所带人数
        List<Post> posts = null;
        if (SesUtil.getObject("posts") == null) {
            posts = postDao.selectAll();
        } else {
            posts = (List<Post>) SesUtil.getObject("posts");
        }
        //获取各职称能带人数
        int zhujiao = 0;
        int jiangshi = 0;
        int fujiaoshou = 0;
        int jiaoshou = 0;
        for (Post post : posts) {
            switch (post.getPostType()) {
                case "助教": {
                    zhujiao = new Integer(post.getCount());
                    break;
                }
                case "讲师": {
                    jiangshi = new Integer(post.getCount());
                    break;
                }
                case "副教授": {
                    fujiaoshou = new Integer(post.getCount());
                    break;
                }
                case "教授": {
                    jiaoshou = new Integer(post.getCount());
                    break;
                }
            }
        }
        //获取所有老师信息
        List<Tutor> tutors = tutorDao.selectAllTutor();
        //获取所有学生信息
        List<Student> students = studentDao.selectAllStu();

        //总共所能带的人数
        int a = tutorDao.selecta();
        int b = tutorDao.selectb();
        int c = tutorDao.selectc();
        int d = tutorDao.selectd();
        int distributed = 0;
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
        //总共学生人数
        int stuCount = students.size();
        if (stuCount > distributed) {
            Admin admin = (Admin) SesUtil.getObject("admin");
            Log log = new Log("查询", admin.getAdName() + " 分配失败");
            logDao.insertLog(log);
            //学生太多不够分配
            return false;
        }
        //随机数
        Random random = new Random();
        //完成分配的老师的集合
        List<Tutor> readyTutor = new ArrayList<>();
        for (Tutor tutor : tutors) {
            switch (tutor.getPost()) {
                case "助教": {
                    if (tutor.getStuCount() == zhujiao) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
                case "讲师": {
                    if (tutor.getStuCount() == jiangshi) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
                case "副教授": {
                    if (tutor.getStuCount() == fujiaoshou) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
                case "教授": {
                    if (tutor.getStuCount() == jiaoshou) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
            }
        }
        //算法
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (!(student.getTutor() == null || student.getTutor().isEmpty())) {
                continue;
            }
            int index = random.nextInt(tutors.size());
            Tutor tutor = tutors.get(index);
            student.setTutor(tutor.getTutName());
            tutor.setStuCount((tutor.getStuCount()) + 1);
            switch (tutor.getPost()) {
                case "助教": {
                    if (tutor.getStuCount() == zhujiao) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
                case "讲师": {
                    if (tutor.getStuCount() == jiangshi) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
                case "副教授": {
                    if (tutor.getStuCount() == fujiaoshou) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
                case "教授": {
                    if (tutor.getStuCount() == jiaoshou) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
            }
        }
        for (Student student : students) {
            studentDao.update(student);
        }
        for (Tutor tutor : tutors) {
            tutorDao.update(tutor);
        }
        for (Tutor tutor : readyTutor) {
            tutorDao.update(tutor);
        }
        Admin admin = (Admin) SesUtil.getObject("admin");
        Log log = new Log("查询", admin.getAdName() + " 分配成功");
        logDao.insertLog(log);
        return true;
    }

    //学生重新分配导师的方法
    public boolean redistribute() {
        //获取职称所带人数
        List<Post> posts = null;
        if (SesUtil.getObject("posts") == null) {
            posts = postDao.selectAll();
        } else {
            posts = (List<Post>) SesUtil.getObject("posts");
        }
        //获取各职称能带人数
        int zhujiao = 0;
        int jiangshi = 0;
        int fujiaoshou = 0;
        int jiaoshou = 0;
        for (Post post : posts) {
            switch (post.getPostType()) {
                case "助教": {
                    zhujiao = new Integer(post.getCount());
                    break;
                }
                case "讲师": {
                    jiangshi = new Integer(post.getCount());
                    break;
                }
                case "副教授": {
                    fujiaoshou = new Integer(post.getCount());
                    break;
                }
                case "教授": {
                    jiaoshou = new Integer(post.getCount());
                    break;
                }
            }
        }
        //获取所有老师信息
        List<Tutor> tutors = tutorDao.selectAllTutor();
        //获取所有学生信息
        List<Student> students = studentDao.selectAllStu();

        //总共所能带的人数
        int a = tutorDao.selecta();
        int b = tutorDao.selectb();
        int c = tutorDao.selectc();
        int d = tutorDao.selectd();
        int distributed = 0;
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
        //总共学生人数
        int stuCount = students.size();
        if (stuCount > distributed) {
            Admin admin = (Admin) SesUtil.getObject("admin");
            Log log = new Log("查询", admin.getAdName() + " 重新分配失败");
            logDao.insertLog(log);
            //学生太多不够分配
            return false;
        }
        //随机数
        Random random = new Random();
        //完成分配的老师的集合
        List<Tutor> readyTutor = new ArrayList<>();
        //清空
        for (Student student : students) {
            student.setTutor("");
        }
        for (Tutor tutor : tutors) {
            tutor.setStuCount(0);
        }
        //算法
        for (Student student : students) {
            int index = random.nextInt(tutors.size());
            Tutor tutor = tutors.get(index);
            student.setTutor(tutor.getTutName());
            tutor.setStuCount((tutor.getStuCount()) + 1);
            switch (tutor.getPost()) {
                case "助教": {
                    if (tutor.getStuCount() == zhujiao) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
                case "讲师": {
                    if (tutor.getStuCount() == jiangshi) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
                case "副教授": {
                    if (tutor.getStuCount() == fujiaoshou) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
                case "教授": {
                    if (tutor.getStuCount() == jiaoshou) {
                        readyTutor.add(tutor);
                        tutors.remove(tutor);
                    }
                    break;
                }
            }
        }
        for (Student student : students) {
            studentDao.update(student);
        }
        for (Tutor tutor : tutors) {
            tutorDao.update(tutor);
        }
        for (Tutor tutor : readyTutor) {
            tutorDao.update(tutor);
        }
        Admin admin = (Admin) SesUtil.getObject("admin");
        Log log = new Log("查询", admin.getAdName() + " 重新分配成功");
        logDao.insertLog(log);
        return true;
    }

    public void outputExcel(File file) {
        try {
            WritableWorkbook wwb = null;

            // 创建可写入的Excel工作簿
            String path = file.getPath();
            path = path + "//result.xls";
            file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以fileName为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);

            // 创建工作表
            WritableSheet ws = wwb.createSheet("Sheet1", 0);

            //查询数据库中所有的数据
            List<Student> list = studentDao.selectAllStu();
            //要插入到的Excel表格的行号，默认从0开始
            Label stuId = new Label(0, 0, "学生编号");
            Label StuName = new Label(1, 0, "学生姓名");
            Label TutId = new Label(2, 0, "导师编号");
            Label TutName = new Label(3, 0, "导师姓名");

            ws.addCell(stuId);
            ws.addCell(StuName);
            ws.addCell(TutId);
            ws.addCell(TutName);
            for (int i = 0; i < list.size(); i++) {

                Label stuId_i = new Label(0, i + 1, list.get(i).getStuId());
                Label StuName_i = new Label(1, i + 1, list.get(i).getStuName());
                Label TutId_i = new Label(2, i + 1, tutorDao.selectByName(list.get(i).getTutor()));
                Label TutName_i = new Label(3, i + 1, list.get(i).getTutor());
                ws.addCell(stuId_i);
                ws.addCell(StuName_i);
                ws.addCell(TutId_i);
                ws.addCell(TutName_i);
            }

            //写进文档
            wwb.write();
            // 关闭Excel工作簿对象
            wwb.close();
            Admin admin = (Admin) SesUtil.getObject("admin");
            Log log = new Log("查询", admin.getAdName() + " 导出分配结果Excel表");
            logDao.insertLog(log);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
