
package com.omerfpekgoz.movieapp.network;


import com.omerfpekgoz.movieapp.service.IMovieDao;

public class APIUtils {

    public static final String BASE_URL = "https://api.themoviedb.org/";

    public static final String API_KEY = "0307c4edab2bfd30c576d9972a43bf2a";

    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500/";


    public static IMovieDao getMovieDao() {

        return RetrofitClient.getClient(BASE_URL).create(IMovieDao.class);

    }

}
