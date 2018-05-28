package com.iaspp.prepareyourself.model;

import android.content.Context;

import com.iaspp.prepareyourself.dto.AbstractResponseDTO;
import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.repository.MovieRepository;
import com.iaspp.prepareyourself.utils.RequestType;

import java.util.HashMap;

public class MovieController {

    private MovieRepository repository;

    public MovieController(Context appContext) {
        this.repository = new MovieRepository(appContext);
    }

    public void fetch(Context appContext)  {
        fetch(appContext, 1);
    }

    public void fetch(Context appContext, int page) {
        fetch(appContext, page, RequestType.UPCOMING);
    }

    public void fetch(Context appContext, int page, RequestType type)  {
        fetch(appContext, page, type, null);
    }

    public void fetch(Context appContext, int page, RequestType type, HashMap<String, String> map) {
        repository.fetchData(appContext, page, type, map);
    }
}
