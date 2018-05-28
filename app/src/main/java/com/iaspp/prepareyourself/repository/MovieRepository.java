package com.iaspp.prepareyourself.repository;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iaspp.prepareyourself.R;
import com.iaspp.prepareyourself.dto.AbstractResponseDTO;
import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.utils.RequestType;
import com.iaspp.prepareyourself.dto.ResponseMovieUpcomingDTO;
import com.iaspp.prepareyourself.dto.ResponseSearchMovieDTO;

import java.util.HashMap;

public class MovieRepository {

    private Gson gson;

    public MovieRepository(Context appContext) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("YYYY-MM-dd");
        gson = gsonBuilder.create();
    }

    public void fetchData(Context appContext, final int page, final RequestType type, HashMap<String, String> map) {
        final RequestQueue requestQueue = Volley.newRequestQueue(appContext);
        String api_url = getUrl(appContext, page, type, map);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                AbstractResponseDTO dto;
                switch (type) {
                    default:
                    case UPCOMING:
                        dto = gson.fromJson(response, ResponseMovieUpcomingDTO.class);
                        break;
                    case SEARCH_MOVIE:
                        dto = gson.fromJson(response, ResponseSearchMovieDTO.class);
                        break;
                }
                //Log.i("Size", dto.getResultList().size() + " movies loaded.");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }

    /**
     * @param appContext
     * @param page
     * @param type
     * @param map
     * @return https://api.themoviedb.org/3/search/movie?api_key=1f54bd990f1cdfb230adb312546d765d&language=en-US&page=1&include_adult=false&query=summer
     */
    @NonNull
    private String getUrl(Context appContext, int page, RequestType type, HashMap<String, String> map) {
        final String api_url = appContext.getResources().getString(R.string.api_url);
        final String reqStr = type.toString();
        final String key = appContext.getResources().getString(R.string.key);
        if (map == null) {
            map = new HashMap<>();
        }

        map.put("api_key", key);
        map.put("page", String.valueOf(page));

        final StringBuffer sb = new StringBuffer(api_url);
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
        final StringBuffer sb = new StringBuffer();
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
