package com.lib.login;

import android.content.Context;
import android.content.Intent;

import com.example.fast.lib.interfaces.ILogin;
import com.lib.login.activity.LoginActivity;

public class LoginImpl implements ILogin {

    @Override
    public void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
