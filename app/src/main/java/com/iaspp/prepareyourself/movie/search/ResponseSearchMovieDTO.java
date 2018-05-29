package com.iaspp.prepareyourself.movie.search;

import com.google.gson.annotations.SerializedName;
import com.iaspp.prepareyourself.movie.AbstractResponseDTO;

import java.util.List;

public class ResponseSearchMovieDTO extends AbstractResponseDTO {
    @SerializedName("results")
    private List<ResultSearchMovieDTO> resultList;

    public List<ResultSearchMovieDTO> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultSearchMovieDTO> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());

        sb.append(resultList);

        return sb.toString();
    }
}
