package com.pjqdyd.config;

import com.pjqdyd.job.HelloJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**   
 * @Description:  [定时任务配置]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "job-group1")
                .usingJobData("data1", "hello world!")
                .storeDurably()
                .build();
    }
    @Bean
    public SimpleTrigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2) //间隔2秒执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity("tirgger1", "group1")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
