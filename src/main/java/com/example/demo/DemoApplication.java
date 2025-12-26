package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.example.demo.servlet.SimpleStatusServlet;

@SpringBootApplication
public class DemoApplication {
    
    public static void main(String[] args) {
        try {
            SpringApplication.run(DemoApplication.class, args);
            System.out.println("Application started successfully on port 9001");
            System.out.println("Swagger UI: http://localhost:9001/swagger-ui/index.html");
            System.out.println("API Docs: http://localhost:9001/v3/api-docs");
            System.out.println("Servlet Status: http://localhost:9001/simple-status");
        } catch (Exception e) {
            System.err.println("Failed to start application: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    @Bean
    public ServletRegistrationBean<SimpleStatusServlet> simpleStatusServlet() {
        ServletRegistrationBean<SimpleStatusServlet> bean = new ServletRegistrationBean<>(
            new SimpleStatusServlet(), "/simple-status"
        );
        bean.setLoadOnStartup(1);
        return bean;
    }
}