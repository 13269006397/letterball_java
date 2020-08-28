package com.letterball.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class  RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     *根据key - value插入缓存
     * @param key: 具体的key
     * @param value: 具体的值
     * @param time: 缓存过期时间
     * @param : 缓存过期时间单位  例如天  TimeUnit.DAYS
     * 		TimeUnit.DAYS          //天
     * 		TimeUnit.HOURS         //小时
     * 		TimeUnit.MINUTES       //分钟
     * 		TimeUnit.SECONDS       //秒
     * 		TimeUnit.MILLISECONDS  //毫秒
     * */

    // 缓存时间 单位:秒
    public void setKeyS(String key, Object value, Integer time){
        redisTemplate.opsForValue().set(key, value.toString(), time, TimeUnit.SECONDS);
    }

    //缓存时间 单位:分钟
    public void setKeyM(String key, Object value, Integer time){
        redisTemplate.opsForValue().set(key, value.toString(), time, TimeUnit.MINUTES);
    }

    //缓存时间 单位:小时
    public void setKeyH(String key, Object value, Integer time){
        redisTemplate.opsForValue().set(key, value.toString(), time, TimeUnit.MINUTES.HOURS);
    }

    //缓存时间 单位:天
    public void setKeyD(String key, Object value, Integer time){
        redisTemplate.opsForValue().set(key, value.toString(), time, TimeUnit.MINUTES.DAYS);
    }

    // 缓存list
    public void setKeyList(String key, List list){
        redisTemplate.opsForList().leftPushAll(key, list);
    }

    /**
     *根据key获取缓存
     * */
    public String getKey(String key){
        return redisTemplate.opsForValue().get(key).toString();
    }

    // 获取Redis数据
    public List getValueList(String key){
        return redisTemplate.opsForList().range(key, 0 , -1);
    }



    /**
     * 判断key是否存在
     * */
    public boolean exitst(String key){
        return redisTemplate.hasKey(key);
    }


    /**
     * 根据key删除
     * */
    public void remove(String key){
        if (exitst(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * 缓存value自增
     * */
    public Long increment(String key, Long num){
        return redisTemplate.opsForValue().increment(key, num);
    }

    /**
     *
     * */
    public void setBitmap(String key, Long uid){
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setBit( ( (RedisSerializer< String >)redisTemplate.getKeySerializer() ).serialize( key ), uid, true );
                return true;
            }
        });
        redisTemplate.expire(key, 7, TimeUnit.DAYS);
    }


    /**
     * 统计
     * */
    public Long bitCount(String key){
        Long count = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.bitCount( ( (RedisSerializer< String >)redisTemplate.getKeySerializer() ).serialize( key ));
            }
        });
        return count;
    }
}
