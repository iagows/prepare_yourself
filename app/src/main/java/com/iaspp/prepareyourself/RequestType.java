package com.iaspp.prepareyourself;

public enum RequestType {
    SEARCH_MOVIE("search/movie"),
    UPCOMING("movie/upcoming");

    private final String type;

    RequestType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
