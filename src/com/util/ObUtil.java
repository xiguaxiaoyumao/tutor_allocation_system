package com.util;

import com.dao.*;
import com.service.*;

/**
 * 1.这个类是一个容器类，里面生成了很多全局要用的类
 * 2.在需要使用某个类的时候直接通过这个容器类 中该类的get方法来获取
 * 3.程序开启后容器里面的类就会生成，在其他地方要用的时候直接通过静态方法获取就行
 * @date 2020/10/25 10:51
 */
public class ObUtil {
    private static AdminDao adminDao = null;
    private static AdminService adminService = null;
    private static StudentDao studentDao = null;
    private static StudentService studentService = null;
    private static TutorDao tutorDao = null;
    private static TutorService tutorService = null;
    private static PostDao postDao = null;
    private static PostService postService = null;
    private static SysService sysService = null;
    private static LogDao logDao = null;
    private static LogService logService = null;

    public static AdminService getAdminService() {
        return adminService;
    }

    public static AdminDao getAdminDao() {
        return adminDao;
    }

    public static StudentDao getStudentDao() {
        return studentDao;
    }

    public static StudentService getStudentService() {
        return studentService;
    }

    public static TutorDao getTutorDao() {
        return tutorDao;
    }

    public static TutorService getTutorService() {
        return tutorService;
    }

    public static PostDao getPostDao() {
        return postDao;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static SysService getSysService() {
        return sysService;
    }

    public static LogService getLogService() {
        return logService;
    }

    public static LogDao getLogDao() {
        return logDao;
    }

    //静态块在类加载的时候就会执行
    static {
        logDao = new LogDao();
        adminDao = new AdminDao();
        studentDao = new StudentDao();
        tutorDao = new TutorDao();
        postDao = new PostDao();
        adminService = new AdminService();
        studentService = new StudentService();
        tutorService = new TutorService();
        postService = new PostService();
        sysService = new SysService();
        logService = new LogService();
    }
}
