package com.i2dsp.sa.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

//@Component
//@Service
public class QuartzTestService {

    public String sayHello() {
        return "QuartzTestService:" + new Date();
    }

}
