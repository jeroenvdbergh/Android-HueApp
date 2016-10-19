package com.example.jeroen.hueapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.jeroen.hueapp.R;
import com.example.jeroen.hueapp.VolleyRequestQueue;
import com.example.jeroen.hueapp.requests.HueRegisterRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener {

    private RequestQueue mQueue;
    private Button mButton;
    private EditText userNameEditText;
    private JSONObject lights;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button)findViewById(R.id.button);

        // Set Volleykey
        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();


    }

    @Override
    protected void onStart(){
        super.onStart();

        //initialize UI components
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);


        //Listeners
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                try{
                    register(userNameEditText.getText().toString());

                }catch(Exception ex){

                }

            }
        });
    }

    public void register(String username) throws JSONException {

        String jsonString = "\t{\"devicetype\":\"jeroenvdbergh1\"}";
        JSONObject jsonObject = new JSONObject(jsonString);

        String url = "http://127.0.0.1:1262/api";
        final HueRegisterRequest req = new HueRegisterRequest(Request.Method.GET,
                url,
                jsonObject,
                this,
                this);
        req.setTag("VolleyRegisterRequest");

        mQueue.add(req);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("",error.getMessage());
    }

    @Override
    public void onResponse(Object response) {
        Log.i("TAG", "response");
        try {

           lights = new JSONObject(response.toString());

            turnLightOff(6);


        }
        catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    public void turnLightOn(int index) throws JSONException {

        String json = "{'on':false}";

        JSONObject body = new JSONObject(json);

        String url = "http://192.168.1.179/api/18e381602023c54f34500db07e8c53f/lights/" + index + "/state";
        final HueRegisterRequest req = new HueRegisterRequest(Request.Method.PUT,
                url,
                body,
                this,
                this);
        req.setTag("VolleyRegisterRequest");
        mQueue.add(req);
    }

    public void turnLightOff(int index) throws JSONException {

        String json = "{'on':false}";

        JSONObject body = new JSONObject(json);

        String url = "http://192.168.1.179/api/18e381602023c54f34500db07e8c53f/lights/" + index + "/state";
        final HueRegisterRequest req = new HueRegisterRequest(Request.Method.PUT,
                url,
                body,
                this,
                this);
        req.setTag("VolleyRegisterRequest");
        mQueue.add(req);
    }

}
