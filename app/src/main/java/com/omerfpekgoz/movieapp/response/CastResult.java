package com.omerfpekgoz.movieapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cast")
    @Expose
    private List<CastResponse> cast = null;
    @SerializedName("crew")
    @Expose
    private List<CrewResponse> crew = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CastResponse> getCast() {
        return cast;
    }

    public void setCast(List<CastResponse> cast) {
        this.cast = cast;
    }

    public List<CrewResponse> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewResponse> crew) {
        this.crew = crew;
    }
}
