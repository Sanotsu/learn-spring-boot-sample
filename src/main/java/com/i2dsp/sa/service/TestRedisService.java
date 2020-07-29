package com.i2dsp.sa.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;


/**
 * redis中的数据操作大体分为两种
 * 1 针对key的操作,相关方法在RedisTemplate中
 * 2 针对具体数据类型的操作,相关方法需要首先获取对应的数据类型,获取数据类型的操作方法是opsForXXX
 */

@Service
@RequiredArgsConstructor  // 配合NonNull,需要参数构造器
public class TestRedisService {
    @NonNull
//    RedisTemplate redisTemplate;
    RedisTemplate<String, String> redisTemplate; // 直接泛型，指定传入的类型为String,也能避免出现序列化时的前缀和乱码。

    @NonNull
    StringRedisTemplate stringRedisTemplate;

    public void helloRedis() {
        /**
         *  RedisTemplate中,key默认的序列化方案是 JdkSerializationRedisSerializer,所以可能存入redis中的key有额外前缀
         *  而 StringRedisTemplate中key默认序列化方案是 StringRedisSerializer,默认key前不会有前缀
         *
         * 可以修改 RedisTemplate的序列化方案 或者 直接使用StringRedisTemplate
         *
         */

        // 修改 RedisTemplate的序列化方案
//        redisTemplate.setKeySerializer(new StringRedisSerializer());

        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("k1", "v1");  // redis里面存的值是乱码,可在泛型时指定类型
        Object k1 = ops.get("k1");
        System.out.println(k1);
    }

    // 直接使用StringRedisTemplate
    public void helloRedis2() {

        ValueOperations ops = stringRedisTemplate.opsForValue();
        ops.set("k2", "v2");
        Object k1 = ops.get("k2");
        System.out.println(k1);
    }

}