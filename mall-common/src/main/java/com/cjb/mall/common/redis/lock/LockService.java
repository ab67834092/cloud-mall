package com.cjb.mall.common.redis.lock;

/**
 * Created by chenjiabao on 2020/1/1.
 */
public interface LockService{

    public boolean tryLock(String lockId) throws InterruptedException;

    public void unLock(String lockId);
}
