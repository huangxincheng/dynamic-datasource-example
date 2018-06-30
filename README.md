# dynamic-datasource-example
动态切换数据源例子


Pom.xml
        
        可以从这里https://github.com/huangxincheng/dynamic-spring-boot-starter下载打包
        <!--增加dynamic-spring-boot-starter的依赖-->
        <dependency>
            <groupId>com.qy</groupId>
            <artifactId>dynamic-spring-boot-starter</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!--增加mybatis-spring-boot-starter官方整合的依赖-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis-springboot-starter.version}</version>
        </dependency>
        
application.properties

    # 配置
    spring.application.name=demo

    # 配置Mybatis
    mybatis.mapper-locations=classpath:mapper/**/*.xml
    mybatis.type-aliases-package=com.qs.domain
    # 这里必须配置QyDataSource
    spring.datasource.type=com.qy.dynamic.spring.boot.autoconfigure.QyDataSource
    # 声明源数据源使用Druid
    spring.datasource.qy.source-class-name=com.alibaba.druid.pool.DruidDataSource
    # 声明默认的数据源Key为h2
    spring.datasource.qy.default-source-key=h2

    # 这里配置参考Druid
    spring.datasource.qy.druid.druid-data-source-map.h1.url=jdbc:mysql://mailou.com:3306/springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false
    spring.datasource.qy.druid.druid-data-source-map.h1.username=hxc
    spring.datasource.qy.druid.druid-data-source-map.h1.password=hxc
    spring.datasource.qy.druid.druid-data-source-map.h1.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.qy.druid.druid-data-source-map.h1.min-idle=1
    spring.datasource.qy.druid.druid-data-source-map.h1.max-active=100

    spring.datasource.qy.druid.druid-data-source-map.h2.url=jdbc:mysql://mailou.com:3306/springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false
    spring.datasource.qy.druid.druid-data-source-map.h2.username=hxc
    spring.datasource.qy.druid.druid-data-source-map.h2.password=hxc
    spring.datasource.qy.druid.druid-data-source-map.h2.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.qy.druid.druid-data-source-map.h2.min-idle=2
    spring.datasource.qy.druid.druid-data-source-map.h2.max-active=50

    # 配置Druid监控后台账号密码
    spring.datasource.druid.stat-view-servlet.login-username=hxc
    spring.datasource.druid.stat-view-servlet.login-password=hxc
    
  DynamicDatasouceExampleApplication.java
  
    @SpringBootApplication
    @MapperScan(basePackages = {"com.qs.mapper"})
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    // 必须增加扫描com.qy 否则QyDataSource Aop无效
    @ComponentScans(value = {@ComponentScan("com.qy")})
    public class DynamicDatasouceExampleApplication {

        public static void main(String[] args) {
            SpringApplication.run(DynamicDatasouceExampleApplication.class, args);
        }
    }
