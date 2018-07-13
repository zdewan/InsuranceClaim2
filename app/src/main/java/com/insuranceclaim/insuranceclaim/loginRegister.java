package com.insuranceclaim.insuranceclaim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class loginRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPass = (EditText) findViewById(R.id.etPass);
        final EditText etPassCon = (EditText) findViewById(R.id.etPassCon);

        final Button bRegister = (Button) findViewById(R.id.bRegister);

    }
}
