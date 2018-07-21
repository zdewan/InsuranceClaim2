package com.insuranceclaim.insuranceclaim;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class loginRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPass = (EditText) findViewById(R.id.etPass);
        final EditText etPassCon = (EditText) findViewById(R.id.etPassCon);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Email =etEmail.getText().toString();
                final String Pass =etPass.getText().toString();
                final String PassCon =etPassCon.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(loginRegister.this, Login.class);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(loginRegister.this);
                                builder.setMessage("Register Unsuccessful")
                                        .setNegativeButton("Try Again", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                RegisterRequest registerRequest = new RegisterRequest(Email, Pass, PassCon, responseListener);
                RequestQueue queue = Volley.newRequestQueue(loginRegister.this);
                queue.add(registerRequest);

            }
        });
    }
}
