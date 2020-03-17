package com.pjqdyd.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**   
 * @Description:  [要执行的定时任务]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Component
public class HelloScheduled {

    @Scheduled(cron = "1/3 * * * * ?")
    public void testScheduled(){
        System.out.println("任务运行:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}
