package com.i2dsp.sa.redistest.hyperloglog;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.Data;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 使用HyperLogLog统计UV的案例#
 * 假设现在有个简单的场景，就是统计博客文章的UV，要求UV的计数不需要准确，也不需要保存客户端的IP数据。
 * 下面就这个场景，使用HyperLogLog做一个简单的方案和编码实施。
 */

public class UvTest {

    private static RedisCommands<String, String> COMMANDS;

    @BeforeClass
    public static void beforeClass() throws Exception {
        // 初始化Redis客户端
        RedisURI uri = RedisURI.builder().withHost("192.168.28.55").withPort(6379).withPassword("123456").build();
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        COMMANDS = connect.sync();
    }

    @Data
    public static class PostDetail {

        private Long id;
        private String content;
    }

    private PostDetail selectPostDetail(Long id) {
        PostDetail detail = new PostDetail();
        detail.setContent("content");
        detail.setId(id);
        return detail;
    }

    private PostDetail getPostDetail(String clientIp, Long postId) {
        PostDetail detail = selectPostDetail(postId);
        String key = "puv:" + postId;
        COMMANDS.pfadd(key, clientIp);
        return detail;
    }

    private Long getPostUv(Long postId) {
        String key = "puv:" + postId;
        return COMMANDS.pfcount(key);
    }

    @Test
    public void testViewPost() throws Exception {
        Long postId = 1L;
        getPostDetail("111.111.111.111", postId);
        getPostDetail("111.111.111.222", postId);
        getPostDetail("111.111.111.333", postId);
        getPostDetail("111.111.111.444", postId);
        System.out.println(String.format("The uv count of post [%d] is %d", postId, getPostUv(postId)));
    }
}