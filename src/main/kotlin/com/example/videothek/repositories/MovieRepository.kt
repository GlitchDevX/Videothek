package com.example.videothek.repositories

import com.example.videothek.model.Movie
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.mongodb.core.query.Query
import java.util.*

interface MovieRepository {
    // high authorization endpoints
    fun addMovies(movies: List<Movie>)
    fun addMovie(movie: Movie);
    fun removeMovie(id: UUID): Boolean;
    fun updateMovie(movie: Movie);

    // unauthorized endpoints
    fun getAllMovies(limit: Int, query: Query?): List<Movie>;
    fun getMovie(movieId: UUID): Movie?;
    fun search(title: String): List<Movie>;
}