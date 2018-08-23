package com.insuranceclaim.insuranceclaim;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static String LOGIN_REQUEST_URL = "http://infanticidal-lungs.000webhostapp.com/login.php";
    private Map<String, String> params;

    public LoginRequest(String email, String Password, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("Password", Password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
