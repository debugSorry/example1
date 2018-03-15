package com.example.example.recyclerview;

import android.content.Context;
import android.view.WindowManager;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/9/19$ 16:20$
 * <p/>
 */
public class Utils {
    /**获取屏幕高度*/
    public static int getScreenHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
    /**获取屏幕宽度*/
    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
}
