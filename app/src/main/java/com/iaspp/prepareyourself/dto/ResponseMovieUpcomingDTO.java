package com.iaspp.prepareyourself.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMovieUpcomingDTO extends AbstractResponseDTO {
    @SerializedName("results")
    private List<ResultMovieUpcomingDTO> resultList;

    public List<ResultMovieUpcomingDTO> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultMovieUpcomingDTO> resultList) {
        this.resultList = resultList;
    }

}
