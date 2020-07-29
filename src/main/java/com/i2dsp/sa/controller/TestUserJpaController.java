package com.i2dsp.sa.controller;

import com.i2dsp.sa.dao.jpademo.JpaUserDao;
import com.i2dsp.sa.entity.JpaUser;
import com.i2dsp.sa.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
// 用来标记当前Controller的功能
@Api(tags = "jpa测试用户相关接口")
@RequestMapping("/jpaUser")
@RequiredArgsConstructor
/**
 * @author david
 */
public class TestUserJpaController {

    @NonNull
    JpaUserDao jpaUserDao;

//    @Autowired
//    public void setJpaUserDao(JpaUserDao jpaUserDao) {
//        this.jpaUserDao = jpaUserDao;
//    }

    @GetMapping("/getJpaUserByAddressAndUserId")
    @ApiOperation("getJpaUserByAddressEqualsAndUserIdLessThanEqual")
    public List<JpaUser> getUsers(@RequestParam("address") String address, @RequestParam("userId") Integer userId) {
        List<JpaUser> ret = jpaUserDao.getJpaUserByAddressEqualsAndUserIdLessThanEqual(address, userId);

        System.out.println(ret.toString());
        return ret;
    }

    @GetMapping("/maxUserIdUser")
    @ApiOperation("maxUserIdUser")
    public JpaUser getUsers() {
        JpaUser ret = jpaUserDao.maxUserIdUser();

        System.out.println(ret.toString());
        return ret;
    }

}
