package com.ww.lp.utils;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.ww.lp.lputils.LogUtils;
import com.ww.lp.lputils.SPUtils;
import com.ww.lp.utils.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.setDebug(true);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.tSp.setOnClickListener(v -> {
            LogUtils.w("测试...");
            SPUtils.putString(this, "sp_key", "sp_value");
            LogUtils.d(SPUtils.getString(this, "sp_key", ""));
        });

    }

}
