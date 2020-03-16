package com.pjqdyd.calendar;

import com.pjqdyd.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**   
 * @Description:  [Quartz基本使用, 日历触发器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class CalendarQuartz {

    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {
        //创建scheduler调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义一个Trigger触发条件类
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2" , "group2")
                .startNow()
                .withSchedule(
                        CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                        //.withIntervalInDays(2)  //每2天执行一次
                        .withIntervalInSeconds(3) //每3秒执行一次
                )
                .endAt(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse("2020.04.18 15:38:59"))
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
