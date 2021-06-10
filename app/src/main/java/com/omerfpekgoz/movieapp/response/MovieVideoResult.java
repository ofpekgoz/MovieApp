package com.omerfpekgoz.movieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideoResult {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<MovieVideoResponse> movieVideoResponses = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieVideoResponse> getMovieVideoResponses() {
        return movieVideoResponses;
    }

    public void setMovieVideoResponses(List<MovieVideoResponse> movieVideoResponses) {
        this.movieVideoResponses = movieVideoResponses;
    }


}
