package de.otto.springboottutorium.repositories

import de.otto.springboottutorium.model.Movie
import org.springframework.data.mongodb.core.query.Query
import java.util.*

interface MovieRepository {
    // high authorization endpoints
    fun addMovies(movies: List<Movie>)
    fun addMovie(movie: Movie);
    fun removeMovie(id: UUID);
    fun updateMovie(movie: Movie);

    // unauthorized endpoints
    fun getAllMovies(query: Query?): List<Movie>;
    fun getMovie(movieId: UUID): Movie?;
}