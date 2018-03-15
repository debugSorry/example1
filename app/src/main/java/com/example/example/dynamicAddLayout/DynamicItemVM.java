package com.example.example.dynamicAddLayout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.example.BR;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/13$ 15:56$
 * <p/>
 */
public class DynamicItemVM extends BaseObservable{
    private String               dayStr;
    private boolean              selectBackground;
    @Bindable
    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
        notifyPropertyChanged(BR.dayStr);
    }
    @Bindable
    public boolean isSelectBackground() {
        return selectBackground;
    }

    public void setSelectBackground(boolean selectBackground) {
        this.selectBackground = selectBackground;
        notifyPropertyChanged(BR.selectBackground);
    }
}
