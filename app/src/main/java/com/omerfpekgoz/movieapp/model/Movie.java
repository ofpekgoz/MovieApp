package com.omerfpekgoz.movieapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer movieId;
    @SerializedName("title")
    @Expose
    private String movieTitle;
    @SerializedName("poster_path")    //Poster
    @Expose
    private String moviePosterPath;
    @SerializedName("release_date")   //Çıkış Tarihi
    @Expose
    private String movieReleaseDate;
    @SerializedName("vote_average")   //Oy Ortalaması
    @Expose
    private Float movieVoteAverage;
    @SerializedName("overview")   //Genel Bakış
    @Expose
    private String movieOverview;
    @SerializedName("original_language")   //Genel Bakış
    @Expose
    private String original_language;
    @SerializedName("backdrop_path")   //Genel Bakış
    @Expose
    private String backdrop_path;

    public Movie() {
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public Float getMovieVoteAverage() {
        return movieVoteAverage;
    }

    public void setMovieVoteAverage(Float movieVoteAverage) {
        this.movieVoteAverage = movieVoteAverage;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", moviePosterPath='" + moviePosterPath + '\'' +
                ", movieReleaseDate='" + movieReleaseDate + '\'' +
                ", movieVoteAverage=" + movieVoteAverage +
                ", movieOverview='" + movieOverview + '\'' +
                ", original_language='" + original_language + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                '}';
    }
}
