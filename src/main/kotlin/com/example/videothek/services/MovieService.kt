package com.example.videothek.services

import com.example.videothek.dto.request.MovieRequest
import com.example.videothek.exceptions.NotFoundException
import com.example.videothek.exceptions.NotFreeException
import com.example.videothek.model.*
import com.example.videothek.repositories.MovieRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieService (
    @Qualifier("movieRepositoryMongoDb")
    private val movieRepository: MovieRepository,
    @Value("\${com.example.videothek.max-movies}")
    private val maxMovies: Int,
) {
    private val log: Logger = LoggerFactory.getLogger(MovieService::class.java)

    fun addMovies(requestMovies: Array<MovieRequest>): List<UUID> {
        val movies = requestMovies.map { m -> Movie(UUID.randomUUID(), m.title, m.description, m.genre, m.lendStatus, m.minutesLength, m.releaseYear); };
        movieRepository.addMovies(movies);
        log.info("Created movies with titles [{}]", movies.joinToString(", ") { it.title });
        return movies.map { m -> m.id };
    }

    fun addMovie(title: String, description: String, genre: String, lendStatus: LendStatus, minutesLength: Int, releaseYear: Int): UUID {
        val movie = Movie(UUID.randomUUID(), title, description, genre, lendStatus, minutesLength, releaseYear);
        movieRepository.addMovie(movie);
        log.info("Created movie with title '{}'", title);
        return movie.id;
    }

    fun removeMovie(id: UUID) {
        val success = movieRepository.removeMovie(id);
        if (!success) {
            log.warn("Movie with id '{}', was not found, while trying to delete it.", id);
            throw NotFoundException("Movie not found");
        }
        else log.info("Deleted movie with id '{}'.", id);
    }

    fun updateMovie(id: UUID, title: String, description: String, genre: String, lendStatus: LendStatus, minutesLength: Int, releaseYear: Int) {
        val movie = Movie(id, title, description, genre, lendStatus, minutesLength, releaseYear)
        movieRepository.updateMovie(movie);
        log.info("Updated movie with id '{}'.", id);
    }

    fun getAllMovies(filters: Array<String>?, sort: Sort): List<Movie> {
        val filterList = extractFilters(filters);
        val query = MovieHelper.generateFilterQuery(filterList);

        val movies = movieRepository.getAllMovies(maxMovies, query);
        val sortedMovies = MovieHelper.sortMovies(movies, sort);
        log.debug("Get all movies with filters: ({}) and sort ({}).", (filters?.joinToString(", ")), sort.sortType.toString() + ", " + sort.sortDirection.toString());
        return sortedMovies;
    }

    fun getMovieById(id: UUID): Movie {
        val movie = movieRepository.getMovie(id)
        return if (movie != null) movie
        else {
            log.warn("Movie with id '{}' not found, while trying to get it.", id);
            throw NotFoundException("Movie with id '$id' not found");
        }
    }

    fun lendMovie(id: UUID) {
        val movie = getMovieById(id);
        if (movie.lendStatus != LendStatus.Free) {
            throw NotFreeException("The movie was already been lent.")
        }
        movie.lendStatus = LendStatus.Taken;
        movieRepository.updateMovie(movie);
        log.info("Movie with id '{}' was lend", id);
    }

    fun returnMovie(id: UUID) {
        val movie = getMovieById(id);
        movie.lendStatus = LendStatus.Free;
        movieRepository.updateMovie(movie);
        log.info("Movie with id '{}' was returned", id);
    }

    fun searchMovie(title: String): List<Movie> {
        log.info("Search movie(s) with title containing '{}' ", title);
        return movieRepository.search(title);
    }

    private fun extractFilters(filters: Array<String>?): List<Filter> {
        return filters?.map { f -> Filter(
                com.example.videothek.model.FilterType.entries.find { it.name == f.split(":")[0] },
                f.split(":").getOrNull(1));
            }
            ?: listOf();
    }
}