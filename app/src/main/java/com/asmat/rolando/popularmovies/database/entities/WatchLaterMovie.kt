package com.asmat.rolando.popularmovies.database.entities

import android.arch.persistence.room.*

@Entity(tableName = "watch_later_movies")
data class WatchLaterMovie(@ColumnInfo(name = "poster_path")
                           val posterPath: String?,
                           val overview: String,
                           @ColumnInfo(name = "release_date")
                           val releaseDate: String,
                           @PrimaryKey val id: Int,
                           val title: String,
                           @ColumnInfo(name = "backdrop_path")
                           val backdropPath: String?,
                           @ColumnInfo(name = "vote_average")
                           val voteAverage: Double)