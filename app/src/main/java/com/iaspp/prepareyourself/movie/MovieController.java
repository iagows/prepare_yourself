package com.iaspp.prepareyourself.movie;

import android.content.Context;

import com.iaspp.prepareyourself.config.TMDbConfig;
import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.utils.RequestType;

import java.util.HashMap;

public class MovieController {

    private MovieRepository repository;
    private TMDbConfig config;
    private int upComingPage;

    public MovieController(Context appContext) {
        this.repository = new MovieRepository(appContext);
        this.resetUpcoming();
    }

    public void initConfiguration(Context appContext, ICallback.OnRequest callback){
        fetch(appContext, RequestType.CONFIGURATION, callback);
    }

    public void getUpcoming(Context appContent, ICallback.OnRequest callback){
        fetch(appContent, RequestType.UPCOMING, callback, this.upComingPage++);
    }

    public void resetUpcoming(){
        this.upComingPage=1;
    }


    public void fetch(Context appContext, RequestType type, ICallback.OnRequest callback) {
        fetch(appContext, type, callback, -1);
    }

    public void fetch(Context appContext, RequestType type, ICallback.OnRequest callback, int page) {
        fetch(appContext, type, callback, page, null);
    }

    public void fetch(Context appContext, RequestType type, ICallback.OnRequest callback, int page, HashMap<String, String> map) {
        repository.fetchData(appContext, type, callback, page, map);
    }

    public TMDbConfig getConfig() {
        return config;
    }

    public void setConfig(TMDbConfig config) {
        this.config = config;
    }
}
