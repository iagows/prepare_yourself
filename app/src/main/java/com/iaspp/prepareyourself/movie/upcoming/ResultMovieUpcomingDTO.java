package com.iaspp.prepareyourself.movie.upcoming;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultMovieUpcomingDTO {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("genre_ids")
    private List<Integer> genreList;
    @SerializedName("release_date")
    private String releaseDate;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Integer> genreList) {
        this.genreList = genreList;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" Poster: ");
        sb.append(posterPath);
        sb.append(" Backdrop: ");
        sb.append(backdropPath);
        sb.append(" Title: ");
        sb.append(originalTitle);
        sb.append(" Release: ");
        sb.append(releaseDate);
        sb.append(" Genres: ");
        sb.append(genreList);

        return sb.toString();
    }
}
