package com.example.videothek.repositories

import com.example.videothek.dto.database.DbMovie
import com.example.videothek.model.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MovieRepositoryMongoDb (
    @Autowired
    private val mongoTemplate: MongoTemplate
) : com.example.videothek.repositories.MovieRepository {
    private val MOVIE_CLASS = DbMovie::class.java;

    override fun addMovies(movies: List<Movie>) {
        mongoTemplate.insertAll(movies);
    }

    override fun addMovie(movie: Movie) {
        mongoTemplate.insert(movie.toDbMovie());
    }

    override fun removeMovie(id: UUID): Boolean {
        val query = Query(Criteria.where("id").`is`(id))
        val result = mongoTemplate.remove(query, MOVIE_CLASS);
        return result.deletedCount >= 1;
    }

    override fun updateMovie(movie: Movie) {
        mongoTemplate.save(movie.toDbMovie());
    }

    override fun getAllMovies(query: Query?): List<Movie> {
        return mongoTemplate.find(query ?: Query(), MOVIE_CLASS).map { m -> m.toMovie() };
    }

    override fun getMovie(movieId: UUID): Movie? {
        return mongoTemplate.findById(movieId, MOVIE_CLASS)?.toMovie();
    }

    override fun search(title: String): List<Movie> {
        val query = Query(Criteria.where("title").regex(title))
        return mongoTemplate.find(query, MOVIE_CLASS).map { m -> m.toMovie() };
    }
}