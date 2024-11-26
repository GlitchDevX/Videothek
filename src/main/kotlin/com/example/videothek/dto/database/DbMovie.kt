package com.example.videothek.dto.database

import com.example.videothek.model.LendStatus
import com.example.videothek.model.Movie
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.util.*

@Document(collection = "movie")
class DbMovie (
    @MongoId
    val id: UUID,

    @Indexed
    val title: String,

    val description: String,

    @Indexed
    val genre: String,

    val lendStatus: LendStatus,

    var minutesLength: Int,

    val releaseYear: Int,
) {
    fun toMovie(): Movie {
        return Movie(id, title, description, genre, lendStatus, minutesLength, releaseYear);
    }
}