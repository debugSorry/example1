package com.example.example.dagger2.login.presenter;

/**
 * Author: 沈志华
 * E-mail: 754456231@qq.com
 * Date: 2017/9/30$ 10:24$
 * <p/>
 */
public interface ILoginPresenter {
    void clear();
    void doLogin(String name, String passwd);
    void setProgressBarVisibility(int visibility);
}
