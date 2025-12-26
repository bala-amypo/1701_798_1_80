package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.example.demo.servlet.SimpleStatusServlet;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean<SimpleStatusServlet> statusServlet() {
        ServletRegistrationBean<SimpleStatusServlet> bean = 
            new ServletRegistrationBean<>(new SimpleStatusServlet(), "/status");
        bean.setLoadOnStartup(1);
        return bean;
    }
}