package com.sucre.cool.mywebsite.config;

import com.sucre.cool.mywebsite.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/weiboimg/**")
                .addResourceLocations("classpath:/static/weiboimg/");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui.html**",
                        "/webjars/**",
                        "/v2/**",
                        "/swagger-resources/**",
                        "/api-docs/**",
                        "/userDO/login",
                        "/common/**",
                        "/weiboimg/**"
                );
        super.addInterceptors(registry);
    }
}
