package com.example.videothek.controller

import com.example.videothek.model.Sort
import com.example.videothek.model.SortDirection
import com.example.videothek.model.SortType
import com.example.videothek.services.MovieHelper
import com.example.videothek.services.MovieService
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@RequestMapping(produces = [MediaType.TEXT_HTML_VALUE])
class MovieUiController (
    private val movieService: MovieService
) {

    @GetMapping("/allMovies")
    fun getAllMovies(model: Model,
             @Valid @RequestParam("filters") filters: Array<String>?,
             @Valid @RequestParam("sort") sortType: SortType?,
             @Valid @RequestParam("sortDirection") sortDirection: SortDirection?
    ): String {
        val sort = Sort(sortType, sortDirection);

        val allMovies = movieService.getAllMovies(filters, sort);
        model.addAttribute("allMovies", allMovies);

        return "AllMovies";
    }

    @GetMapping("/movie/{id}")
    fun getMovie(model: Model, @Valid @PathVariable("id") id: UUID): String {
        val movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("action", MovieHelper.getLendAction(movie.lendStatus, movie.id));
        return "Movie";
    }

    @PostMapping("/lend/{id}", produces = ["text/plain"])
    fun lendMovie(@Valid @PathVariable("id") id: UUID): String {
        movieService.lendMovie(id);
        return "redirect:/movie/$id"
    }

    @PostMapping("/return/{id}", produces = ["text/plain"])
    fun returnMovie(@Valid @PathVariable("id") id: UUID): String {
        movieService.returnMovie(id);
        return "redirect:/movie/$id";
    }

    @PostMapping("/search")
    fun searchMovies(model: Model, @Valid @RequestParam("title") title: String, response: HttpServletResponse): String? {
        val movies = movieService.searchMovie(title);
        if (movies.count() == 1) {
            return "redirect:/movie/${movies.first().id}";
        }
        model.addAttribute("search", title);
        model.addAttribute("movies", movies);
        return "SearchResults"
    }
}