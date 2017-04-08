package com.lib.login.http;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class Urls {

    public static String API_HOST="http://aliyun.dns1.baogukeji.com:16010";//域名
    public static String LOGIN=API_HOST+"/api/useraccount/login";//登录
    public static String REGISTER=API_HOST+"/api/useraccount/RegisterByMobile";//注册
    public static String FORGET_PASSWARD=API_HOST+"/api/useraccount/ResetPasswordBySMS";//忘记密码
    public static String VERIFY_CODE=API_HOST+"/api/verifycode/sms";//短信验证码发送
}
