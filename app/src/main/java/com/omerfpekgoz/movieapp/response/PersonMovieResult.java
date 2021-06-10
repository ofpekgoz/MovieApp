package com.omerfpekgoz.movieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonMovieResult {

    @SerializedName("cast")
    @Expose
    private List<PersonMovieCastResponse> cast = null;

    public List<PersonMovieCastResponse> getCast() {
        return cast;
    }

    public void setCast(List<PersonMovieCastResponse> cast) {
        this.cast = cast;
    }



}
