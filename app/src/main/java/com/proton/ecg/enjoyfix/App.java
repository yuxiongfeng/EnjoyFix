package com.proton.ecg.enjoyfix;

import android.app.Application;

import com.wms.logger.Logger;

/**
 * @Description:
 * @Author: yxf
 * @CreateDate: 2022/11/18 13:35
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.newBuilder()
                .isDebug(true)
                .tag("enjoyFix")
                .build();
    }
}
