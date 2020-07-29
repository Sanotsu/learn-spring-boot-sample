package com.i2dsp.sa.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "t_jpa_user")
public class JpaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String address;
}


