package com.zvz.blog;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/*@EnableScheduling *///  开启定时任务
@EnableTransactionManagement   // 开启事务支持
@EnableCaching  // 开启声明式缓存支持
@ServletComponentScan   // 采用原生Servlet3.0的注解进行配置、@WebServlet 、@WebListener、@WebFilter是Servlet3.0 api中提供的注解
@MapperScan(basePackages = "com.zvz.blog.dao")
public class BlogApplication {

    public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
    }

}
