package com.omerfpekgoz.movieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResult {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<MovieResponse> movieResponse = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<MovieResponse> getMovieResponse() {
        return movieResponse;
    }

    public void setMovieResponse(List<MovieResponse> movieResponse) {
        this.movieResponse = movieResponse;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return "MovieResult{" +
                "page=" + page +
                ", movieResponse=" + movieResponse +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
