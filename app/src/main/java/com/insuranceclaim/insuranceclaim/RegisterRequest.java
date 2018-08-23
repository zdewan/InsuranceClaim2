package com.insuranceclaim.insuranceclaim;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static String REGISTER_REQUEST_URL = "http://infanticidal-lungs.000webhostapp.com/register.php";
    private Map<String, String> params;

    public RegisterRequest(String email, String Password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("Password", Password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
