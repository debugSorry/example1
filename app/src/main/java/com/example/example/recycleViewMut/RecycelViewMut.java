package com.example.example.recycleViewMut;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.example.R;
import com.example.example.common.BaseActivity;
import com.example.example.databinding.ActivityRecyclerviewMutBinding;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/28$ 9:52$
 * <p/>
 */
public class RecycelViewMut extends BaseActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
       ActivityRecyclerviewMutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_recyclerview_mut);
    }
}
