package com.example.day31_mvp_modle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day31_mvp_modle.Presenter.UserPresenter;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEdt;
    private EditText passwordEdt;
    private UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        userPresenter = new UserPresenter();
    }

    private void initView() {
        usernameEdt = (EditText) findViewById(R.id.username_edt);
        passwordEdt = (EditText) findViewById(R.id.password_edt);
    }
    public void click(View view){
        String username = usernameEdt.getText().toString();
        String password = passwordEdt.getText().toString();
        String result = userPresenter.login(username, password);
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
    }
}
