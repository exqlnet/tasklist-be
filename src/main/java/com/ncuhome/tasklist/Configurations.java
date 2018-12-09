package com.ncuhome.tasklist;


import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.service.UserProvider;
import com.ncuhome.tasklist.util.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;


@Configuration
public class Configurations implements WebMvcConfigurer {




    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration =new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1
        corsConfiguration.addAllowedHeader("*"); // 2
        corsConfiguration.addAllowedMethod("*"); // 3
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }

    @Bean
    AuthInterceptor getAuthInterceptor(){
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthInterceptor());
    }


//    @Bean
//    public UserProvider userProvider(HttpServletRequest httpServletRequest){
//
//        return new UserProvider() {
//            @Override
//            public User getUser() {
//                return new User();
//            }
//        };
//
//    }
}
