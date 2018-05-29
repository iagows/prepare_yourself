package com.iaspp.prepareyourself.movie.upcoming;

import com.google.gson.annotations.SerializedName;
import com.iaspp.prepareyourself.movie.AbstractResponseDTO;

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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());

        sb.append(resultList);

        return sb.toString();
    }

}
