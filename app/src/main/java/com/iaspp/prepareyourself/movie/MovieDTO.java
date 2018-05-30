package com.iaspp.prepareyourself.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.iaspp.prepareyourself.interfaces.IDTO;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO implements IDTO, Parcelable {
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

    private String genreWithComma;
    private String fullUrl;

    public static final Creator<MovieDTO> CREATOR = new Creator<MovieDTO>() {
        @Override
        public MovieDTO createFromParcel(Parcel in) {
            return new MovieDTO(in);
        }

        @Override
        public MovieDTO[] newArray(int size) {
            return new MovieDTO[size];
        }
    };

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

    public String getGenreWithComma() {
        return genreWithComma;
    }

    public void setGenreWithComma(String genreWithComma) {
        this.genreWithComma = genreWithComma;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    @Override
    public String toString() {
        String out = "ID: " + id + " poster: " + posterPath + " backdrop: " + backdropPath + " title " + originalTitle + " release: " + releaseDate + " genres: " + genreList;

        if (StringUtils.isNotBlank(overview)) {
            out = out + " Overview: " + overview.substring(0, overview.length() > 80 ? 80 : overview.length());
        }

        return out;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeString(originalTitle);
        dest.writeList(genreList);
        dest.writeString(releaseDate);
        dest.writeString(overview);
        dest.writeString(genreWithComma);
        dest.writeString(fullUrl);
    }

    private MovieDTO(Parcel in) {
        id = in.readInt();
        posterPath = in.readString();
        backdropPath = in.readString();
        originalTitle = in.readString();

        genreList = new ArrayList<>();
        in.readList(genreList, List.class.getClassLoader());
        releaseDate = in.readString();
        overview = in.readString();
        genreWithComma = in.readString();
        fullUrl = in.readString();
    }
}
