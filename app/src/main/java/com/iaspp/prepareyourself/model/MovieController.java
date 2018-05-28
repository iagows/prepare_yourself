package com.iaspp.prepareyourself.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iaspp.prepareyourself.R;

public class MovieController {

    private RequestQueue requestQueue;

    public void fetchMovies(Context applicationContext) {
        requestQueue = Volley.newRequestQueue(applicationContext);

        //String url = applicationContext.getResources().getString(R.string.api_url);
        String url = "https://api.themoviedb.org/3/search/movie?api_key=1f54bd990f1cdfb230adb312546d765d&language=en-US&page=1&include_adult=false&query=summer";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, onSucessLoad, onFailLoad);

        requestQueue.add(stringRequest);
    }

    private final Response.Listener<String> onSucessLoad = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            System.out.println("Response: " + response);
            Log.i("PostActivity", response);
        }
    };

    private final Response.ErrorListener onFailLoad = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("Error: " + error);
            Log.e("PostActivity", error.toString());
        }
    };
}
