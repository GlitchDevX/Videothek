<#macro component movie>
    <div class="movie-block-wrapper">
        <div class="movie-block-title-info-wrapper">
            <a href="/${movie.id}" class="movie-block-title">${movie.title}</a>
            <a href="/allMovies?filters=Genre:${movie.genre}" class="movie-block-genre">${movie.genre}</a>
            <div class="movie-block-length">
                <img src="https://www.svgrepo.com/show/521888/time.svg" alt="time icon">
                <span>${movie.minutesLength}</span>
            </div>
            <span class="movie-block-year">${movie.releaseYear?c}</span>
        </div>
        <p class="movie-block-description">${movie.description}</p>
    </div>
</#macro>