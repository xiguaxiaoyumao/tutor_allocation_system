package com.pojo;

/**
 * 1.对应数据库中的post表
 *
 * @date 2020/10/26 8:08
 */
public class Post {
    private String postType;
    private String count;

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return this.postType;
    }
}
