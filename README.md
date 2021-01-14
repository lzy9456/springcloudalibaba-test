# springcloud alibaba - helloworld项目

## 技术栈目录

* **Mybatis plus3.4.0**
* **参数验证**
* **全局异常处理**
* **统一响应前端封装对象Result**



* **spring-boot-dependencies - 2.3.2.RELEASE**
* **spring-cloud-dependencies - Hoxton.SR8**
* **spring-cloud-alibaba-dependencies - 2.2.3.RELEASE**



* **Dubbo** (spring-cloud-starter-dubbo2.2.3 - dubbo2.7.8 分布式服务调用)
* **Nacos** (spring-cloud-starter-alibaba-nacos-discovery2.2.3 - nacos1.3.3, discovery|config 分布式服务注册、配置)
* **Seata** (spring-cloud-starter-alibaba-seata2.2.3 - seata1.3.0分布式事务)
* **Sentinel** (spring-cloud-starter-alibaba-sentinel2.2.3 - sentinel1.8.0, 分布式流控、高可用防护)
* [**RocketMQ**] (spring-cloud-starter-stream-rocketmq2.2.3 - rocketmq4.4.0, 分布式消息队列)
* **Gateway** (spring-cloud-starter-gateway2.2.5 - gateway2.2.5 分布式网关)


* **Lombok**
* [**Zookeeper**] 分布式服务注册备选
* [**Websocket**]
* [**Alibaba Java Code Guidelines**] 阿里代码规范插件
* [**Websocket**]

## Quick Start

1. 启动server
   * nacos-server1.3.2(8848)
   * seata-server1.4.0(8091)
   * sentinel-dashboard1.8.0(8858)

     > nacos\bin下新建sca.nacos-server1.3.2-start8848.bat \
     shutdown.cmd && start startup -m standalone
     
     > seata-server-1.4.0目录新建sca.seata.server.start8091.bat \
     md logs \
     bin\seata-server -p 8091 -h 127.0.0.1 -m file

     > sentinel-dashboard目录新建sca.sentinel-dashboard.start8858.bat \
     java -Dserver.port=8858 -jar sentinel-dashboard-1.8.0.jar

2. 启动项目
   * dubbo-consumer，配置启动jvm参数(sentinel参数)
     > -Djava.net.preferIPv4Stack=true -Dcsp.sentinel.api.port=8721 -Dcsp.sentinel.dashboard.server=localhost:8858 -Dproject.name=dubbo-consumer
   * dubbo-provider
     > -Djava.net.preferIPv4Stack=true -Dcsp.sentinel.api.port=8721 -Dcsp.sentinel.dashboard.server=localhost:8858 -Dproject.name=dubbo-provider 

   * 依次启动dubbo-provider、dubbo-consumer、springcloud-gateway
     > 

3. 访问接口 <http://localhost:8081/say>





## 介绍
### Mybatis plus3.4.0
* **集成Mybatis plus**
  
    1. pom引入依赖包(mybatis-plus-boot-starter,[mybatis-plus-generator、velocity-engine-core])
    
* **generator代码生成**
    1. 修改对应路径，生成代码；src/test/java/my/dubbo/provider/mybatisplus/generator/MybatisPlusMysqlGenerator.java

* **继承扩展mapper、xml**
    1. 代码生成到test下auto包内，mapper、xml复制覆盖项目dao包下auto包，扩展dao下继承扩展mapper类、xml写自定义查询方法，避免修改表重新生成覆盖影响

### 参数验证
    演示项目：dubbo-consumer

* **集成validation**
> springboot2.3.2没集成validation，手动引入包

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

* **添加验证注解**
  
    1. 类上加@Validated注解启用验证
       >demo类：org.dubbo.consumer.controller.UserController
    2. 方法参数，加@Valid注解启用验证,再添加相应验证注解@NotBlank(message = "id不能为空")、@min()
       >demo方法：org.dubbo.consumer.controller.UserController.sayHello()
    3. 方法参数对象，加@Valid注解启用验证,再对象属性上添加相应验证注解@NotBlank(message = "id不能为空")、@min()
       >demo方法：org.dubbo.consumer.controller.UserController.getUser()


### 全局异常处理
> src/main/java/org/dubbo/global
* controller之前异常404、500 - src/main/java/org/dubbo/global/MyBasicErrorController.java
* controller中异常（自定义异常、未捕获异常） - src/main/java/org/dubbo/global/GlobalExceptionHandler.java
* 其他，sentinel fallback等降级异常处理


### 统一响应前端封装对象Result
> com.example.entity.result.Result
* 异常报错返回
  > org.dubbo.global.GlobalExceptionHandler.constraintViolationException()
* 请求成功、失败返回
  > org.dubbo.consumer.controller.UserController.sayHello()


### 事务处理
> src/main/java/org/dubbo/global
* **本地事务**
  
  1. @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
  2. 调用同类方法事务是否生效？ @Autowired注入引用自己；AopContext；ApplicationContext;
  3. 同一数据库更新操作是否聚合？还是分开？看执行是否耗时，超过多少分开


