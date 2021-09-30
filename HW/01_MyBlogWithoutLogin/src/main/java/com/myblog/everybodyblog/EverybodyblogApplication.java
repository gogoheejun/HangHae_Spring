package com.myblog.everybodyblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class EverybodyblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(EverybodyblogApplication.class, args);

    }
//    @PostConstruct
//    void started() {
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
//    }

}
