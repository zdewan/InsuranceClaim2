package com.insuranceclaim.insuranceclaim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class accidentUserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_user_info);


        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
    }
}
