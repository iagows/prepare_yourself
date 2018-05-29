package com.iaspp.prepareyourself.movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDTO {
    @SerializedName("movie_id")
    private int id;
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
    private String overview;

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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public String toString() {
        String out = "ID: " + id + " poster: " + posterPath + " backdrop: " + backdropPath + " title " + originalTitle + " release: " + releaseDate + " genres: " + genreList;

        if (overview != null && !overview.equals("")) {
            out = out + " Overview: " + overview.substring(0, overview.length() > 80 ? 80 : overview.length());
        }

        return out;
    }
}
