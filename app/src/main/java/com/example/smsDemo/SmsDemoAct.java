package com.example.smsDemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.example.R;
import com.example.example.common.BaseActivity;
import com.example.example.databinding.SmsdemoActBinding;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/17$ 14:05$
 * <p/>
 */
public class SmsDemoAct extends BaseActivity{
    private SmsdemoActBinding binding;
    /**原始验证码*/
    private int codeOri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.smsdemo_act);
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }
    /**发送验证码*/
    void send(){
        sercerReq();
    }
    /**获取设备信息*/
    void getDeviceInfo(){

    }
    /**请求服务器接口,返回加密算法，发送验证码*/
    void sercerReq(){
        //服务器产生原始验证码
        int codePro = 123456;
        codeOri = codePro;
        //服务器加密方式1
        code1(codeOri);
        //服务器加密方式2
        code2(codeOri,456789);
    }
    /**第一次加密*/
    void code1(int code){
        int num1 = code/100000;//第一位1
        int num2 = code%100000/10000;//第二位2
        int num3 = code%10000/1000;//第三位3
        int num4 = code%1000/100;//第四位4
        int num5 = code%100/10;//第五位5
        int num6 = code%10;//第六位6
        //第一位与第三位交换
        int proxy = num1;
        num1 = num3;
        num3 = proxy;
        //321456
        //第六位与第四位加7取余
        num6 = (num6 +7)%10;
        num4 = (num4+7)%10;
        //321153
        //第二位与第六位交换并分别加5取余
        proxy = num2;
        num2 = (num6+5)%10;
        num6 = (proxy+5)%10;
        //381157
        //第五位加上第一位取余
        num5 = (num5+num1)%10;
        //381187
        //第三位乘3取余
        num3 = num3*3%10;
        //383187
        //整体颠倒
        codeOri = num6*100000+num5*10000+num4*1000+num3*100+num2*10+num1;
        //781383
    }
    /**第二次加密*/
    void code2(int code,int key){
        //781383   456789
        int num1 = code/100000;//第一位1
        int num2 = code%100000/10000;//第二位2
        int num3 = code%10000/1000;//第三位3
        int num4 = code%1000/100;//第四位4
        int num5 = code%100/10;//第五位5
        int num6 = code%10;//第六位6
        int keyNum1 = key/100000;//key第一位1
        int keyNum2 = key%100000/10000;//key第二位2
        int keyNum3 = key%10000/1000;//key第三位3
        int keyNum4 = key%1000/100;//key第四位4
        int keyNum5 = key%100/10;//key第五位5
        int keyNum6 = key%10;//key第六位6
        //code第一位加上key第5位取余
        num1 = (num1+keyNum5)%10;
        //581383   456789
        //code第二位和key第2位交换
        int temp = num2;
        num2 = keyNum2;
        keyNum2 = num2;
        //551383   486789
        //code第三位与key第1位相乘取余
        num3 = num3*keyNum1%10;
        //554383   486789
        //key第五位与key第六位交换，code加上key最后两位
        temp = keyNum5;
        keyNum5 = keyNum6;
        keyNum6 = temp;
        codeOri = (keyNum5*10+keyNum6+num1*100000+num2*10000+num3*1000+num4*100+num5*10+num6)%1000000;
        //554383+98=554481
    }
}
