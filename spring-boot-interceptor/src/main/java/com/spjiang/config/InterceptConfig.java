package com.spjiang.config;

import com.spjiang.interceptions.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Package: com.spjiang.config
 *
 * @description:
 *
 * extends WebMvcConfigurerAdapter
 * implements WebMvcConfigurer
 *
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-15 11:14
 */
@Component
public class InterceptConfig extends WebMvcConfigurationSupport {
    @Autowired
    private MyInterceptor myInterceptor;

    // 参数1，InterceptorRegistry，拦截器注册对象

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/file/**")
                .excludePathPatterns("/file/upload");
        //最后将register往这里塞进去就可以了
        // super.addInterceptors(registry);
    }

}
