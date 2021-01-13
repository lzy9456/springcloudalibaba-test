package org.my.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@RefreshScope
@EnableAutoConfiguration
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class);
        System.out.println(name);
        System.out.println(pwd);
    }


    /**
     * 默认本工程共享配置(dev、test、prod等共享) [spring.application.name].yaml --> springcloud-gateway.yaml
     *
     * 共享配置,可用于所有项目全局配置 - user.pwd
     */
    private static String name; //
    @Value("${user.name}")
    public void setName(String name) {
        GateWayApplication.name = name;
    }

    private static String pwd;
    @Value("${user.pwd}")
    public void setPwd(String pwd) {
        GateWayApplication.pwd = pwd;
    }
}
