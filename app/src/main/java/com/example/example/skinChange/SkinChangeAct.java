package com.example.example.skinChange;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example.R;
import com.example.example.common.BaseActivity;
import com.example.example.databinding.ActivitySkinchangeBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.feng.skin.manager.entity.AttrFactory;
import cn.feng.skin.manager.entity.DynamicAttr;
import cn.feng.skin.manager.listener.ILoaderListener;
import cn.feng.skin.manager.loader.SkinManager;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/22$ 16:22$
 * <p/>
 */
public class SkinChangeAct extends BaseActivity{
    private ActivitySkinchangeBinding binding;
    private String SKIN_NAME = "test.skin";
    private String SKIN_DIR = Environment.getExternalStorageDirectory()+ File.separator+SKIN_NAME;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_skinchange);
        binding.btnChangeSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File skin = new File(SKIN_DIR);
                if(skin == null || !skin.exists()){
                    Toast.makeText(getApplicationContext(), "请检查" + SKIN_DIR + "是否存在", Toast.LENGTH_SHORT).show();
                    return;
                }

                SkinManager.getInstance().load(skin.getAbsolutePath(), new ILoaderListener() {
                    @Override
                    public void onStart() {
                        System.out.println("start");
                    }

                    @Override
                    public void onSuccess() {
                        System.out.println("onSuccess");
                    }

                    @Override
                    public void onFailed() {
                        System.out.println("onFailed");
                    }
                });
            }
        });
        binding.btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinManager.getInstance().restoreDefaultTheme();
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dynamicAddTextView();
            }
        });
    }
    /**动态添加textview*/
    private void dynamicAddTextView() {
        TextView                    textView = new TextView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView.setLayoutParams(params);
        textView.setTextColor(SkinManager.getInstance().getColor(R.color.text_color));
        textView.setText("hellohello");
        textView.setTextSize(28);
        List<DynamicAttr> mDanamicAttr = new ArrayList<DynamicAttr>();
        mDanamicAttr.add(new DynamicAttr(AttrFactory.TEXT_COLOR,R.color.text_color));
        dynamicAddView(textView,mDanamicAttr);
        binding.addLayout.addView(textView);

    }
}
