package com.iaspp.prepareyourself.genre;

import com.iaspp.prepareyourself.interfaces.IDTO;

import java.util.List;

public class GenreResponseDTO implements IDTO {
    private List<GenreDTO> genres;

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }
}
