package com.jsf2184.springsecurityjdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityJdbcApplication {

    public static Logger _log = LoggerFactory.getLogger(SpringSecurityJdbcApplication.class);

    public static void main(String[] args) {
        Simple simple = new Simple("abc");
        String data = simple.getData();
        _log.info("about to start SpringApplication.run() with data = {}", data);

        SpringApplication.run(SpringSecurityJdbcApplication.class, args);
    }

}
