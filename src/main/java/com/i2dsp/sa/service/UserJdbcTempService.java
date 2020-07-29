package com.i2dsp.sa.service;

import com.i2dsp.sa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 缓存的几个核心注解
 * <p>
 * // @CacheConfig: 在类上使用, 用来描述该类中所有方法使用的缓存名称, 也可以不使用该注解, 直接在具体的缓存注解上配置名称, 示例:
 * --
 * // @Service
 * // @CacheConfig(cacheNames="c1") public class UserService{
 * ...
 * }
 * <p>
 * // @Cacheable: 一般加在查询方法上, 表示将一个方法的返回值缓存起来.默认情况下, 缓存的key就是方法的参数, 缓存的value就是方法的返回值, 示例
 * --
 * // @Cacheable(key="#id")
 * public User getUserById(Integer id,String username){
 * return getUserFromDbById(id);
 * }
 * <p>
 * // @CachePut(key = "#user.userId") 一般用在更新方法上,数据库数据更新后,缓存的数据也跟着更新.方法的返回值自动更新到已存在的key上
 * <p>
 * // @CacheEvict: 一般加在删除方法上,当数据库中数据删除后,相关的缓存也自动清除
 * 可以配置按照某种条件删除(condition属性)或者配置清除所有缓存(allEntries属性)
 */

@Service
@CacheConfig(cacheNames = "c1")
public class UserJdbcTempService {

    /**
     * spring 自带的JdbcTemplate
     * 虽然功能简陋,但使用简单,算得上最简单的数据持久化方案了
     * <p>
     * 在Spring boot中,配置完数据库基本信息之后,就有了一个JdbcTemplate,原因是:
     * org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration类中
     * 在当前类路径下存在DataSource和JdbcTemplate时,该类会被自动配置.
     * jdbcTemplate方法则表示,如果开发者没有自己提供一个JdbcOperations的实例的话,系统就会自动配置一个JdbcTemplate Bean
     * (JdbcTemplate 是JdbcOperations接口的一个实现)
     */
    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 增
    public int addUser(User user) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date current = new Date();

        try {
            int i = jdbcTemplate.update("insert into user (user_code,username,password,crt_usr_code,create_datetime) values (?,?,?,?,?)",
                    user.getUserCode(), user.getUsername(), user.getPassword(), user.getCrtUsrCode(), sdf.format(current));
            return i;
        } catch (Exception e) {
            System.out.println(e);
            // E的ascii码 69 demo用
            return 69;
        }
    }

    // 改
    @CachePut(key = "#user.userId")
    public int updateUserById(User user) {
        return jdbcTemplate.update("update user set username=?,password=? where user_id =?",
                user.getUsername(), user.getPassword(), user.getUserId());
    }

    // 删
    @CacheEvict()
    public int deleteUserById(User user) {
        return jdbcTemplate.update("delete from user where user_id =?", user.getUserId());
    }


    // 查
    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {

                User user = new User();
                user.setUserId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
    }
}
