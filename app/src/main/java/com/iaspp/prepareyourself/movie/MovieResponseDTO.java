package com.iaspp.prepareyourself.movie;

import com.google.gson.annotations.SerializedName;
import com.iaspp.prepareyourself.interfaces.IDTO;

import java.util.List;

public class MovieResponseDTO implements IDTO {

    private int page;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("results")
    private List<MovieDTO> resultList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<MovieDTO> getResultList() {
        return resultList;
    }

    public void setResultList(List<MovieDTO> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "Page: " + page + "/" + totalPages + " " + totalResults + " movies " + " results: " + resultList;
    }
}
