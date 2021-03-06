package com.example.example.dagger2.login.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.example.R;
import com.example.example.dagger2.login.dagger.DaggerMainComponent;
import com.example.example.dagger2.login.dagger.MainModel;
import com.example.example.dagger2.login.presenter.LoginPresenterCompl;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView{
    private EditText editUser;
    private EditText editPass;
    private Button   btnLogin;
    private Button   btnClear;
    @Inject
    LoginPresenterCompl loginPresenter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUser = (EditText) this.findViewById(R.id.et_login_username);
        editPass = (EditText) this.findViewById(R.id.et_login_password);
        btnLogin = (Button) this.findViewById(R.id.btn_login_login);
        btnClear = (Button) this.findViewById(R.id.btn_login_clear);
        progressBar = (ProgressBar) this.findViewById(R.id.progress_login);
        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        DaggerMainComponent.builder().mainModel(new MainModel(this)).build().inject(this);
//        loginPresenter = new LoginPresenterCompl(this);

        loginPresenter.setProgressBarVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_login_clear:
                loginPresenter.clear();
                break;
            case R.id.btn_login_login:
                loginPresenter.setProgressBarVisibility(View.VISIBLE);
                btnLogin.setEnabled(false);
                btnClear.setEnabled(false);
                loginPresenter.doLogin(editUser.getText().toString(),editPass.getText().toString());
                break;
            default:break;
        }
    }

    @Override
    public void onClearText() {
        editUser.setText("");
        editPass.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        loginPresenter.setProgressBarVisibility(View.INVISIBLE);
        btnLogin.setEnabled(true);
        btnClear.setEnabled(true);
        if (result){
            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Login Fail, code = " + code,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
