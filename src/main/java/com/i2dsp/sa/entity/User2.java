package com.i2dsp.sa.entity;

import lombok.Data;

@Data
public class User2 {

    private String employeeId;
    private String username;
    private String password;
    private String burnInRackId;
    private long burnInTest;
    private long burnInConfig;
    private long burnInResult;
    private long permissionConfig;
    private long groupKind;
    private String uuid;
    private String createDatetime;
    private String createUser;
    private String updateDatetime;
    private String updateUser;


}
