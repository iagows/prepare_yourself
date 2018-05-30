package com.iaspp.prepareyourself.config;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TMDbImagesConfig {
    @SerializedName("base_url")
    private String base;

    @SerializedName("secure_base_url")
    private String secureBase;

    @SerializedName("backdrop_sizes")
    private List<String> backdropSizes;

    @SerializedName("poster_sizes")
    private List<String> posterSizes;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSecureBase() {
        return secureBase;
    }

    public void setSecureBase(String secureBase) {
        this.secureBase = secureBase;
    }

    public List<String> getBackdropSizes() {
        return backdropSizes;
    }

    public void setBackdropSizes(List<String> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public List<String> getPosterSizes() {
        return posterSizes;
    }

    public void setPosterSizes(List<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BASE: ");
        sb.append(base);
        sb.append(" BACKDROP: ");
        sb.append(backdropSizes);
        sb.append(" POSTER: ");
        sb.append(posterSizes);
        return sb.toString();
    }
}
