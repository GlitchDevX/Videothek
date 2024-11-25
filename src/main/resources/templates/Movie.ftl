<#import "Base.ftl" as base>
<@base.layout>
    <h1 class="page-title">
        ${movie.title}
    </h1>
    <span class="movie-page-year">
        Release Year: ${movie.releaseYear?c}
    </span>
    <p>
        ${movie.description}
    </p>
    <div class="movie-page-info-lend-wrapper">
        <div class="movie-page-info-container">
            <a href="/allMovies?filters=Genre:${movie.genre}" class="movie-block-genre">${movie.genre}</a>
            <div class="movie-block-length">
                <img src="https://www.svgrepo.com/show/521888/time.svg" alt="time icon">
                <span>${movie.minutesLength}</span>
            </div>
            <#if movie.lendStatus == "Free">
            <div class="movie-available-wrapper">
                <img src="https://www.svgrepo.com/show/532154/check.svg" alt="Check Icon" height="18px">
                Available
            </div>
            </#if>
        </div>

        <form action="${action.url}" method="post">
            <input type="submit" value="${action.buttonText}" class="movie-page-lend-button">
        </form>
    </div>
</@base.layout>

<style>
.page-title {
    margin-bottom: -4px;
}
.movie-page-year {
    color: dimgrey;
    margin-bottom: 5px;
}
.movie-page-description-info-wrapper {
}
.movie-page-info-lend-wrapper {
    display: flex;
    justify-content: space-between;
    flex-direction: row;
}
.movie-page-info-container {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    gap: 8px;
}
.movie-page-lend-button {
    background-color: var(--t-yellow);
    color: #fff;
    font-weight: bold;
    font-size: large;

    border: none;
    padding: 6px;
    border-radius: 6px;

    cursor: pointer;
}
.movie-page-lend-button:hover {
    background-color: var(--t-yellow-h);
}
</style>