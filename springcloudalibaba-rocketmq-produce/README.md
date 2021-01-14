# springcloudalibaba-rocketmq-produce

## Quick Start


1. [下载启动RocketMQ的 Name Server 和 Broker](https://archive.apache.org/dist/rocketmq/)，并解压
   > 如：rocketmq-all-4.8.0-bin-release.zip
    * Name Server
    * Broker

      > rocketmq-all解压，目录下新建bat文件sca.rocketmq4.8.0[namesrv+broker9876].start.bat
      
            @echo off
            set rockmq_home=%~dp0
            set rockmq_bin=%~dp0bin
            setx ROCKETMQ_HOME %rockmq_home%
            cd /d %rockmq_bin%
            start mqnamesrv.cmd
            start mqbroker.cmd -n localhost:9876

2. 启动项目
    * springcloudalibaba-rocketmq-consume
    * springcloudalibaba-rocketmq-produce

3. 访问接口 <http://localhost:28081/send1>
   springcloudalibaba-rocketmq-produce/src/main/java/org/my/demo/controller/MqTestController.java



### RocketMQ
    参考sca官方示例
    演示项目：springcloudalibaba-rocketmq-consume(消费方)、springcloudalibaba-rocketmq-produce(提供方)
    网关：http://localhost:8190/say





2. 引入依赖

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-rocketmq</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
3. 配置
   * springcloudalibaba-rocketmq-consume
        > src/main/resources/application.yml  #spring.cloud.stream: ...

   * springcloudalibaba-rocketmq-produce
        > src/main/resources/application.yml  #spring.cloud.stream: ...


4. 发送与接收消息
    * springcloudalibaba-rocketmq-consume
        > org.my.demo.rockmq.MqReceiveService

    * springcloudalibaba-rocketmq-produce
        > org.my.demo.rocketmq.SenderService


