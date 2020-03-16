package com.pjqdyd.simple;

import com.pjqdyd.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**   
 * @Description:  [Quartz基本使用, 简单触发器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class SimpleQuartz {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //创建scheduler调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义一个Trigger触发条件类
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1" , "group1")
                .startNow()     //触发器立即开始 .startAt()定义开始日期
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3) //隔3秒钟, 立即开始1次,循环2次
                        .withRepeatCount(2)
                        //.repeatForever()  //一直循环
                )
                .endAt(new GregorianCalendar(2020,3,15, 16, 3, 0).getTime()) //结束日期
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
        TimeUnit.SECONDS.sleep(6);
        scheduler.shutdown();
    }

}
