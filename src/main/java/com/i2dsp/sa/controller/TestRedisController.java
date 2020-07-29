package com.i2dsp.sa.controller;

import com.i2dsp.sa.service.TestRedisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRedisController {

    final
    TestRedisService testRedisService;

    public TestRedisController(TestRedisService testRedisService) {
        this.testRedisService = testRedisService;
    }

    @GetMapping("/testRedis")
    public String testRedis() {
        testRedisService.helloRedis();

        testRedisService.helloRedis2();

        return "redis test ok";

    }
}
