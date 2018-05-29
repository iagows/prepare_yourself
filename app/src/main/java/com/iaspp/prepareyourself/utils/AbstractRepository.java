package com.iaspp.prepareyourself.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iaspp.prepareyourself.R;
import com.iaspp.prepareyourself.config.TMDbConfig;
import com.iaspp.prepareyourself.genre.GenreResponseDTO;
import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.interfaces.IDTO;
import com.iaspp.prepareyourself.movie.MovieResponseDTO;
import com.iaspp.prepareyourself.utils.RequestType;

import java.util.HashMap;

public class AbstractRepository {

    private Gson gson;

    public AbstractRepository(Context appContext) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("YYYY-MM-dd");
        gson = gsonBuilder.create();
    }

    public void fetchData(Context appContext, final RequestType type, final ICallback.OnRequest callback, final int page, HashMap<String, String> map) {
        final RequestQueue requestQueue = Volley.newRequestQueue(appContext);
        String api_url = getUrl(appContext, page, type, map);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                IDTO dto;
                switch (type) {
                    default:
                    case UPCOMING:
                    case SEARCH_MOVIE:
                        dto = gson.fromJson(response, MovieResponseDTO.class);
                        break;
                    case CONFIGURATION:
                        dto = gson.fromJson(response, TMDbConfig.class);
                        break;
                    case GENRES:
                        dto = gson.fromJson(response, GenreResponseDTO.class);
                        break;
                }
                callback.onSucess(dto);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFail(error.getMessage());
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
        if (page > -1) {
            map.put("page", String.valueOf(page));
        }

        return api_url + reqStr + "?" + hashPairs(map);
    }

    /**
     * Key Value pairs
     *
     * @param map
     * @return
     */
    private String hashPairs(HashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
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
