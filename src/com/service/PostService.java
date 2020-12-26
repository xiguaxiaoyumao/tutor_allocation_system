package com.service;

import com.dao.LogDao;
import com.dao.PostDao;
import com.pojo.Admin;
import com.pojo.Log;
import com.pojo.Post;
import com.util.ObUtil;
import com.util.SesUtil;

import java.util.List;

/**
 * 1.职称的相关服务
 *
 * @date 2020/10/26 8:09
 */
public class PostService {
    private PostDao postDao = ObUtil.getPostDao();
    private LogDao logDao = ObUtil.getLogDao();

    //查询所有的职称 在加载的时候就执行这个操作 不是管理员操作的所以是不需要日志的
    public List<Post> selectAll() {
        List<Post> posts = postDao.selectAll();
        return posts;
    }

    //更新职称信息 1.更新职称信息 2.添加一个日志
    public int update(Post post) {
        int update = postDao.update(post);
        Admin admin = (Admin) SesUtil.getObject("admin");
        Log log = new Log("修改", admin.getAdName() + " 修改职称所带人数限制");
        logDao.insertLog(log);
        return update;
    }

}
