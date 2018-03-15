package com.example.example.dynamicAddLayout;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.example.R;
import com.example.example.databinding.AddItemBinding;
import com.example.example.databinding.DynamicAddBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/13$ 14:44$
 * Description；动态添加控件
 * <p/>
 */
public class DynamicAddLayout extends AppCompatActivity{
    private List<String> dayList= new ArrayList<>();
    private DynamicAddBinding binding;
    private ObservableArrayList<AddItemBinding> items=new ObservableArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.dynamic_add);
        dayList.add(0,"1天");
        dayList.add(1,"14天");
//        dayList.add(2,"30天");
//        dayList.add(3,"40天");
//        dayList.add(4,"50天");
//        dayList.add(5,"60天");
//        addLinearLayout1();
        addLinearLayout2();
    }
    /**动态添加布局版本2*/
    private void addLinearLayout2() {
        binding.setViewCtrl(new DynamicViewCtrl(dayList));
    }

    /**动态添加布局版本1*/
    private void addLinearLayout1() {
        LinearLayout linearLayout=new LinearLayout(binding.getRoot().getContext());
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        items.clear();
        for(int i=0;i<dayList.size();i++){
            AddItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(binding.getRoot().getContext()),R.layout.add_item,null,false);
            DynamicItemVM dynamicItemVM =  new DynamicItemVM();
            dynamicItemVM.setDayStr(dayList.get(i));
            if(i==0){
                dynamicItemVM.setSelectBackground(true);
            }else{
                dynamicItemVM.setSelectBackground(false);
            }
            itemBinding.setViewCtrl(dynamicItemVM);
            final int finalI = i;
            itemBinding.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int j=0;j<items.size();j++){
                        if(finalI ==j){
                            items.get(j).getViewCtrl().setSelectBackground(true);
                        }else{
                            items.get(j).getViewCtrl().setSelectBackground(false);
                        }
                    }
                }
            });
            items.add(itemBinding);
            linearLayout.addView(itemBinding.getRoot());
        }
        binding.layout.addView(linearLayout);
    }
}
