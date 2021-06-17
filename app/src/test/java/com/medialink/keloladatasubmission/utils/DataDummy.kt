package com.medialink.keloladatasubmission.utils

import com.medialink.keloladatasubmission.data.source.local.entity.MovieDetailEntity
import com.medialink.keloladatasubmission.data.source.local.entity.TvDetailEntity

object DataDummy {

    fun genMovieList(): List<MovieDetailEntity> {

        val movies = ArrayList<MovieDetailEntity>()

        movies.add(
            MovieDetailEntity(
                id = 1,
                backdropPath = "/backdrop",
                adult = false,
                title = "Bird Box",
                originalTitle = "Bird Box",
                originalLanguage = "en",
                releaseDate = "2018-12-13",
                overview = "Five years after an ominous unseen presence drives most of society to suicide, a survivor and her two children make a desperate bid to reach safety.",
                posterPath = "https://thedisplay.net/wp-content/uploads/2019/01/bb1.jpg",
                popularity = 34.439,
                voteAverage = 6.9,
                voteCount = 7558,
                isFavorite = true,
                isDetail = true
            )
        )
        movies.add(
            MovieDetailEntity(
                id = 2,
                backdropPath = "/backdrop",
                adult = false,
                title = "Title 2",
                originalTitle = "Bird Box",
                originalLanguage = "en",
                releaseDate = "2018-12-13",
                overview = "Five years after an ominous unseen presence drives most of society to suicide, a survivor and her two children make a desperate bid to reach safety.",
                posterPath = "https://thedisplay.net/wp-content/uploads/2019/01/bb1.jpg",
                popularity = 34.439,
                voteAverage = 6.9,
                voteCount = 7558,
                isFavorite = true,
                isDetail = true
            )
        )

        return movies
    }

    fun genTvList(): List<TvDetailEntity> {
        return arrayListOf(
            TvDetailEntity(
                id = 1,
                backdropPath = "/backdropPath",
                firstAirDate = "2021-01-01",
                homepage = "homepage",
                inProduction = true,
                lastAirDate = "2021-12-31",
                name = "tv serial satu",
                numberOfEpisodes = 2,
                numberOfSeasons = 3,
                originalLanguage = "en",
                originalName = "original tv satu",
                overview = "overview tv satu",
                popularity = 100.0,
                posterPath = "/poster",
                status = "status",
                tagline = "tagline",
                type = "type",
                voteAverage = 5.5,
                voteCount = 100,
                isFavorite = true,
                isDetail = true
            ),
            TvDetailEntity(
                id = 2,
                backdropPath = "/backdropPath",
                firstAirDate = "2021-01-01",
                homepage = "homepage",
                inProduction = true,
                lastAirDate = "2021-12-31",
                name = "tv serial dua",
                numberOfEpisodes = 2,
                numberOfSeasons = 3,
                originalLanguage = "en",
                originalName = "original tv dua",
                overview = "overview tv dua",
                popularity = 100.0,
                posterPath = "/poster",
                status = "status",
                tagline = "tagline",
                type = "type",
                voteAverage = 5.5,
                voteCount = 100,
                isFavorite = true,
                isDetail = true
            )
        )
    }
}