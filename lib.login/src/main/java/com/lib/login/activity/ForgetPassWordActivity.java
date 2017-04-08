package com.lib.login.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lib.login.R;
import com.lib.login.http.Contants;
import com.lib.login.utils.StringUtils;
import com.lib.login.utils.ToastUtil;
import com.lib.login.http.Urls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


public class ForgetPassWordActivity extends AppCompatActivity {

    private TimeCount time;
    private TextView aboutTxt;
    private String content="点击确定即表示您同意";
    private String contentother="《注册协议》";
    private TextView titleTxt;
    private RelativeLayout rlBack;
    private RelativeLayout rlTitle;
    private EditText etUser;
    private EditText etPsw1;
    private EditText etPsw2;
    private EditText etMsg;
    private TextView tvMsg;
    private TextView okBT;
    private TextView aboutohterTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_foreget_psw);
        initialize();
        initListener();
        initData();
    }

    public void initialize() {
        rlBack= (RelativeLayout) findViewById(R.id.backLay);
        etUser = (EditText) findViewById(R.id.etUser);
        etPsw1 = (EditText) findViewById(R.id.etPsw1);
        etPsw2 = (EditText) findViewById(R.id.etPsw2);
        etMsg = (EditText) findViewById(R.id.etMsg);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        titleTxt = (TextView) findViewById(R.id.titleTxt);
        okBT = (TextView) findViewById(R.id.okBT);
        aboutTxt = (TextView) findViewById(R.id.aboutTxt);
        aboutohterTxt = (TextView) findViewById(R.id.aboutohterTxt);
    }

    public void initListener() {
        tvMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etUser.getText().toString())){
                    ToastUtil.showToast(ForgetPassWordActivity.this,"手机号不能为空");
                }else if (!StringUtils.isValidCellphone(etUser.getText().toString())){
                    ToastUtil.showToast(ForgetPassWordActivity.this,"手机格式有误");
                }else {
                    Map<String, String> maps = new HashMap<String, String>();
                    maps.put("Mobile",etUser.getText().toString());
                    maps.put("type", Contants.ResetPassword);
                    OkGo.post(Urls.VERIFY_CODE)
                            .params(maps)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    ToastUtil.showToast(ForgetPassWordActivity.this,"短信已发送");
                                    time.start();
                                }
                            });
                }
            }
        });
        okBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etUser.getText().toString())){
                    ToastUtil.showToast(ForgetPassWordActivity.this,"手机号不能为空");
                    return;
                }
                if (!StringUtils.isValidCellphone(etUser.getText().toString())){
                    ToastUtil.showToast(ForgetPassWordActivity.this,"手机格式有误");
                    return;
                }
                if(TextUtils.isEmpty(etPsw1.getText().toString())){
                    ToastUtil.showToast(ForgetPassWordActivity.this,"密码不能为空");
                    return;
                }
                if (!StringUtils.isValidPassword(etPsw1.getText().toString())){
                    ToastUtil.showToast(ForgetPassWordActivity.this,"密码至少6位");
                    return;
                }
                if(!etPsw1.getText().toString().equals(etPsw2.getText().toString())){
                    ToastUtil.showToast(ForgetPassWordActivity.this,"两次密码不一致");
                    return;
                }
                if(TextUtils.isEmpty(etMsg.getText().toString())){
                    ToastUtil.showToast(ForgetPassWordActivity.this,"验证码不能为空");
                    return;
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("Mobile",etUser.getText().toString());
                map.put("password",etPsw1.getText().toString());
                map.put("passwordconfirm",etPsw2.getText().toString());
                map.put("VerifiCode",etMsg.getText().toString());
                OkGo.post(Urls.FORGET_PASSWARD)
                        .params(map)
                        .tag(this)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                ToastUtil.showToast(ForgetPassWordActivity.this,"注册成功");
                                Intent intent = new Intent(ForgetPassWordActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                ToastUtil.showToast(ForgetPassWordActivity.this,"注册失败");
                            }
                        });
            }
        });
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initData() {
        time = new TimeCount(60000, 1000);
        titleTxt.setText("重置密码");
        aboutTxt.setText(content);
        aboutohterTxt.setText(contentother);
        aboutohterTxt.setTextColor(getResources().getColor(R.color.write));
        aboutohterTxt.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
    }


    //60秒短信计时
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
//            getphoneTxt.setBackgroundResource(R.drawable.roundtexting);
            tvMsg.setClickable(false);
            tvMsg.setText(millisUntilFinished / 1000 +"后可重发");
        }
        @Override
        public void onFinish() {
            tvMsg.setText("重发");
            tvMsg.setClickable(true);
//            getphoneTxt.setBackgroundResource(R.drawable.roundtext);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
