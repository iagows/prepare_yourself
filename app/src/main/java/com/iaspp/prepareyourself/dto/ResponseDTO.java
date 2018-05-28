package com.iaspp.prepareyourself.dto;

import java.util.List;

public class ResponseDTO <T extends MovieResultDTO>{
    private int page;
    private int total_pages;
    private int total_results;
    private List<T> results;
}
