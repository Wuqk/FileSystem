package com.weqia.site.fileSystem.dao;

import com.weqia.site.fileSystem.pojo.fileEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  文件管理数据库操作层
 */
@Component
@Mapper
public interface FileMapping {

    /**
     * 文件上传
     * @param fEntity
     */
    @Insert("insert into files(fname,fpath,uid) value (#{fEntity.fname},#{fEntity.fpath},#{fEntity.uid});")
    void upLoad(@Param("fEntity") fileEntity fEntity );

    /**
     * 查找所有文件
     * @return
     */
    @Select("select * from files where uid = #{uid};")
    List<fileEntity> findAll(int uid);

    /**
     * 根据文件id查找文件
     * @param fid
     * @return
     */
    @Select("select * from files where fid = #{fid};")
    fileEntity findById(int fid);


    /**
     * 根据文件id删除文件
     * @param fid
     */
    @Delete("delete from files where fid = #{fid};")
    void  del(int fid);
}
