//package com.i2dsp.sa.controller;
//
//import com.i2dsp.sa.entity.User;
//import com.i2dsp.sa.entity.User2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * 注意测试代码和TestUserJdbcTempController冲突,注释掉一个测试另一个
// */
//@RestController
//public class TestUserJdbcTempMultiController {
//
////    @Qualifier("jdbcTemplateOne")
//    JdbcTemplate jdbcTemplateOne;
//
//    @Autowired
//    public void setJdbcTemplateOne(JdbcTemplate jdbcTemplateOne) {
//        this.jdbcTemplateOne = jdbcTemplateOne;
//    }
//
//    @Resource(name = "jdbcTemplateTwo")
//    JdbcTemplate jdbcTemplateTwo;
//
//    @GetMapping("oneUser")
//    public List<User> getAllUser() {
//        List<User> list = jdbcTemplateOne.query("select * from user", new BeanPropertyRowMapper<>(User.class));
//        System.out.println(list.toString());
//
//        return list;
//    }
//
//    @GetMapping("oneUser2")
//    public List<User2> getAllUser2() {
//        List<User2> list = jdbcTemplateTwo.query("select * from user", new BeanPropertyRowMapper<>(User2.class));
//        System.out.println(list.toString());
//
//        return list;
//    }
//
//}
