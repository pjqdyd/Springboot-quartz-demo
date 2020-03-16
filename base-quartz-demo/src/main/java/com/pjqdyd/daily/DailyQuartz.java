package com.pjqdyd.daily;

import com.pjqdyd.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import static org.quartz.DateBuilder.*;

/**   
 * @Description:  [Quartz基本使用, 每日触发器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class DailyQuartz {

    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {
        //创建scheduler调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义一个Trigger触发条件类
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger3" , "group3")
                .withSchedule(
                        DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                        .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 0)) //每天9:00开始
                        .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(18, 0))  //18:00结束
                        .onDaysOfTheWeek(MONDAY, TUESDAY, SATURDAY) //周一, 二, 六执行
                        .withIntervalInHours(1) //间隔1小时
                        .withRepeatCount(99) //最多重复100次
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
        //scheduler.shutdown();
    }

}
