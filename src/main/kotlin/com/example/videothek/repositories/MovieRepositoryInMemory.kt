package com.example.videothek.repositories

import com.example.videothek.model.Movie
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

    override fun removeMovie(id: UUID): Boolean {
        return movies.remove(id) != null;
    }

    override fun updateMovie(movie: Movie) {
        movies[movie.id] = movie;
    }

    override fun getAllMovies(limit: Int, query: Query?): List<Movie> {
        return movies.values.toList().slice(IntRange(0, limit));
    }

    override fun getMovie(movieId: UUID): Movie? {
        return movies[movieId];
    }

    override fun search(title: String): List<Movie> {
        val matches: MutableList<Movie> = mutableListOf();
        movies.values.forEach { m ->
            if (m.title.contains(title)) {
                matches.add(m);
            }
        }
        return matches;
    }
}