package com.chat.mica.chatappv8888;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void chat(View v)
    {
        Intent i =new Intent(LoginActivity.this,MainchatListActivity.class);
        startActivity(i);

    }
}
