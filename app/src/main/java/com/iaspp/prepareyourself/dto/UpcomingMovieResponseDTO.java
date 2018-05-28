package com.iaspp.prepareyourself.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingMovieResponseDTO extends AbstractResponse {
    @SerializedName("results")
    private List<MovieResultDTO> resultList;


    public List<MovieResultDTO> getResultList() {
        return resultList;
    }

    public void setResultList(List<MovieResultDTO> resultList) {
        this.resultList = resultList;
    }
}
