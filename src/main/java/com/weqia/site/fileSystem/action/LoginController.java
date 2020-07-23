package com.weqia.site.fileSystem.action;

import com.weqia.site.fileSystem.pojo.User;
import com.weqia.site.fileSystem.bo.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * 登录和注册控制器
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    public HttpSession session;

    /**
     * 注册控制
     * 用户已存在返回0，正常注册返回1，注册异常返回-1
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int register(@RequestBody User user) {
        User user1 = loginService.findByName(user.getUname());
        if (user1 != null) {
            return 0;
        } else {
            try {
                loginService.register(user);
                return 1;
            } catch (Exception e) {
                return -1;
            }
        }
    }

    /**
     * 登录控制,正常返回1，密码错误返回-1,未注册返回0
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public int login(@RequestBody User user) {
        //根据用户名查询用户
        User user1 = loginService.findByName(user.getUname());
        if (user1 != null) {
            //对比查询用户的密码和输入密码
            if (user.getUpwd().equals(user1.getUpwd())) {
                session.setAttribute("user",user1);
                String path = System.getProperty("user.dir");
                File file = new File(path+File.separator+user1.getUid());
                if (!file.exists()){
                    file.mkdir();
                }
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    /**
     * 未登录拦截
     * @return
     */
    @RequestMapping("/unlog")
    public String index(){
        return "unlog";
    }

}
