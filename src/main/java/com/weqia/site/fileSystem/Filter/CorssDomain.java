package com.weqia.site.fileSystem.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 跨域配置
 * Created by admin on 2020/1/10.
 */
@EnableWebMvc
@Configuration
public class CorssDomain implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST","GET","PUT")
                .allowCredentials(true)
                .maxAge(600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginHandlerInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/login/*");
    }

}
