package com.medialink.keloladatasubmission.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_detail")
class TvDetailEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String?,

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String?,

    @ColumnInfo(name = "homepage")
    var homepage: String? = null,

    @ColumnInfo(name = "in_production")
    var inProduction: Boolean? = false,

    @ColumnInfo(name = "last_air_date")
    var lastAirDate: String? = null,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "next_episode_to_air")
    var nextEpisodeToAir: String? = null,

    @ColumnInfo(name = "number_of_episodes")
    var numberOfEpisodes: Int? = 0,

    @ColumnInfo(name = "number_of_seasons")
    var numberOfSeasons: Int? = 0,

    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null,

    @ColumnInfo(name = "original_name")
    var originalName: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "popularity")
    var popularity: Double?,

    @ColumnInfo(name = "poster_path")
    var posterPath: String?,

    @ColumnInfo(name = "status")
    var status: String? = null,

    @ColumnInfo(name = "tagline")
    var tagline: String? = null,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double?,

    @ColumnInfo(name = "vote_count")
    var voteCount: Int?,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "is_detail")
    var isDetail: Boolean = false
)