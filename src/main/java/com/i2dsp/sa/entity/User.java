package com.i2dsp.sa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class User {

    private long userId;
    private String userCode;
    private String username;
    private String password;
    private String crtUsrCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private java.sql.Timestamp createDatetime;
    private String updUsrCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private java.sql.Timestamp updateDatetime;
    private boolean deleted;
    private String delUsrCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private java.sql.Timestamp deleteDatetime;
}


