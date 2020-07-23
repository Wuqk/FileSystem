package com.weqia.site.fileSystem.pojo;

/**
 * 文件实体类
 */
public class fileEntity {
    //文件id
    private int fid;
    //文件名
    private String fname;
    //文件保存路径
    private String fpath;
    //用户id
    private int uid;

    public fileEntity(String fname,String fpath,int uid){
        this.fname = fname;
        this.fpath = fpath;
        this.uid = uid;
    }

    public fileEntity(int fid,String fname,String fpath,int uid){
        this.fid = fid;
        this.fname = fname;
        this.fpath = fpath;
        this.uid = uid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFpath() {
        return fpath;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }
}
