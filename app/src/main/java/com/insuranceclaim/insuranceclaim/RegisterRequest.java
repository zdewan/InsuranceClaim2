package com.insuranceclaim.insuranceclaim;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static String REGISTER_REQUEST_URL = "https://infanticidal-lungs.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String email, String password, String passCon, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("passCon", passCon);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
