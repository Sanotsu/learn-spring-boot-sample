package com.i2dsp.sa.controller;

import com.i2dsp.sa.entity.User;
import com.i2dsp.sa.mybatismapper.UserMapper;
import com.i2dsp.sa.mybatismapper.UserXmlMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "mybatis测试用户相关接口")  // 用来标记当前Controller的功能
@RequestMapping("/mybatisUser")
@RequiredArgsConstructor
public class TestUserMybatisController {

    // 注解配置的mapper
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // xml配置的mapper
    UserXmlMapper userXmlMapper;

    @Autowired
    public void setUserXmlMapper(UserXmlMapper userXmlMapper) {
        this.userXmlMapper = userXmlMapper;
    }

    @GetMapping("/getUsers")
    @ApiOperation("查询所有用户")
    public List<User> getUsers() {
//        List<User> ret = userMapper.getAllUsers(); // mybatis 注解配置
        List<User> ret = userXmlMapper.getAllUsers(); // mybatis xml配置
        System.out.println(ret.toString());
        return ret;
    }

    @GetMapping("/getUserById")
    @ApiOperation("通过Id查询指定用户")
    public User getUserById(@RequestParam Long userId) {
        User ret = userMapper.getUserById(userId);  // mybatis 注解配置
        System.out.println(ret);
        return ret;
    }

    @PostMapping("/addUser")
    @ApiOperation("新增用户")
    public int addUser(@RequestBody User user) {
        int ret = userMapper.addUser(user);
        System.out.println(ret);
        return ret;
    }

    @GetMapping("/getUserByName")
    @ApiOperation("通过用户名查找用户")
    public List<User> getUsersByName(@RequestParam String username) {
        List<User> ret = userMapper.getUsersByName(username);
        System.out.println(ret);
        return ret;
    }

    @PostMapping("/updateUserById")
    @ApiOperation("通过id修改用户")
    public int updateUserById(@RequestBody User user) {
//        int ret = userMapper.updateUserById(user);  // mybatis 注解配置
        int ret = userXmlMapper.updateUserById(user);  // mybatis xml配置
        System.out.println(ret);
        return ret;
    }

    @GetMapping("/deleteUserById")
    @ApiOperation("通过id删除用户")
    public int deleteUserById(@RequestParam Integer userId) {
//        int ret = userMapper.deleteUserById(userId); // 注解配置
        int ret = userXmlMapper.deleteUserById(userId); // xml配置

        System.out.println(ret);
        return ret;
    }


}
