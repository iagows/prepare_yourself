package com.iaspp.prepareyourself.movie;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.iaspp.prepareyourself.config.TMDbConfig;
import com.iaspp.prepareyourself.config.TMDbImagesConfig;
import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.utils.RequestType;

import java.util.HashMap;
import java.util.List;

public class MovieController {

    private MovieRepository repository;
    private TMDbConfig config;
    private int upComingPage;
    private int width;

    public MovieController(Context appContext, WindowManager windowManager) {
        this.repository = new MovieRepository(appContext);
        setWidth(windowManager);
        this.resetUpcoming();
    }

    private void setWidth(WindowManager manager) {
        Display display = manager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.width = size.x;
    }

    public String getImageUrl(MovieDTO dto) {
        TMDbImagesConfig images = config.getImages();
        String max = "";
        String image = "";
        if (dto.getPosterPath() != null) {
            max = getMaxSize(images.getPosterSizes());
            image = dto.getPosterPath();
        } else {
            max = getMaxSize(images.getBackdropSizes());
            image = dto.getBackdropPath();
        }

        return images.getBase() + max + image;
    }

    private String getMaxSize(List<String> list) {
        String best = "";
        for (String w : list) {
            int width;
            try {
                width = Integer.parseInt(w.substring(1));
                if (width > getWidth()) {
                    break;
                }
                best = w;
            } catch (Exception e) {
                // just continue
            }
        }
        return best;
    }

    public int getWidth() {
        return this.width;
    }

    public void initConfiguration(Context appContext, ICallback.OnRequest callback) {
        fetch(appContext, RequestType.CONFIGURATION, callback);
    }

    public void getUpcoming(Context appContent, ICallback.OnRequest callback) {
        fetch(appContent, RequestType.UPCOMING, callback, this.upComingPage++);
    }

    public void resetUpcoming() {
        this.upComingPage = 1;
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
