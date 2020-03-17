package com.pjqdyd.job;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.util.Date;

/**   
 * @Description:  [要执行的任务]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
public class HelloJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //创建工作详情
        JobDetail detail = jobExecutionContext.getJobDetail();
        //获取工作的相关信息
        String name = detail.getKey().getName();
        String group = detail.getKey().getGroup();
        String job = detail.getJobDataMap().getString("data1");
        System.out.println("job执行--> job名:" + name + " group组:" + group + " dada数据:" + job + " 日期:" + new Date());
    }
}
