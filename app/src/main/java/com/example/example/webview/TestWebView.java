package com.example.example.webview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;

import com.example.example.R;
import com.example.example.common.BaseActivity;
import com.example.example.databinding.ActivityWebviewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/12/13$ 9:48$
 * <p/>
 */
public class TestWebView extends BaseActivity{
    private List<String> list=new ArrayList<>();
    private ActivityWebviewBinding binding;
    private int mkeyCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_webview);
        initData();
        WebSettings webSetting = binding.webView.getSettings();
        // 是否允许在webview中执行javascript
        webSetting.setJavaScriptEnabled(true);
        binding.webView.addJavascriptInterface(this,"testJS");
        binding.webView.loadUrl("file:///android_asset/test.html");

    }

    private void initData() {
        for(int i=0;i<5;i++){
            list.add("list中的第"+i+"个");
        }
    }
    @JavascriptInterface
    public Object getObject(int index){
        System.out.println("getObject");
        return list.get(index);
    }
    @JavascriptInterface
    public int getSize(){
        System.out.println("getSize");
        return list.size();
    }
    @JavascriptInterface
    public void Callfunction() {
        System.out.println("Callfunction");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.webView.loadUrl("javascript: GetList()");
            }
        });
    }
    @JavascriptInterface
    public void printStr(String str){
        System.out.println("printStr="+str);
    }
    @JavascriptInterface
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        mkeyCode = keyCode;
        System.out.println("keyCode="+keyCode);
        binding.webView.loadUrl("javascript: OnKeyUp()");
        return super.onKeyUp(keyCode, event);
    }
    @JavascriptInterface
    public int getKeyCode(){
        return mkeyCode;
    }
}
