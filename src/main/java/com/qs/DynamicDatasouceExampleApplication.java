package com.qs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"com.qs.mapper"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
// 必须增加扫描com.qy 否则QyDataSource Aop无效
//@ComponentScans(value = {@ComponentScan("com.qy")})
@ComponentScans(value = {@ComponentScan("com.limaila")})
public class DynamicDatasouceExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasouceExampleApplication.class, args);
    }
}
