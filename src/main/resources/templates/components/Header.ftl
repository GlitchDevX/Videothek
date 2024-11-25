<div class="header-wrapper">
    <a href="/" class="logo-wrapper">
        <span class="first-logo-part">Bit</span>
        <span class="second-logo-part">Buster</span>
    </a>
    <div class="header-link-search-wrapper">
        <a href="/allMovies" class="header-link">All Movies</a>
        <form action="/search" method="post">
            <input name="title" type="text" class="header-search-box" placeholder="Search a movie">
        </form>
    </div>
</div>

<style>
.header-wrapper {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    background: var(--t-blue);
}
.logo-wrapper {
    font-size: xx-large;
    font-weight: bolder;
    display: flex;
    justify-content: center;
    align-items: center;
    text-decoration: none;
}
.first-logo-part {
    color: var(--t-yellow);
}
.second-logo-part {
    color: #fff;
}
.header-link-search-wrapper {
    display: flex;
    flex-direction: row;
    align-items: center;
    gap: 16px;
}
.header-search-box {
    border: 2px grey solid;
    background: #fff;
    padding: 6px 8px;
    border-radius: 12px;
    outline: none;
    font-weight: 600;
    width: 200px;
}
.header-search-box:focus-visible, .header-search-box:focus {
    border: 2px var(--t-yellow) solid;
}
.header-link {
    color: var(--t-yellow);
    text-decoration: none;
    font-weight: 600;
}
</style>