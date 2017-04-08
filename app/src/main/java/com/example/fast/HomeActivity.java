package com.example.fast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lib.login.utils.PrefUtil;

public class HomeActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView= (TextView) findViewById(R.id.tvTitle);
        String phone=PrefUtil.getInstance(getApplicationContext()).getUserInfo().getMobile();
        boolean login=PrefUtil.getInstance(getApplicationContext()).isLogin();
        textView.setText(phone+"------"+login);
    }
}
