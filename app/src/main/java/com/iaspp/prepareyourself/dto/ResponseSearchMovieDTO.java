package com.iaspp.prepareyourself.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSearchMovieDTO extends AbstractResponseDTO {
    @SerializedName("results")
    private List<ResultSearchMovieDTO> resultList;

    public List<ResultSearchMovieDTO> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultSearchMovieDTO> resultList) {

    }
}
