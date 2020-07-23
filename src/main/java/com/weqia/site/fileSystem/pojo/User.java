package com.weqia.site.fileSystem.pojo;

import java.io.Serializable;

/**
 * 用户实体类，实现Serializable，方便redis存放
 */
public class User implements Serializable {
    //用户id
    private int uid;
    //用户名
    private String uname;
    //用户密码
    private String upwd;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
}
