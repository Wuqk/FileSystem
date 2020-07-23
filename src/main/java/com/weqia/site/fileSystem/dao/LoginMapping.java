package com.weqia.site.fileSystem.dao;

import com.weqia.site.fileSystem.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 用户数据交换层
 */
@Component
@Mapper
public interface LoginMapping {

    /**
     * 注册
     * @param user
     */
    @Insert("insert into user (uname,upwd) values (#{user.uname},#{user.upwd});")
    void register(@Param("user") User user);

    /**
     * 登录
     * @param uname
     * @return
     */
    @Select("select * from user where uname=#{uname}")
    User findByName(String uname);

}
