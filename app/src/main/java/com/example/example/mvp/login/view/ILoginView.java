package com.example.example.mvp.login.view;

/**
 * Author: 沈志华
 * E-mail:754456231@qq.com
 * Date: 2017/9/30$ 10:22$
 * <p/>
 */
public interface ILoginView {
    void onClearText();
    void onLoginResult(Boolean result, int code);
    void onSetProgressBarVisibility(int visibility);
}
