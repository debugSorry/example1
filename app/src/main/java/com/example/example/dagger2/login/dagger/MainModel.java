package com.example.example.dagger2.login.dagger;

import com.example.example.dagger2.login.view.ILoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Author: 沈志华
 * E-mail: 754456231@qq.com
 * Date: 2017/10/9$ 10:01$
 * <p/>
 */
@Module
public class MainModel {
    private final ILoginView view;
    public MainModel(ILoginView view){
        this.view=view;
    }
    @Provides
    ILoginView  provideView(){
        return view;
    }

}
