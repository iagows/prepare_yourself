package com.iaspp.prepareyourself.dto;

public class SearchResultDTO extends MovieResultDTO {
    private String overview;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
