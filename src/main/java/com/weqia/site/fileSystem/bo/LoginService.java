package com.weqia.site.fileSystem.bo;

import com.weqia.site.fileSystem.dao.LoginMapping;
import com.weqia.site.fileSystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

/**
 * 登录注册逻辑层
 */
@Service
@Transactional
public class LoginService {

    @Autowired
    private LoginMapping loginMapping;

    /**
     * 注册
     * @param user
     */
    public void register(User user){
        loginMapping.register(user);
    }

    /**
     * 登录
     * @param uname
     * @return
     */
    public User findByName(String uname){
        return loginMapping.findByName(uname);
    }
}
