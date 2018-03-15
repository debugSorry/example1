package com.example.example.recyclerview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/9/19$ 16:46$
 * <p/>
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    OnItemClickListener listener;
    private long time;
    public RecyclerItemClickListener(Activity acticity,OnItemClickListener listener){
        System.out.println("22222222222222");
        this.listener=listener;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if(e.getAction()==MotionEvent.ACTION_DOWN){
            time=System.currentTimeMillis();
        }
        if(e.getAction() == MotionEvent.ACTION_UP){
            if(System.currentTimeMillis()-time<100){
                View view = rv.findChildViewUnder(e.getX(),e.getY());
                System.out.println(rv.getChildLayoutPosition(view)+"---");
                listener.onItemClick(view,rv.getChildLayoutPosition(view));
                return true;
            }
        }
        System.out.println("333333333");
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        System.out.println("44444444444");
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        System.out.println("5555555555");
    }

    public interface OnItemClickListener{
       void  onItemClick(View view, int position);
    }
}
