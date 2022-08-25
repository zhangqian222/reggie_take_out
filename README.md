# reggie外卖

## 一.数据库环境配置

## 二.创建springboot项目

添加pom依赖包括druid、mybaits_plus、mysql
```xml

<dependencys>
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.48</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/com.alibaba/druid-spring-boot-starter -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.2.11</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter -->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.5.2</version>
    </dependency>
</dependencys>
```
spirngboot配置文件(yaml格式)
```yaml
# 指定tomcat端口
server:
  port: 8080
spring:
  application:
    # 配置项目名
    name: reggie_take_out
  # 数据源配置
  datasource:
    druid:
      driver-class-name:  com.mysql.jdbc.Driver
      url: jdbc:mysql://localhost:3306/reggie
      username: root
      password: zq
```