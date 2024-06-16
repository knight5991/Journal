package com.journal.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.journal.controller")
public class SpringMvcConfig implements WebMvcConfigurer {
    //配置静态资源直接访问
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/page/**")
                    .addResourceLocations("/page/");
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("/images/");
            registry.addResourceHandler("/assets/**")
                    .addResourceLocations("/assets/");
            registry.addResourceHandler("/personalHtml/**")
                    .addResourceLocations("/personalHtml/");
    }

}
