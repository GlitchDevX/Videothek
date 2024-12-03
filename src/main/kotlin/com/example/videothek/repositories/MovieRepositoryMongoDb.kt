package com.example.videothek.repositories

import com.example.videothek.dto.database.DbMovie
import com.example.videothek.model.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MovieRepositoryMongoDb (
    @Autowired
    private val mongoTemplate: MongoTemplate
) : MovieRepository {
    private val MOVIE_CLASS = DbMovie::class.java;

    @Caching(evict = [
        CacheEvict("movies", "search")
    ])
    override fun addMovies(movies: List<Movie>) {
        mongoTemplate.insertAll(movies);
    }

    @Caching(evict = [
        CacheEvict("movies", "search")
    ])
    override fun addMovie(movie: Movie) {
        mongoTemplate.insert(movie.toDbMovie());
    }

    @Caching(evict = [
        CacheEvict("movie", key = "#id"),
        CacheEvict("movies", "search")
    ])
    override fun removeMovie(id: UUID): Boolean {
        val query = Query(Criteria.where("id").`is`(id))
        val result = mongoTemplate.remove(query, MOVIE_CLASS);
        return result.deletedCount >= 1;
    }

    @Caching(evict = [
        CacheEvict("movie", key = "#movie.id"),
        CacheEvict("movies", "search")
    ])
    override fun updateMovie(movie: Movie) {
        mongoTemplate.save(movie.toDbMovie());
    }

    @Cacheable("movies")
    override fun getAllMovies(limit: Int, query: Query?): List<Movie> {
        val mappedQuery = query ?: Query()
        mappedQuery.limit(limit);
        return mongoTemplate.find(mappedQuery, MOVIE_CLASS).map { m -> m.toMovie() };
    }

    @Cacheable("movie", key = "#movieId")
    override fun getMovie(movieId: UUID): Movie? {
        return mongoTemplate.findById(movieId, MOVIE_CLASS)?.toMovie();
    }

    @Cacheable("search", key = "#title")
    override fun search(title: String): List<Movie> {
        val query = Query(Criteria.where("title").regex(title))
        return mongoTemplate.find(query, MOVIE_CLASS).map { m -> m.toMovie() };
    }
}