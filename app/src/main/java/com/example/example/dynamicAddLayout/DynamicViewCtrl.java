package com.example.example.dynamicAddLayout;

import android.databinding.ObservableInt;

import com.example.example.BR;
import com.example.example.R;
import com.example.example.common.BaseRecycleViewCtrl;
import com.example.example.common.BaseRecyclerViewVM;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/13$ 16:47$
 * <p/>
 */
public class DynamicViewCtrl extends BaseRecycleViewCtrl{
    public ObservableInt daySize =new ObservableInt(3);
    public DynamicViewCtrl(final List<String> dayList){
        if(dayList.size()>=3){
            daySize.set(3);
        }else{
            daySize.set(dayList.size());
        }
        viewModel.set(new BaseRecyclerViewVM<DynamicItemVM>() {
            @Override
            protected void setItemBinding(ItemBinding itemBinding, int position, DynamicItemVM item) {
                itemBinding.set(BR.viewCtrl, R.layout.add_item).bindExtra(BR.listener,getListener());
            }
        });
        viewModel.get().setListener(new BaseRecyclerViewVM.OnItemClickListener() {
            @Override
            public void onItemClickListener(String dayStr) {
                for(int i=0;i< viewModel.get().items.size();i++){
                    DynamicItemVM dynamicItemVM = (DynamicItemVM) viewModel.get().items.get(i);
                    if(dayStr!=null){
                        if(dayStr.equals(dynamicItemVM.getDayStr())){
                            dynamicItemVM.setSelectBackground(true);
                        }else{
                            dynamicItemVM.setSelectBackground(false);
                        }
                    }
                }
            }
        });
        viewModel.get().items.clear();
        for(int i=0;i<(dayList.size()>=3?3:dayList.size());i++){
            DynamicItemVM vm =new DynamicItemVM();
            vm.setDayStr(dayList.get(i));
            if(i==0){
                vm.setSelectBackground(true);
            }else{
                vm.setSelectBackground(false);
            }
            viewModel.get().items.add(vm);
        }

    }
}