* **分布式事务seata**
    > 参考后续seata介绍

### Dubbo
    演示项目：dubbo-consumer(消费方)、dubbo-provider(提供方)
1. 消费、提供方都引入依赖（同时引入注册中心依赖 - 目前推荐nacos）
   
        <dependency>
           <groupId>com.alibaba.cloud</groupId>
           <artifactId>spring-cloud-starter-dubbo</artifactId>
        </dependency>

2. 配置
    > dubbo-provider项目：src/main/resources/application.yml - #dubbo配置nacos
 
    > dubbo-consumer项目：src/main/resources/application.yml - #dubbo
3. 注解
    > 1. 启动类加@EnableAutoConfiguration自动启用
   
    > 2. dubbo-provider: dubbo服务类上添加注解 - @DubboService(version = "1.0.0")
    * 参考my.dubbo.provider.service.impl.UserServiceImpl

    > 3. dubbo-consumer：注入dubbo服务类 - @DubboReference(version = "1.0.0")
    * 参考org.dubbo.consumer.service.impl.UserMgrServiceImpl.IUserService
    * 参考org.dubbo.consumer.service.impl.UserMgrServiceImpl.IRoleService



### Nacos discovery
    演示项目：dubbo-consumer(消费方)、dubbo-provider(提供方)
    控制台: http://localhost:8848/
    nacos\nacos
1. 引入依赖（消费、提供方相同）（同时引入注册中心依赖 - 目前推荐nacos）

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

2. 配置（消费、提供方相同）
    > 参考dubbo-consumer项目：src/main/resources/application.yml - 复制spring: cloud：nacos：

3. 注解
    > 1. 启动类加@EnableAutoConfiguration自动启用


### Nacos config
    演示项目：springcloud-gateway
1. 引入依赖

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>

2. 配置
   > springcloud-gateway项目，新建bootstrap.yml
   * Namespace
   * Group
   * Data Id -(${prefix}-${spring.profile.active}.${file-extension})

3. 注解
   > 1. @RefreshScope自动刷新


### Seata
1. 集成seata

    * 引入seata依赖包

           <dependency>
             <groupId>com.alibaba.cloud</groupId>
             <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
           </dependency>

    * 配置文件配置连接seata
      src/main/resources/application.properties
    * @GlobalTransactional
      org.dubbo.consumer.service.impl.UserMgrServiceImpl.updateUser()

2. 聚合在consumer - service调用其他多个更新服务，以便使用事务注解@GlobalTransactional



### Sentinel
    演示项目：dubbo-consumer(消费方)、dubbo-provider(提供方)
    控制台: http://localhost:8858/#/login
    sentinel\sentinel
1. 消费、提供方都引入依赖（同时引入注册中心依赖 - 目前推荐nacos）

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>

2. 下载sentinel-server.jar，启动
   > * jar目录新建文件start.bat
   > * 编辑复制保存：java -Dserver.port=8858 -jar sentinel-dashboard-1.8.0.jar
   > * 双击start.bat启动
3. idea、eclipse等添加项目启动jvm参数（指定连接sentinel-server端口等）
   > -Dserver.port=8082可去掉，项目启动多个测试，指定端口用
   > 
   > -Dserver.port=8082 -Djava.net.preferIPv4Stack=true -Dcsp.sentinel.api.port=8721 -Dcsp.sentinel.dashboard.server=localhost:8858 -Dproject.name=dubbo-consumer002
   
4. 启动项目，访问接口后，接口信息推送到sentinel
    > 如访问UserController.say()，

### Gateway
    演示项目：dubbo-consumer(消费方)、dubbo-provider(提供方)
    网关：http://localhost:8190/say
1. 引入依赖（同时引入注册中心依赖 - 目前推荐nacos）

         <!-- spring cloud alibaba nacos discovery -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
   
        <!-- spring cloud alibaba nacos discovery -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

2. 配置
   > src/main/resources/application.yml
   > 
   > nacos配置
   > 
   > gateway配置
   >    * routes配置：
   >        1. 如拦截访问路径-Path：/say/**，转到uri：http://localhost:8081
   >        2. predicates内置有十多种拦截匹配规则：path，after，between，...
   >        3. filter内置几十种拦截过滤规则：





### RocketMQ
    参考sca官方示例
    演示项目：springcloudalibaba-rocketmq-consume(消费方)、springcloudalibaba-rocketmq-produce(提供方)
    网关：http://localhost:8190/say


1. [下载启动RocketMQ 的 Name Server 和 Broker](https://archive.apache.org/dist/rocketmq/)，并解压
    > 如：rocketmq-all-4.8.0-bin-release.zip



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










### Websocket
> src/main/java/org/dubbo/websocket
>
> 注意：nginx需要做一些配置，支持websocket通信。

    proxy_http_version 1.1;
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection "upgrade";