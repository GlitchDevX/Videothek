package de.otto.springboottutorium.controller

import de.otto.springboottutorium.model.*
import de.otto.springboottutorium.services.MovieService
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
@RequestMapping(produces = [MediaType.TEXT_HTML_VALUE])
class MovieUiController (
    private val movieService: MovieService
) {

    @GetMapping("/allMovies")
    fun test(model: Model,
             @Valid @RequestParam("filters") filters: Array<String>?,
             @Valid @RequestParam("sort") sortType: SortType?,
             @Valid @RequestParam("sortDirection") sortDirection: SortDirection?
    ): String {
        val sort = Sort(sortType, sortDirection);

        val allMovies = movieService.getAllMovies(filters, sort);
        model.addAttribute("allMovies", allMovies);

        return "AllMovies";
    }
}