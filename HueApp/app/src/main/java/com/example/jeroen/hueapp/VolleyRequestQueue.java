package com.example.jeroen.hueapp;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by Jeroen on 20/09/2016.
 */
public class VolleyRequestQueue {

    private static VolleyRequestQueue mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    //VolleyRequestQueue constructor
    private VolleyRequestQueue(Context context){
        this.mContext = context;
        this.mRequestQueue = getRequestQueue();
    }

    //Singleton
    public static synchronized  VolleyRequestQueue getInstance(Context context){
        if(mContext == null){
            mInstance = new VolleyRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            Cache cache = new DiskBasedCache(mContext.getCacheDir(), 1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }
        return mRequestQueue;
    }


}
