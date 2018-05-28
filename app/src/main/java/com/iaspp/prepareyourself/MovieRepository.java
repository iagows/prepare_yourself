package com.iaspp.prepareyourself;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class MovieRepository {

    private String url;
    private String key;
    private RequestQueue requestQueue;


    public MovieRepository(Context appContext) {
        this.url = appContext.getResources().getString(R.string.api_url);
        this.key = appContext.getResources().getString(R.string.key);
    }

    public void fetchData(Context appContext, int page, RequestType type, HashMap<String, String> map) {
        requestQueue = Volley.newRequestQueue(appContext);
        String url = getUrl(appContext, page, type, map);
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

    /**
     * @param appContext
     * @param page
     * @param type
     * @param map
     * @return https://api.themoviedb.org/3/search/movie?api_key=1f54bd990f1cdfb230adb312546d765d&language=en-US&page=1&include_adult=false&query=summer
     */
    @NonNull
    private String getUrl(Context appContext, int page, RequestType type, HashMap<String, String> map) {
        final String url = appContext.getResources().getString(R.string.api_url);
        final String reqStr = type.toString();
        final String key = appContext.getResources().getString(R.string.key);
        if (map == null) {
            map = new HashMap<>();
        }

        map.put("api_key", key);
        map.put("page", String.valueOf(page));

        StringBuffer sb = new StringBuffer(url);
        sb.append(reqStr);
        sb.append("?");
        sb.append(hashPairs(map));
        return sb.toString();
    }

    /**
     * Key Value pairs
     *
     * @param map
     * @return
     */
    private String hashPairs(HashMap<String, String> map) {
        StringBuffer sb = new StringBuffer();
        for (String key : map.keySet()) {
            sb.append(key);
            sb.append("=");
            sb.append(map.get(key));
            sb.append("&");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
