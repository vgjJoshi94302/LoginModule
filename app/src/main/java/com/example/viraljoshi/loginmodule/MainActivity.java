package com.example.viraljoshi.loginmodule;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends AppCompatActivity  {
    EditText username,password;
    Button login_button;
    String str_username,str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText)findViewById(R.id.et_username);
        password=(EditText)findViewById(R.id.et_password);


    }
    public void onLogin(View v)
    {
        String UsernameEt= username.getText().toString();
        String PasswordEt=password.getText().toString();
        String type="login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(type,UsernameEt,PasswordEt);
    }



}
