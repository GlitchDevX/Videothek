package com.example.videothek.controller

import com.example.videothek.dto.request.IdMovieRequest
import com.example.videothek.dto.request.MovieRequest
import com.example.videothek.dto.request.MoviesRequest
import com.example.videothek.dto.response.*
import com.example.videothek.model.*
import com.example.videothek.services.MovieService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api", produces = [MediaType.APPLICATION_JSON_VALUE])
class MovieController (private val movieService: MovieService) {

    @PostMapping("/addMultiple")
    fun addMultipleMovies(@Valid @RequestBody movies: MoviesRequest): ResponseEntity<IdsResponse> {
        val ids = movieService.addMovies(movies.movies);
        return ResponseEntity(IdsResponse(ids), HttpStatus.CREATED)
    }

    @PostMapping("/add")
    fun addMovie(@Valid @RequestBody movie: MovieRequest): ResponseEntity<OpenResponse> {
        val id = movieService.addMovie(movie.title, movie.description, movie.genre, movie.lendStatus, movie.minutesLength, movie.releaseYear);
        return ResponseEntity(IdResponse(id), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    fun removeMovie(@Valid @PathVariable("id") id: UUID): ResponseEntity<OpenResponse> {
        movieService.removeMovie(id);
        return ResponseEntity(OpenResponse(), HttpStatus.OK);
    }

    @PatchMapping("/update")
    fun updateMovie(@Valid @RequestBody movie: IdMovieRequest): ResponseEntity<OpenResponse> {
        movieService.updateMovie(movie.id, movie.title, movie.description, movie.genre, movie.lendStatus, movie.minutesLength, movie.releaseYear);
        return ResponseEntity(OpenResponse(), HttpStatus.OK);
    }

    @GetMapping("/all")
    fun getAllMovies(
        @Valid @RequestParam("filters") filters: Array<String>?,
        @Valid @RequestParam("sort") sortType: SortType?,
        @Valid @RequestParam("sortDirection") sortDirection: SortDirection?
    ): ResponseEntity<MoviesResponse> {
        val sort = Sort(sortType, sortDirection);

        val allMovies = movieService.getAllMovies(filters, sort);
        return ResponseEntity(MoviesResponse(allMovies), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    fun getMovie(@Valid @PathVariable("id") id: UUID): ResponseEntity<MovieResponse> {
        val movie = movieService.getMovieById(id);
        return ResponseEntity(MovieResponse(movie), HttpStatus.OK);
    }

    @PatchMapping("/lend/{id}")
    fun lendMovie(@Valid @PathVariable("id") movieId: UUID): ResponseEntity<OpenResponse> {
        movieService.lendMovie(movieId);
        return ResponseEntity(OpenResponse(), HttpStatus.OK);
    }

    @PatchMapping("/return/{id}")
    fun returnMovie(@Valid @PathVariable("id") movieId: UUID): ResponseEntity<OpenResponse> {
        movieService.returnMovie(movieId);
        return ResponseEntity(OpenResponse(), HttpStatus.OK);
    }
}