package com.pjqdyd;

import org.quartz.*;

import java.util.Date;

/**   
 * @Description:  [Job类, 任务类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

//@DisallowConcurrentExecution //不允许并发执行
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //创建工作详情
        JobDetail detail = jobExecutionContext.getJobDetail();
        //获取工作的相关信息
        String name = detail.getKey().getName();
        String group = detail.getKey().getGroup();
        String job = detail.getJobDataMap().getString("data1");
        System.out.println("job执行--> job名:" + name + " group组:" + group + " dada数据:" + job + " 日期:" + new Date());
    }
}
