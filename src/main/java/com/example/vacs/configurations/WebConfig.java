//package com.example.vacs.configurations;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Value("${app.storage.path}")
//    String appPath;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//        registry
//                .addResourceHandler("/**")
//                .addResourceLocations("file:" + appPath + "src/main/resources/static/");
//    }
//}
