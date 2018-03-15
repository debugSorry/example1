package com.example.example.recyclerview.fragmentAdapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.example.example.R;
import com.example.example.recyclerview.RecyclerDetailActivity;
import com.example.example.recyclerview.RecyclerItemClickListener;
import com.example.example.recyclerview.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/9/19$ 14:28$
 * <p/>
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{
    private List<Integer> list =new ArrayList<>();
    private Activity context;
    private boolean animateItems = false;
    private int lastAnimatedPosition = -1;
    public RecycleAdapter(Activity context, List<Integer>mList){
        TypedValue mTypedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground,mTypedValue,true);
        list=updateItems(mList,true);
        this.context=context;
    }

    private List<Integer> updateItems(List<Integer> mList, boolean b) {
        List<Integer> list = new ArrayList<>();
        animateItems = b;
        lastAnimatedPosition = -1;
        list.addAll(mList);
        notifyDataSetChanged();
        return list;
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        holder.iv_card.setBackgroundResource(list.get(position));
    }

    private void runEnterAnimation(View itemView, int position) {
        if(!animateItems || position >= 3){
            return;
        }
        if(position > lastAnimatedPosition){
            lastAnimatedPosition =position;
            itemView.setTranslationY(Utils.getScreenHeight(context));
            itemView.animate()
                    .translationY(0)
                    .setStartDelay(100*position)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public RecyclerItemClickListener.OnItemClickListener onItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent       intent = new Intent(context, RecyclerDetailActivity.class);
            intent.putExtra("book", "book");
            System.out.println("11111111");
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                            view.findViewById(R.id.iv_card),context.getString(R.string.transition_book_img));
            ActivityCompat.startActivity(context, intent, options.toBundle());

        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv_card;
        public int       position;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_card = (ImageView)itemView.findViewById(R.id.iv_card);
        }
    }

}
