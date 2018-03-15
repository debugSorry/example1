package com.example.example.wxapply;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.example.R;

import java.net.URISyntaxException;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/30$ 10:57$
 * <p/>
 */
public class WxApply extends AppCompatActivity{

    private WebView vWebview;

//    public static final String LOAD_URL = "http://h5.tnbpay.com/wxjspayh5/do2.html";
    public static final String LOAD_URL = "http://m.lpbdht.com/api/jumptoweixin?wid=1312&qid=59f82c023fe72cd179a670ee&iswebview=no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wx);
        vWebview = (WebView) findViewById(R.id.main_webView);
        WebSettings settings = vWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        vWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("url=="+url);
//                if(url.startsWith("weixin://")){
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(url));
//                    startActivity(intent);
//                    return true;
//                }
                if(url.startsWith("intent://")) {
                    Intent intent;
                    try {
                        intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                        // forbid launching activities without BROWSABLE
                        // category
                        intent.addCategory("android.intent.category.BROWSABLE");
                        // forbid explicit call
                        intent.setComponent(null);
                        // forbid intent with selector intent
                        intent.setSelector(null);
                        // start the activity by the intent
                        startActivityIfNeeded(intent, -1);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                    return super.shouldOverrideUrlLoading(view, url);
            }
        });
        vWebview.loadUrl(LOAD_URL);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(vWebview.canGoBack()){
                vWebview.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
