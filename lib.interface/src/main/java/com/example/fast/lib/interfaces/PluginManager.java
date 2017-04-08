package com.example.fast.lib.interfaces;

/**
 * Created by bingbing on 2016/12/21.
 */

public class PluginManager {

    ILogin login;

    private static PluginManager mInstance;

    public PluginManager() {
        try {
            login = getImpl("com.lib.login.LoginImpl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> T getImpl(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return (T) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static PluginManager getInstance() {
        if (mInstance == null) {
            synchronized (PluginManager.class) {
                if (mInstance == null) {
                    mInstance = new PluginManager();
                }
            }
        }
        return mInstance;
    }

    public static ILogin getLogin() {
        return getInstance().login;
    }
}
