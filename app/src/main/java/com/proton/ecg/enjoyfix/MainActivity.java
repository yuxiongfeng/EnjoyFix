package com.proton.ecg.enjoyfix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.proton.ecg.annotation.MyAnnotation;
import com.wms.logger.Logger;

@MyAnnotation
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClassLoader classLoader = String.class.getClassLoader();
        ClassLoader classLoader1 = this.getClassLoader();
        Logger.w("======", classLoader);
//        com.proton.ecg.enjoyfix.Test.print();
//        BuildConfig.APPLICATION_ID;
    }
}