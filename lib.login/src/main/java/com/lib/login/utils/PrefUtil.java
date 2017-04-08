package com.lib.login.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.lib.login.http.UserBean;


public class PrefUtil {

    private static final String SSO_TYPE = "SSO_TYPE";
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;
    public static final String KEY_APPPLICATION_SPF_MAIN = "TB_APPLICATION_SPF_MAIN";
    public static final String KEY_LANDING_PAGE_SHOWN = "KEY_LANDING_PAGE_SHOWN";
    public static final String KEY_FIRST_LAUNCHING = "KEY_FIRST_LAUNCHING";
    private static PrefUtil mInstance = null;

    private static final String AUTH_TOKEN = "AUTH_TOKEN";
    private static final String DEVICE_TOKEN = "DEVICE_TOKEN";
    private static final String LOGIN = "LOGIN";
    private static final String USER_PHONE = "USER_PHONE";
    private static final String USER_INFO = "USER_INFO";
    private static final String USER_LOCATION = "USER_LOCATION";
    private static final String CATEGORY = "CATEGORY";
    private static final String UNREAD_MESSAGE = "UNREAD_MESSAGE";
    private static final String CURRENT_TIME = "CURRENT_TIME";
    public static final String KEY_NO_DISTURB_MODE = "KEY_NO_DISTURB_MODE";


    public synchronized static PrefUtil getInstance(BaseView view) {
        if (null == mInstance) {
            mInstance = new PrefUtil(view.getActivity().getApplicationContext());
        }
        return mInstance;
    }
    public synchronized static PrefUtil getInstance(Context view) {
        if (null == mInstance) {
            mInstance = new PrefUtil(view.getApplicationContext());
        }
        return mInstance;
    }

    private PrefUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(KEY_APPPLICATION_SPF_MAIN, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public boolean isLogin() {
        return mSharedPreferences.getBoolean(LOGIN, false);
    }

    /**
     * 是否显示过落地页
     *
     * @return
     */
    public boolean getLandingPageShown() {
        boolean isShown = mSharedPreferences.getBoolean(KEY_LANDING_PAGE_SHOWN, false);
        mEditor.putBoolean(KEY_LANDING_PAGE_SHOWN, true).commit();
        return isShown;
    }

    /**
     * 是否显示过落地页
     *
     * @return
     */
    public boolean isFirstLunching() {
        boolean isFirst = mSharedPreferences.getBoolean(KEY_FIRST_LAUNCHING, true);
        mEditor.putBoolean(KEY_FIRST_LAUNCHING, false).commit();
        return isFirst;
    }


    public void removeUserInfo() {
        mEditor.remove(AUTH_TOKEN);
        mEditor.remove(LOGIN);
        mEditor.remove(USER_INFO);
        mEditor.remove(SSO_TYPE);
        mEditor.commit();
    }


    public void saveToken(String access_info) {
        mEditor.putString(AUTH_TOKEN, access_info).commit();
        setLogin();
    }

    public void saveDeviceToken(String deviceToken) {
        mEditor.putString(DEVICE_TOKEN, deviceToken).commit();
    }

    public String getDeviceToken() {
        String deviceT=mSharedPreferences.getString(DEVICE_TOKEN,"");
        if (StringUtils.isEmpty(deviceT)){
            return "";
        }else {
            return deviceT;
        }

    }

    public void saveUserAccount(String phone) {
        mEditor.putString(USER_PHONE, phone).commit();
    }

    public String getUserAccount() {
        return mSharedPreferences.getString(USER_PHONE, "");
    }


    public void setLogin() {
        mEditor.putBoolean(LOGIN, true).commit();
    }
    public void removeLogin(){
        mEditor.putBoolean(LOGIN, false).commit();

    }

    public UserBean getUserInfo() {
        String json = mSharedPreferences.getString(USER_INFO, "");
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return new Gson().fromJson(json, UserBean.class);
    }

    public void saveUserInfo(String json) {

        mEditor.putString(USER_INFO, json).commit();
    }

    public void saveCurrentTime(String data){
        mEditor.putString(CURRENT_TIME, data).commit();
    }

    public String getCurrentTime(){
        return mSharedPreferences.getString(CURRENT_TIME,"");
    }
}
