package de.otto.springboottutorium.services

import de.otto.springboottutorium.dto.request.MovieRequest
import de.otto.springboottutorium.exceptions.NotFoundException
import de.otto.springboottutorium.exceptions.NotFreeException
import de.otto.springboottutorium.model.*
import de.otto.springboottutorium.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MovieService (
    @Qualifier("movieRepositoryMongoDb")
    private val movieRepository: MovieRepository
) {
    fun addMovies(requestMovies: Array<MovieRequest>): List<UUID> {
        val movies = requestMovies.map { m -> Movie(UUID.randomUUID(), m.title, m.description, m.genre, m.lendStatus, m.minutesLength, m.releaseYear); };
        movieRepository.addMovies(movies);
        return movies.map { m -> m.id };
    }
    fun addMovie(title: String, description: String, genre: String, lendStatus: LendStatus, minutesLength: Int, releaseYear: Int): UUID {
        val movie = Movie(UUID.randomUUID(), title, description, genre, lendStatus, minutesLength, releaseYear);
        movieRepository.addMovie(movie);
        return movie.id;
    }
    fun removeMovie(id: UUID) {
        movieRepository.removeMovie(id);
    }
    fun updateMovie(id: UUID, title: String, description: String, genre: String, lendStatus: LendStatus, minutesLength: Int, releaseYear: Int) {
        val movie = Movie(id, title, description, genre, lendStatus, minutesLength, releaseYear)
        movieRepository.updateMovie(movie);
    }

    fun getAllMovies(filters: List<Filter>, sort: Sort): List<Movie> {
        val query = MovieHelper.generateFilterQuery(filters);
        val movies = movieRepository.getAllMovies(query);
        val sortedMovies = MovieHelper.sortMovies(movies, sort);
        return sortedMovies;
    }
    fun getMovieById(id: UUID): Movie {
        return movieRepository.getMovie(id) ?:
            throw NotFoundException("Movie with id '$id' not found");
    }

    fun lendMovie(id: UUID) {
        val movie = getMovieById(id);
        if (movie.lendStatus != LendStatus.Free) {
            throw NotFreeException("The movie was already been lent.")
        }
        movie.lendStatus = LendStatus.Taken;
        movieRepository.updateMovie(movie);
    }
    fun returnMovie(id: UUID) {
        val movie = getMovieById(id);
        movie.lendStatus = LendStatus.Free;
        movieRepository.updateMovie(movie);
    }
}