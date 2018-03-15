package com.example.example.mvp.login.presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.example.mvp.login.model.IUser;
import com.example.example.mvp.login.model.UserModel;
import com.example.example.mvp.login.view.ILoginView;

/**
 * Author: 沈志华
 * E-mail: 754456231@qq.com
 * Date: 2017/9/30$ 10:24$
 * <p/>
 */
public class LoginPresenterCompl implements ILoginPresenter{
    ILoginView iLoginView;
    IUser      user;
    Handler    handler;
    public LoginPresenterCompl(ILoginView iLoginView){
        this.iLoginView=iLoginView;
        initUser();
        handler =new Handler(Looper.getMainLooper());
    }

    private void initUser() {
        user = new UserModel("mvp","mvp");
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {
        Boolean isLoginSuccess = true;
        final int code =user.checkUserValidity(name,passwd);
        if(code != 0){
            isLoginSuccess = false;
        }
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(result, code);
            }
        },3000);
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        iLoginView.onSetProgressBarVisibility(visibility);
    }
}
