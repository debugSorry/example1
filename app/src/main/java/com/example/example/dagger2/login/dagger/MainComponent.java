package com.example.example.dagger2.login.dagger;

import com.example.example.dagger2.login.view.LoginActivity;

import dagger.Component;

/**
 * Author: 沈志华
 * E-mail: 754456231@qq.com
 * Date: 2017/10/9$ 10:02$
 * <p/>
 */
@Component(modules = {MainModel.class})
public interface MainComponent {
    void inject(LoginActivity activity);
}
