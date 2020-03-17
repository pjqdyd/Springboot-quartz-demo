package com.pjqdyd.cron;

import com.pjqdyd.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**   
 * @Description:  [Quartz基本使用, Cron表达式触发器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class CronQuartz {

    public static void main(String[] args) throws SchedulerException {
        //创建scheduler调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义一个Trigger触发条件类
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger4" , "group4")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("* * * * * ?") //每天的每一秒执行一次
                        //CronScheduleBuilder.cronSchedule("0 0/2 10-12 * * ?") //每天的10-12点,隔2秒执行一次
                )
                .build();
        //创建一个任务详情类(封装job)
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "job-group1")
                .usingJobData("data1", "hello world!")
                .build();
        //调度器中加入触发器和任务
        scheduler.scheduleJob(jobDetail, trigger);
        //启动调度器, 内部的触发器开始计时
        scheduler.start();
        //关闭调度器(这里虽然主线程在阻塞, 但触发器任务是在新线程中异步执行的)
        //TimeUnit.SECONDS.sleep(6);
        //scheduler.shutdown();
    }

}
