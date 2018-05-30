package com.iaspp.prepareyourself.utils;

public enum RequestType {
    SEARCH_MOVIE("search/movie"),
    UPCOMING("movie/upcoming"),
    CONFIGURATION("configuration"),
    GENRES("genre/movie/list");

    private final String type;

    RequestType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
