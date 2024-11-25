<#import "Base.ftl" as base>
<#import "components/MovieBlock.ftl" as movieBlock>

<@base.layout>
    <h1 class="page-title">
        All movies:
    </h1>
    <h3>Filters & Sort</h3>
    <div class="filter-sort-wrapper">
        <div class="filter-wrapper">
            <label for="genres">
                Genre
            </label>
            <select name="genres" id="genres">
                <#list genres as genre>
                    <option value="${genre}">
                        ${genre}
                    </option>
                </#list>
            </select>
            <label>
                <#if filters?contains("Available")>
                    <input type="checkbox" checked>
                <#else>
                    <input type="checkbox">
                </#if>
                Available
            </label>
        </div>
        <div class="sort-wrapper">
            <label for="sortTypes">
                Sort By
            </label>
            <select name="sortTypes" id="sortTypes">
                <#list sortTypes as sortType>
                    <option value="${sortType}">
                        ${sortType}
                    </option>
                </#list>
            </select>
            <a>
                <#if sort?? || sort.sortDirection == "Ascending">
                    <img src="https://www.svgrepo.com/show/532208/sort-amount-down.svg" height="18px" alt="">
                <#else>
                    <img src="https://www.svgrepo.com/svg/532213/sort-amount-up" height="18px" alt="">
                </#if>
            </a>
        </div>
    </div>
    <div class="movies-wrapper">
    <#list allMovies as movie>
        <@movieBlock.component movie=movie/>
    </#list>
    </div>
</@base.layout>

<style>
.movies-wrapper {
    display: flex;
    flex-direction: column;
    gap: 8px;
}
.filter-sort-wrapper {
    display: flex;
    justify-content: space-between;
    align-content: flex-end;
}
</style>
