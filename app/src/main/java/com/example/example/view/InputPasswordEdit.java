package com.example.example.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.example.R;
import com.example.example.common.BaseActivity;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/12/27$ 9:23$
 * <p/>
 */
public class InputPasswordEdit extends BaseActivity{
    private PasswordInputEdit edit;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_password_edit);
        edit = (PasswordInputEdit) findViewById(R.id.edit);

        edit.setOnInputOverListener(new PasswordInputEdit.onInputOverListener() {
            @Override
            public void onInputOver(String text) {
                Toast.makeText(InputPasswordEdit.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
