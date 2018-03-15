package com.example.example.common;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/13$ 16:49$
 * <p/>
 */
public abstract class BaseRecyclerViewVM<T> extends BaseObservable{
    /** 数据源 */
    public final ObservableList<T> items = new ObservableArrayList<>();
    public interface OnItemClickListener{
        void onItemClickListener(String dayStr);
    }
    OnItemClickListener listener;
    protected  abstract void setItemBinding(ItemBinding itemBinding, int position, T item);

    public OnItemBind<T> itemBinding =new OnItemBind<T>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, T item) {
            setItemBinding(itemBinding,position,item);
        }
    };

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
