package com.iaspp.prepareyourself.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.iaspp.prepareyourself.config.TMDbConfig;
import com.iaspp.prepareyourself.config.TMDbImagesConfig;
import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.movie.MovieDTO;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractController {

    private AbstractRepository abstractRepository;

    public AbstractController(Context appContext, WindowManager windowManager) {
        this.abstractRepository = new AbstractRepository(appContext);
    }

    public void initConfiguration(Context appContext, ICallback.OnRequest callback) {
        fetch(appContext, RequestType.CONFIGURATION, callback);
    }

    public void fetch(Context appContext, RequestType type, ICallback.OnRequest callback) {
        fetch(appContext, type, callback, -1);
    }

    public void fetch(Context appContext, RequestType type, ICallback.OnRequest callback, int page) {
        fetch(appContext, type, callback, page, null);
    }

    public void fetch(Context appContext, RequestType type, ICallback.OnRequest callback, int page, HashMap<String, String> map) {
        abstractRepository.fetchData(appContext, type, callback, page, map);
    }
}
