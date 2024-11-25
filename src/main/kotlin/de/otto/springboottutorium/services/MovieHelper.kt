package de.otto.springboottutorium.services

import de.otto.springboottutorium.model.*
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.util.UUID

class MovieHelper {
    companion object {
        fun getLendAction(lendStatus: LendStatus, movieId: UUID): LendAction {
            return when (lendStatus) {
                LendStatus.Free -> LendAction("Lend", "/lend/$movieId");
                LendStatus.Taken -> LendAction("Return", "/return/$movieId");
                LendStatus.Reserved -> LendAction("Free", "/return/$movieId");
            }
        }

        fun generateFilterQuery(filters: List<Filter>): Query {
            val query = Query()
            filters.forEach { f ->
                query.addCriteria(getCriteriaByFilter(f))
            }
            return query;
        }

        private fun getCriteriaByFilter(filter: Filter): Criteria {
            return when (filter.filterType) {
                FilterType.Genre
                    -> Criteria.where("genre").`is`(filter.filterValue)
                FilterType.Available
                    -> Criteria.where("lendStatus").`is`("Free")
                else -> {
                    Criteria()
                }
            }
        }

        fun sortMovies(movies: List<Movie>, sort: Sort): List<Movie> {
            val sortedMovies = when (sort.sortType) {
                SortType.Title
                        -> movies.sortedBy { it.title }
                SortType.Genre
                    -> movies.sortedBy { it.genre }
                SortType.ReleaseYear
                        -> movies.sortedBy { it.releaseYear }
                SortType.Minutes
                        -> movies.sortedBy { it.minutesLength }
                else -> movies
            }
            return if (sort.sortDirection == SortDirection.Descending) sortedMovies.reversed() else sortedMovies;
        }
    }
}