package com.weqia.site.fileSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@EnableRedisHttpSession
@SpringBootApplication
public class FilesystemApplication {

    /**
     * cookie序列化设置
     * @return
     */
    @Bean
    public CookieSerializer httpSessionIdResolver(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName("token");
        cookieSerializer.setUseHttpOnlyCookie(false);
        cookieSerializer.setSameSite(null);
        cookieSerializer.setUseBase64Encoding(true);
        return cookieSerializer;
    }

    /**
     * 启动类
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(FilesystemApplication.class, args);
    }

}
