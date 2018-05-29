package com.iaspp.prepareyourself.movie;

import com.google.gson.annotations.SerializedName;
import com.iaspp.prepareyourself.interfaces.IDTO;

public abstract class AbstractResponseDTO implements IDTO {

    private int page;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;

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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Page: ");
        sb.append(page);
        sb.append("/");
        sb.append(totalPages);
        sb.append(" ");
        sb.append(totalResults);
        sb.append("movies");

        return sb.toString();
    }
}
