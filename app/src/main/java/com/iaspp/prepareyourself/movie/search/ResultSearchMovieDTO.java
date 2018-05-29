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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(super.toString());

        sb.append(" Overview: ");
        sb.append(overview.substring(0, 80));

        return sb.toString();
    }
}
