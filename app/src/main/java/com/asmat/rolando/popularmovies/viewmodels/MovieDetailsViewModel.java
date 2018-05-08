package com.asmat.rolando.popularmovies.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.asmat.rolando.popularmovies.database.FavoriteMovie;
import com.asmat.rolando.popularmovies.database.Movie;
import com.asmat.rolando.popularmovies.managers.MoviesRepository;
import com.asmat.rolando.popularmovies.models.Review;
import com.asmat.rolando.popularmovies.models.Video;

public class MovieDetailsViewModel extends ViewModel {
    private int movieID;
    private LiveData<Movie> movie;
    private LiveData<FavoriteMovie> favoriteMovie;
    private LiveData<Video[]> videos;
    private LiveData<Review[]> reviews;

    public void init(int movieID) {
        this.movieID = movieID;
        initLiveData();
    }

    // Setup LiveData
    private void initLiveData() {
        movie = MoviesRepository.getMovie(movieID);
        favoriteMovie = MoviesRepository.getFavoriteMovie(movieID);
        videos = MoviesRepository.getVideos(movieID);
        reviews = MoviesRepository.getReviews(movieID);
    }

    // Getters

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public LiveData<FavoriteMovie> getFavoriteMovie() {
        return favoriteMovie;
    }

    public LiveData<Video[]> getVideos() {
        return videos;
    }

    public LiveData<Review[]> getReviews() {
        return reviews;
    }
}