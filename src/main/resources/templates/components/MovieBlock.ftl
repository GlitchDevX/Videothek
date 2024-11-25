<#macro component movie>
    <div class="movie-block-wrapper">
        <div class="movie-block-info-container">
            <div class="movie-block-left-container">
                <a href="/movie/${movie.id}" class="movie-block-title">${movie.title}</a>
                <a href="/allMovies?filters=Genre:${movie.genre}" class="movie-block-genre">${movie.genre}</a>
                <div class="movie-block-length">
                    <img src="https://www.svgrepo.com/show/521888/time.svg" alt="time icon" height="18px">
                    <span>${movie.minutesLength}</span>
                </div>
            </div>
            <div class="movie-block-right-container">
                <#if movie.lendStatus == "Free">
                <div class="movie-available-wrapper">
                    <img src="https://www.svgrepo.com/show/532154/check.svg" alt="Check Icon" height="18px">
                    Available
                </div>
                </#if>
                <span class="movie-block-year">${movie.releaseYear?c}</span>
            </div>
        </div>
        <p class="movie-block-description">${movie.description}</p>
    </div>
</#macro>