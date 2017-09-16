package com.cjw.demo.doc.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisDataSourceProvider implements RedisDataSource {

    private static final Logger logger = LoggerFactory.getLogger(RedisDataSourceProvider.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    public ShardedJedis getRedisClient() {
        try {
            ShardedJedis jedis = shardedJedisPool.getResource();
            return jedis;
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }
        return null;
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis) {
        try {
            shardedJedisPool.returnResourceObject(shardedJedis);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }

    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }
}
