package com.iaspp.prepareyourself.genre;

import android.content.Context;
import android.view.WindowManager;

import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.interfaces.IDTO;
import com.iaspp.prepareyourself.utils.AbstractController;
import com.iaspp.prepareyourself.utils.RequestType;

import java.util.List;

public class GenreController extends AbstractController {
    private List<GenreDTO> genres;

    public GenreController(Context appContext, WindowManager windowManager) {
        super(appContext, windowManager);
    }

    public void saveGenres(IDTO dto) {
        this.genres = ((GenreResponseDTO)dto).getGenres();
    }

    public String getGenre(int id){
        for(GenreDTO genre : this.genres){
            if(genre.getId() == id){
                return genre.getName();
            }
        }
        return "";
    }

    public void getGenres(Context appContent, ICallback.OnRequest callback){
        fetch(appContent, RequestType.GENRES, callback);
    }
}
