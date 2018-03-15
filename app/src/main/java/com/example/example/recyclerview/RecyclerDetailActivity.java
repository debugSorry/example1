package com.example.example.recyclerview;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.example.R;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/9/19$ 16:57$
 * <p/>
 */
public class RecyclerDetailActivity extends AppCompatActivity{
    private TextView text_value;
    private String value;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.recycler_detail_act);
        System.out.println();
        text_value = (TextView) findViewById(R.id.text_value);
        value=getIntent().getStringExtra("book");
        System.out.println("value="+value);
        text_value.setText(value);
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("book",value);
        System.out.println("bbbbbbbbbb");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("aaaaaaaaaaa");
        value = savedInstanceState.getString("book");
        text_value.setText(value);
    }
}
