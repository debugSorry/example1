package com.example.example.recyclerview.fragmentAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.example.R;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/9/19$ 16:35$
 * <p/>
 */
public class RecycleVpFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.view_pager,container,false);
        return view;
    }
}
