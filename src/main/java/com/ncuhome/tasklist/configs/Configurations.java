package com.ncuhome.tasklist.configs;


import com.ncuhome.tasklist.dataobject.User;
import com.ncuhome.tasklist.service.UserProvider;
import com.ncuhome.tasklist.service.UserService;
import com.ncuhome.tasklist.util.AuthInterceptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;


@Configuration
@Log4j2
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
        registry.addInterceptor(getAuthInterceptor()).addPathPatterns("/**");;
    }


    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public User currentUser(HttpServletRequest httpServletRequest, UserService userService){
        String token = httpServletRequest.getHeader("Authorization");
        return userService.verifyToken(token);
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
