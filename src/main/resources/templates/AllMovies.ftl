<#import "Base.ftl" as base>
<#import "components/MovieBlock.ftl" as movieBlock>

<@base.layout>
    <h1 class="page-title">
        All movies:
    </h1>
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