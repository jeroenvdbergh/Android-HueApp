package com.example.jeroen.hueapp.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jeroen.hueapp.view.MainActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Jeroen on 20/09/2016.
 */
public class HueRegisterRequest extends JsonObjectRequest {

    public HueRegisterRequest(int method,
                              String url,
                              JSONObject jsonRequest,
                              MainActivity listener,
                              MainActivity errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String,String>();
        headers.put("Content-type","application/json; charset=utf-8");
        return headers;
    }

    @Override
    public RetryPolicy getRetryPolicy() {

        return super.getRetryPolicy();
    }

}


