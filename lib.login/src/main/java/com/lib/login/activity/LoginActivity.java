package com.lib.login.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lib.login.http.HttpBean;
import com.lib.login.utils.PrefUtil;
import com.lib.login.R;
import com.lib.login.utils.StringUtils;
import com.lib.login.utils.ToastUtil;
import com.lib.login.http.Urls;
import com.lib.login.http.UserBean;
import com.lib.login.callback.DialogCallback;
import com.lzy.okgo.OkGo;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 *Author:Zachary
 *Time:2017/4/5 0005 上午 10:13
 *Dec:登录
 *Call:0592-3278796
 *Email:developer@baogukeji.com
 *Web:www.baogukeji.com
 */
public class LoginActivity extends AppCompatActivity{
    private ImageView ivClose;
    private TextView tvLogin,tvRegister;
    private boolean isFirst=true;
    private EditText phoneEdit;
    private EditText pswEdit;
    private TextView registTxt;
    private TextView forgetpasswordTxt;
    private ImageView ivEye;
    private RelativeLayout centerLay;
    private TextView loginTxt;
    private TextView notice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        initialize();
        initListener();
    }


    public void initialize() {
        phoneEdit = (EditText) findViewById(R.id.phoneEdit);
        pswEdit = (EditText) findViewById(R.id.pswEdit);
        registTxt = (TextView) findViewById(R.id.registTxt);
        forgetpasswordTxt = (TextView) findViewById(R.id.forget_passwordTxt);
        ivClose = (ImageView) findViewById(R.id.ivCloseText);
        ivEye = (ImageView) findViewById(R.id.ivEye);
        centerLay = (RelativeLayout) findViewById(R.id.centerLay);
        loginTxt = (TextView) findViewById(R.id.loginTxt);
        notice = (TextView) findViewById(R.id.notice);
    }

    public void initListener() {
        forgetpasswordTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(LoginActivity.this,ForgetPassWordActivity.class);
                startActivity(intent);

            }
        });
        registTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(phoneEdit.getText().toString())||StringUtils.isEmpty(pswEdit.getText().toString())){
                    notice.setText("用户名或密码不能为空");
                }else {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Account", phoneEdit.getText().toString());
                    params.put("password",pswEdit.getText().toString());
                    OkGo.post(Urls.LOGIN)
                            .params(params)
                            .execute(new DialogCallback<HttpBean<UserBean>>(LoginActivity.this) {
                                @Override
                                public void onSuccess(HttpBean<UserBean> httpBean, Call call, Response response) {
                                    Log.d("LoginActivity", "======onSuccess=======");
                                    UserBean userBean = httpBean.Data;
                                    PrefUtil.getInstance(getApplicationContext()).saveUserInfo(new Gson().toJson(userBean));
                                    PrefUtil.getInstance(getApplicationContext()).saveToken(userBean.getSessionId());
                                    PrefUtil.getInstance(getApplicationContext()).setLogin();
                                    notice.setVisibility(View.GONE);
                                    ToastUtil.showToast(LoginActivity.this,"登录成功");

                                    ComponentName componetName = new ComponentName(
                                            // 这个是另外一个应用程序的包名
                                            "com.example.fast",
                                            // 这个参数是要启动的Activity
                                            "com.example.fast.HomeActivity");
                                    Intent intent = new Intent();
                                    intent.setComponent(componetName);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    Log.d("LoginActivity", "======e======="+e);
                                }
                            });
                }

            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneEdit.setText("");
            }
        });
        ivEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFirst){
                    isFirst=false;
                    ivEye.setImageResource(R.mipmap.ic_eye_open);
                    //选择状态 显示明文--设置为可见的密码
                    pswEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    isFirst=true;
                    ivEye.setImageResource(R.mipmap.ic_eye_close);
                    //默认状态显示密码--设置文本 要一起写才能起作用  InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    pswEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s.toString())){
                    ivClose.setVisibility(View.VISIBLE);
                }else {
                    ivClose.setVisibility(View.GONE);
                }
            }
        });
    }
}
