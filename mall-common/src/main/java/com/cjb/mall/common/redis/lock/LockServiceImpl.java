package com.cjb.mall.common.redis.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenjiabao on 2020/1/1.
 */
@Component
public class LockServiceImpl implements LockService{

    @Autowired
    RedissonClient client;

    private final ConcurrentHashMap<String,RLock> lockMap = new ConcurrentHashMap<>();

    @Override
    public boolean tryLock(String lockId) throws InterruptedException {
        if(lockMap.containsKey(lockId)){
            return lockMap.get(lockId).tryLock(5, 10, TimeUnit.SECONDS);
        }
        RLock lock = client.getLock(lockId);
        lockMap.put(lockId,lock);
        return lock.tryLock(5, 10, TimeUnit.SECONDS);
    }

    @Override
    public void unLock(String lockId) {
        if(lockMap.containsKey(lockId)){
            RLock rLock = lockMap.get(lockId);
            if(rLock.isLocked()){
                if(rLock.isHeldByCurrentThread()){
                    rLock.unlock();
                }
            }
        }
    }
}


