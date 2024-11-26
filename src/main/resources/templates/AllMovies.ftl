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
                    <input type="checkbox" checked onclick="toggleAvailable(false)">
                <#else>
                    <input type="checkbox" onclick="toggleAvailable(true)">
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

<script>
function toggleAvailable(value) {
    console.log(value ? "Available" : "All");
    console.log(window.location)

    const {sortRaw, filtersArray } = getUrlParts();

    const availableFilterIndex = filtersArray.findIndex(f => f === "Available");
    if (availableFilterIndex !== -1) {
        filtersArray[availableFilterIndex] = "";
    }
    else {
        filtersArray.push("Available");
    }

    const queryNew = mergeFiltersRawSort(filtersArray, sortRaw);

    const newUrl = window.location.href + queryNew;
    window.location.replace(newUrl);
}
function getUrlParts() {
    const queryRaw = window.location.search;
    const queryTokens = queryRaw.substring(1).split("&");

    let sortType = queryTokens.find(s => s.includes("sort"));
    if (sortType === "") {
        sortType = undefined;
    }
    let sortDirection = queryTokens.find(s => s.includes("sortDirection"));
    if (sortDirection === "") {
        sortDirection = undefined;
    }
    const sortRaw = [sortType, sortDirection].join("&");

    const filtersRaw = queryTokens.find(s => s.includes("filters=")).substring(8);
    const filters = filtersRaw ? filtersRaw.split(",") : [];

    return {
        sortRaw: sortRaw,
        sortType: sortType,
        sortDirection: sortDirection,
        filtersRaw: filtersRaw,
        filtersArray: filters
    }
}
function mergeFiltersRawSort(filters, rawSort) {
    if (filters[0] !== "") {
        filters[0] += "?";
    }

    console.log(filters)
    console.log(rawSort)

    return [filters, rawSort].join("&");
}
function mergeUrlParts(filters, sortType, sortDirection) {
    let sort = [sortType, sortDirection].join("&")
    // if (sort === "") {
    //     sort = undefined
    // }

    if (filters !== "" || filters === undefined) {
        filters = "?filters=" + filters;
    }

    return [filters, sort].join("&");
}
</script>

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