package com.iaspp.prepareyourself.movie;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.iaspp.prepareyourself.config.TMDbConfig;
import com.iaspp.prepareyourself.config.TMDbImagesConfig;
import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.interfaces.IDTO;
import com.iaspp.prepareyourself.utils.AbstractController;
import com.iaspp.prepareyourself.utils.RequestType;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;

public class MovieController extends AbstractController {
    private TMDbConfig config;
    private int width;
    private int totalMovies;

    public MovieController(Context appContext, WindowManager windowManager) {
        super(appContext, windowManager);
        setWidth(windowManager);
    }

    private void setWidth(WindowManager manager) {
        final Display display = manager.getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        this.width = size.x;
    }

    public void setImageUrl(MovieDTO dto) {
        final TMDbImagesConfig images = config.getImages();
        String max, image;
        if (StringUtils.isNotBlank(dto.getPosterPath())) {
            max = getMaxSize(images.getPosterSizes());
            image = dto.getPosterPath();
        } else if (StringUtils.isNotBlank(dto.getBackdropPath())) {
            max = getMaxSize(images.getBackdropSizes());
            image = dto.getBackdropPath();
        } else {
            max = image = "";
        }
        dto.setFullUrl(images.getBase() + max + image);
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

    private int getWidth() {
        return this.width;
    }

    public void getUpcoming(Context appContent, ICallback.OnRequest callback, int page) {
        fetch(appContent, RequestType.UPCOMING, callback, page);
    }

    public void getByName(Context appContext, ICallback.OnRequest callback, int page, String query){
        HashMap<String, String> map = new HashMap<>();
        map.put("query", query);
        fetch(appContext, RequestType.SEARCH_MOVIE, callback, page, map);
    }

    public TMDbConfig getConfig() {
        return config;
    }

    public void setConfig(TMDbConfig config) {
        this.config = config;
    }

    public void setTotalMovies(int totalMovies) {
        this.totalMovies = totalMovies;
    }

    public int getTotalMovies() {
        return totalMovies;
    }
}
