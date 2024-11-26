package com.example.videothek.model

import com.example.videothek.dto.database.DbMovie
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.util.*

@Document(collection = "movie")
data class Movie (
    @MongoId
    val id: UUID,
    val title: String,
    val description: String,
    val genre: String,
    var lendStatus: LendStatus,
    var minutesLength: Int,
    val releaseYear: Int,
) {
    fun toDbMovie(): DbMovie {
        return DbMovie(
            id,
            title,
            description,
            genre,
            lendStatus,
            minutesLength,
            releaseYear
        );
    }
}