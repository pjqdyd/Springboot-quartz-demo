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
                .withIdentity("job2", "job-group2")
                .usingJobData("data1", "hello world!")
                .storeDurably()
                .build();
    }
    @Bean
    public Trigger quartzTrigger(){
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity("tirgger2", "group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .build();
    }

}
