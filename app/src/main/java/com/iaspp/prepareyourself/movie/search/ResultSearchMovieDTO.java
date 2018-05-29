package com.iaspp.prepareyourself.movie.search;

import com.iaspp.prepareyourself.movie.upcoming.ResultMovieUpcomingDTO;

public class ResultSearchMovieDTO extends ResultMovieUpcomingDTO {
    private String overview;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
