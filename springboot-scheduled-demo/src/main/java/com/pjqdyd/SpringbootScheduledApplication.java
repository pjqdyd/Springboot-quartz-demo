package com.pjqdyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**   
 * @Description:  [启动类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@EnableScheduling
@SpringBootApplication
public class SpringbootScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootScheduledApplication.class);
    }

}
