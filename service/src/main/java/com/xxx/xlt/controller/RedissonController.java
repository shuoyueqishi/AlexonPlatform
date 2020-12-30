package com.xxx.xlt.controller;

import com.xxx.xlt.utils.redis.RedisUtil;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisson")
public class RedissonController {
    private static Logger logger = LoggerFactory.getLogger(RedissonController.class);

    private static final String PRODUCT = "MoonCake";
    private static final String AA= "aa";

    private static Integer stock = 100;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/lockAdd")
    public void lockAdd() throws Exception {
        //对数据进行加锁
        RLock lock = redissonClient.getLock(PRODUCT);
        if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
            if (stock > 0) {
                stock -= 1;
                RedisUtil.set(AA,stock,50);
                Integer product = (Integer)RedisUtil.get(AA);
                logger.info("Get from redis, AA="+product);
                logger.info("扣减成功，库存stock：" + stock);
                Thread.sleep(5000);
            } else {
                //没库存
                logger.info("扣减失败，库存不足");
            }
            //解锁
            lock.unlock();
        }
    }
    @GetMapping("/queue/push")
    public void pushQueue() throws Exception {
        RLock writeQueueLock = redissonClient.getLock("writeQueueLock");
        if (writeQueueLock.tryLock(500,TimeUnit.MILLISECONDS)) {
            RQueue<Object> xltQueue = redissonClient.getQueue("xltQueue");
            xltQueue.add("queue body1");
            xltQueue.add("queue body2");
            xltQueue.add("queue body3");
            writeQueueLock.unlock();
        }

    }

    @GetMapping("queue/poll")
    public void pollQueue() throws Exception {
        RLock readQueueLock = redissonClient.getLock("readQueueLock");
        if (readQueueLock.tryLock(1000,TimeUnit.MILLISECONDS)) {
            RQueue<Object> xltQueue = redissonClient.getQueue("xltQueue");
            while (xltQueue.size()>0) {
                String poll = (String)xltQueue.poll();
                logger.info("poll msg from rabbit, msg="+poll);
            }
            readQueueLock.unlock();
        }
    }

    @GetMapping("bloom/filter")
    public void bloomFilter() throws Exception {
        RLock bloomFilterLock = redissonClient.getLock("bloomFilterLock");
        if (bloomFilterLock.tryLock(200,100*1000,TimeUnit.MILLISECONDS)) {
            RBloomFilter<Object> xltRecords = redissonClient.getBloomFilter("xltRecords");
            xltRecords.tryInit(100,0.001);
            int TOTAL=100;
            int cnt = 0;
            for (int i=0;i<TOTAL;i++) {
                xltRecords.add("Article_"+i);
                if (!xltRecords.contains("Article_" + (i-1))) {
                    logger.info("NOT Contains Article_"+i*10);
                    cnt++;
                }
            }
//            xltRecords.delete();
            logger.info("total="+TOTAL+",error cnt="+cnt);
            bloomFilterLock.unlock();
        }
    }
}


