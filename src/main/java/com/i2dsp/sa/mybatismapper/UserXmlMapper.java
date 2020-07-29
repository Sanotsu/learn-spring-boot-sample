package com.i2dsp.sa.mybatismapper;

import com.i2dsp.sa.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 这个mapper对应到xml中写sql
 * 对应的xml放置在resources目录下,并在配置中添加指定扫描的路径,避免打包后xml和mapper接口层级不一致等问题
 * <p>
 * 此外可以把xml放到此同级目录下,到时打包时会被忽略掉,就需要在pom.xml继续配置,避免被忽略
 * 前者不需要再配置
 */

/**
 * // 如果在启动类中添加@MapperScan,使用时还是提示无法找到Bean,就使用spring的注解,
 * <p>
 * // 在Spring中有Repository的概念，repository原意指的是仓库，即数据仓库的意思。
 * Repository居于业务层和数据层之间，将两者隔离开来，在它的内部封装了数据查询和存储的逻辑。
 * // @Repository 會被作為持久層操作（資料庫）的bean來使用
 */
@Repository
public interface UserXmlMapper {
    List<User> getAllUsers();

    Integer addUser(User user);

    Integer updateUserById(User user);

    Integer deleteUserById(Integer userId);

}
