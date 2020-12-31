package org.dubbo;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author _lizy
 * @version 1.0
 * @description web启动
 * @date 2020/10/18 8:44
 */
@Aspect
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo
@EnableWebSocket
@EnableWebMvc
@ComponentScan("org.dubbo")
public class DubboConsumerWebApp extends SpringBootServletInitializer implements WebApplicationInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(getClass());
    }

    public static void main(String[] args) { // Run as the generic Spring Boot Web(Servlet) Application
        SpringApplication application = new SpringApplication(DubboConsumerWebApp.class);
        application.setWebApplicationType(WebApplicationType.SERVLET);
        application.run(args);
    }
}
