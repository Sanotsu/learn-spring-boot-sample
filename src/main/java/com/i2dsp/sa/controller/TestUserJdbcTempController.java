package com.i2dsp.sa.controller;

import com.i2dsp.sa.entity.TestUser;
import com.i2dsp.sa.entity.User;
import com.i2dsp.sa.service.UserJdbcTempService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户相关接口")  // 用来标记当前Controller的功能
@RequestMapping("/user")
//@RequiredArgsConstructor
public class TestUserJdbcTempController {

    /**
     * 使用lombok插件的写法
     * 注意除了pom.xml依赖要添加之外,idea编译器也要添加此插件,以便识别
     *
     * 在类上也要注解@RequiredArgsConstructor
     */
//    @NonNull
//    private UserService userService;

    /**
     * 自动装配的一般写法
     */
    private final UserJdbcTempService userJdbcTempService;

    @Autowired
    public TestUserJdbcTempController(UserJdbcTempService userJdbcTempService) {
        this.userJdbcTempService = userJdbcTempService;
    }

    @PostMapping("/")
    @ApiOperation("添加用户的接口") // 用来标记一个方法的作用
    @ApiImplicitParams({     // 多个参数需要放到此里面
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "张三"),   // 用来描述一个参数
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "重庆", required = true) // ApiImplicatiParam的必填,只是Swagger框架的内容必填,抛开框架没有任何意义
    })
    //  @RequestParam(required = true)的参数必填是数据操作时的必填,和Swagger框架也没有关系
    public void insertUser(String username, @RequestParam(required = true) String address) {
        System.out.println("test add user:  " + username + "  " + address);
    }

    @PutMapping("/{id}")
    @ApiOperation("根据用户ID更新用户的接口")
    public TestUser updateUserById(@RequestBody TestUser user) {
        System.out.println(user.getAddress());
        return user;
    }

    /*******************************************
     * 以下是测试使用jdbcTemplate的接口,都是写死的数据,浏览器(postman等工具)访问对应接口测试就好
     *******************************************/

    @GetMapping("/addUser")
    @ApiOperation("添加用户的接口") // 用来标记一个方法的作用
    public String addUser() {

        User user = new User();
        user.setUserCode("david2");
        user.setUsername("大卫科波菲尔");
        user.setPassword("123456");
        user.setCrtUsrCode("admin");

        int ret = userJdbcTempService.addUser(user);

        if (ret == 69) {
            return "add fail";
        } else {
            return "add success number count :" + ret;
        }
    }

    @GetMapping("/updateUser")
    @ApiOperation("指定Id修改用户的接口") // 用来标记一个方法的作用
    public String updateUser() {

        User user = new User();
        user.setUsername("大卫芬奇");
        user.setPassword("11111");
        user.setCrtUsrCode("admin");
        user.setUserId(1);

        int ret = userJdbcTempService.updateUserById(user);

        if (ret == 69) {
            return "update fail";
        } else {
            return "update success number count :" + ret;
        }
    }

    @GetMapping("/deleteUser")
    @ApiOperation("指定Id删除用户的接口")
    public String deleteUser() {

        User user = new User();
        user.setUserId(15);

        int ret = userJdbcTempService.deleteUserById(user);

        if (ret == 69) {
            return "delete fail";
        } else {
            return "delete success number count :" + ret;
        }
    }

    @GetMapping("/getUsers")
    @ApiOperation("查询所有用户")
    public List<User> getUsers() {
        return userJdbcTempService.getAllUsers();
    }

}
