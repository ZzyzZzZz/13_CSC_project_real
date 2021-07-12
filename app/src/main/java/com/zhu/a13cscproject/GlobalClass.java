package com.zhu.a13cscproject;

import android.app.Application;

public class GlobalClass extends Application {
    private Integer dev_mode;

    public Integer getDev_mode() {
        return dev_mode;
    }

    public void setDev_mode(Integer dev_mode) {
        this.dev_mode = dev_mode;//dev_mode global integer share among all classes.
    }
}
