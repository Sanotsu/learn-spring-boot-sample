package com.i2dsp.sa.dao.jpademo;

import com.i2dsp.sa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserDao extends JpaRepository<JpaUser, Integer> {
    List<JpaUser> getJpaUserByAddressEqualsAndUserIdLessThanEqual(String address, Integer userId);

    @Query(value = "select * from t_jpa_user where user_id=(select max (user_id) from t_jpa_user )", nativeQuery = true)
    JpaUser maxUserIdUser();
}
