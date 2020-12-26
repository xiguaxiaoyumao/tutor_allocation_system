package com.dao;

import com.pojo.Post;
import com.util.DBUtil;

import java.util.List;

/**
 * 1.该类是通过操作DBUtil中的方法对数据库中post这个表进行操作
 *
 * @date 2020/10/26 8:09
 */
public class PostDao {
    //查询所有的职称信息
    public List<Post> selectAll() {
        String sql = "select * from post";
        List list = DBUtil.query(sql, Post.class);
        return list;
    }
    //更新职称信息
    public int update(Post post) {
        String sql = "update post set count = ? where postType = ?";
        int i = DBUtil.addDeleteUpdate(sql, post.getCount(), post.getPostType());
        return i;
    }
}
