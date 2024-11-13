package de.otto.springboottutorium.repositories

import de.otto.springboottutorium.model.Movie
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MovieRepositoryInMemory : MovieRepository {
    var movies : MutableMap<UUID, Movie> = mutableMapOf();

    override fun addMovies(movies: List<Movie>) {
        movies.forEach(this::addMovie);
    }

    override fun addMovie(movie: Movie) {
        movies[movie.id] = movie;
    }

    override fun removeMovie(id: UUID) {
        movies.remove(id);
    }

    override fun updateMovie(movie: Movie) {
        movies[movie.id] = movie;
    }

    override fun getAllMovies(query: Query?): List<Movie> {
        return movies.values.toList();
    }

    override fun getMovie(movieId: UUID): Movie? {
        return movies[movieId];
    }
}