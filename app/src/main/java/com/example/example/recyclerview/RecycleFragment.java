package com.example.example.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.example.R;
import com.example.example.recyclerview.fragmentAdapter.RecycleAdapter;
import com.example.example.recyclerview.fragmentAdapter.RecycleVpFragment;
import com.example.example.recyclerview.fragmentAdapter.RecyclerVpAdaper;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/9/18$ 17:59$
 * <p/>
 */
public class RecycleFragment extends Fragment {
    private ArrayList<Integer> mList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View         view        = inflater.inflate(R.layout.recycle_fragment,container,false);
        RecyclerView mRecycleView = view.findViewById(R.id.recycleView);
        mRecycleView.setHasFixedSize(true);
        //ListView形式的列表
        //        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //GirdView形式的列表
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        mRecycleView.setLayoutManager(layoutManager);
        RecyclerViewHeader  header       = view.findViewById(R.id.header);
        AutoScrollViewPager viewPager    = view.findViewById(R.id.vp_auto);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RecycleVpFragment recyclerVpFragment =new RecycleVpFragment();
            fragmentList.add(recyclerVpFragment);
        }
        RecyclerVpAdaper bAdapter =new RecyclerVpAdaper(getChildFragmentManager(),fragmentList);
        viewPager.setAdapter(bAdapter);
        viewPager.setCurrentItem(0);
        viewPager.startAutoScroll();
        header.attachTo(mRecycleView,true);
        for(int i=0;i<5;i++){
            mList.add(R.mipmap.ic_launcher);
        }
        RecycleAdapter mAdapter =new RecycleAdapter(getActivity(),mList);
        mRecycleView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),mAdapter.onItemClickListener));
        mRecycleView.setAdapter(mAdapter);
        return view;
    }
}
