package com.iaspp.prepareyourself.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDTO <T extends MovieResultDTO>{
    private int page;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("results")
    private List<T> resultList;
}
