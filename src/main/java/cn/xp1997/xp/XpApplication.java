package cn.xp1997.xp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("cn.xp1997.xp.*")
public class XpApplication {
    public static void main(String[] args) {
        SpringApplication.run(XpApplication.class, args);
    }
}
