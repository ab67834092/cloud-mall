package com.cjb.mall.elastic.job.task;


import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import org.springframework.stereotype.Component;

//@ElasticSimpleJob(cron = "0/3 * * * * ?", jobName = "Job2", shardingTotalCount = 2, jobParameter = "测试参数", shardingItemParameters = "0=A,1=B")
@ElasticSimpleJob(cron = "0/3 * * * * ?", jobName = "Job2", shardingTotalCount = 1)
@Component
public class Job2 implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(String.format("------Thread ID: %s, 任务总片数: %s, " +
                        "当前分片项: %s.当前参数: %s," +
                        "当前任务名称: %s.当前任务参数: %s"
                ,
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()
        ));
    }
}
