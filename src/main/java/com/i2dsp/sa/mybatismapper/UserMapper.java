package com.i2dsp.sa.mybatismapper;

import com.i2dsp.sa.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * // 如果在启动类中添加@MapperScan,使用时还是提示无法找到Bean,就使用spring的注解,
 * <p>
 * // 在Spring中有Repository的概念，repository原意指的是仓库，即数据仓库的意思。
 * Repository居于业务层和数据层之间，将两者隔离开来，在它的内部封装了数据查询和存储的逻辑。
 * // @Repository 會被作為持久層操作（資料庫）的bean來使用
 */
@Repository
public interface UserMapper {

    /**
     * 这两个select * 查询结果部分属性值为空,但数据库中是有值存在的,原因不明
     * 其他的结构结果都很正常
     */
    @Select("select * from user")
    List<User> getAllUsers();

    @Select("select * from user where username like concat('%',#{username},'%')")
    List<User> getUsersByName(String username);

    // MyBatis中使用@Results注解来映射查询结果集到实体类属性。
    // column为数据库字段名(有as则匹配as后面的)，porperty为实体类属性名，jdbcType为数据库字段数据类型，id为是否为主键。
    // 虽然语句里面查询4个栏位,但是results里面只映射了3个,实际上层能获取到的,也就只有3个
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "u"),
            @Result(property = "userCode", column = "user_code")
//            @Result(property = "crtUsrCode", column = "crt_usr_code")
    })
    @Select("select username as u,user_id ,user_code ,crt_usr_code from user where user_id =#{{userId}")
    User getUserById(Long userId);

    /**
     * //@SelctKey(statement="SQL语句",keyProperty="将SQL语句查询结果存放到keyProperty中去"
     * before="true表示先查询再插入，false反之",resultType=int.class)
     * <p>
     * statement是要运行的SQL语句，它的返回值通过resultType来指定
     * before表示查询语句statement运行的时机
     * keyProperty表示查询结果赋值给代码中的哪个对象，keyColumn表示将查询结果赋值给数据库表中哪一列
     * keyProperty和keyColumn都不是必需的，有没有都可以
     * before=true，插入之前进行查询，可以将查询结果赋给keyProperty和keyColumn，赋给keyColumn相当于更改数据库
     * before=false，先插入，再查询，这时只能将结果赋给keyProperty
     * 赋值给keyProperty用来“读”数据库，赋值给keyColumn用来写数据库
     * <p>
     * selectKey的两大作用：1、生成主键；2、获取刚刚插入数据的主键。
     * 使用selectKey，并且使用MySQL的last_insert_id()函数时，before必为false，也就是说必须先插入然后执行last_insert_id()才能获得刚刚插入数据的ID。
     */
    @Insert({"insert into user(`user_id`,`user_code`,`username`, `password`,`crt_usr_code`,`create_datetime`,`deleted`) " +
            "value (#{userId},#{userCode},#{username},#{password},#{crtUsrCode},#{createDatetime},#{deleted})"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "userId", before = false, resultType = Integer.class)
    Integer addUser(User user);

    @Update("update user set username=#{username},user_code=#{userCode} where user_id=#{userId}")
    Integer updateUserById(User user);

    @Delete("delete from user where user_id=#{userId}")
    Integer deleteUserById(Integer userId);

}
