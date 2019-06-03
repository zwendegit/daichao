package com.daichao.common;

import com.daichao.utils.StringUtil;
import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * Created by hook on 2017/8/26.
 * <p>
 * Redis缓存
 */
public class SimpleRedisCache<T extends Serializable> {
    private static final Logger logger = LoggerFactory.getLogger(SimpleRedisCache.class);

    private static final Integer DEF_MAX_TOTAL = 300;
    private static final Integer DEF_MAX_IDLE = 200;
    private static final Long DEF_MAX_WAIT_MILLIS = 10000L;
    private static final Boolean DEF_TEST_ON_BORROW = false;
    private static final Boolean DEF_TEST_ON_RETURN = false;

    private static final String DEF_CHARSET = "utf-8";

    /**
     * 这些参数不填则用默认值
     */
    private Integer maxTotal;
    private Integer maxIdle;
    private Long maxWaitMillis;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;

    /**
     * 以下几个参数必填（如果没有密码可以不要）
     */
    private String host;
    private Integer port;
    private String password;
    private Integer timeout;

    private JedisPool pool;

    private Boolean inited = false;

    public void init() {
        if (!inited) {
            pool = StringUtil.isBlank(password) ? new JedisPool(getJedisConfig(), host, port, timeout)
                    : new JedisPool(getJedisConfig(), host, port, timeout, password);
            inited = true;
        }
    }

    private JedisPoolConfig getJedisConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal == null ? DEF_MAX_TOTAL : maxTotal);
        config.setMaxIdle(maxIdle == null ? DEF_MAX_IDLE : maxIdle);
        config.setMaxWaitMillis(maxWaitMillis == null ? DEF_MAX_WAIT_MILLIS : maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow == null ? DEF_TEST_ON_BORROW : testOnBorrow);
        config.setTestOnReturn(testOnReturn == null ? DEF_TEST_ON_RETURN : testOnReturn);
        return config;
    }

    public void destroy() {
        if (inited && pool != null) {
            pool.destroy();
            inited = false;
        }
    }

    public void put(String key, T val) {
        Assert.isTrue(inited, "Cache not inited");
        Assert.notNull("key is blank.", key);
        Assert.notNull(val, "value is null");

        Jedis jedis = pool.getResource();
        try {
            jedis.set(key.getBytes(DEF_CHARSET), SerializationUtils.serialize(val));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        jedis.close();
    }

    public void put(String key, T val, int expiredTime) {
        Assert.isTrue(inited, "Cache not inited");
        Assert.notNull("key is blank.", key);
        Assert.notNull(val, "value is null");

        Jedis jedis = pool.getResource();
        try {
            jedis.setex(key.getBytes(DEF_CHARSET), expiredTime, SerializationUtils.serialize(val));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        jedis.close();
    }

    public T get(String key) {
        Assert.isTrue(inited, "Cache not inited");
        Assert.notNull("key is blank.", key);

        Jedis jedis = pool.getResource();
        byte[] rtnBytes = null;
        try {
            rtnBytes = jedis.get(key.getBytes(DEF_CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (rtnBytes == null) {
            jedis.close();
            return null;
        }

        Serializable rtn = (Serializable) SerializationUtils.deserialize(rtnBytes);
        jedis.close();
        //noinspection unchecked
        return (T) rtn;
    }


    public void remove(String key) {
        Assert.isTrue(inited, "Cache not inited");
        Assert.notNull("key is blank.", key);

        Jedis jedis = pool.getResource();
        jedis.del(key);
        jedis.close();
    }

    public void expire(String key, int expiredTime) {
        Assert.isTrue(inited, "Cache not inited");
        Assert.notNull("key is blank.", key);

        Jedis jedis = pool.getResource();
        jedis.expire(key, expiredTime);
        jedis.close();
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public JedisPool getPool() {
        return pool;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    public Boolean getInited() {
        return inited;
    }

    public void setInited(Boolean inited) {
        this.inited = inited;
    }
}
