<#import "Base.ftl" as base>
<#import "components/MovieBlock.ftl" as movieBlock>

<@base.layout>
    <h1 class="page-title">
        Movies with the Title '${search}'
    </h1>
    <div class="movies-wrapper">
        <#list movies as movie>
            <@movieBlock.component movie=movie/>
        </#list>
        <#if movies?size == 0>
            <span>
                No Movies found.
            </span>
        </#if>
    </div>
</@base.layout>

<style>
    .movies-wrapper {
        display: flex;
        flex-direction: column;
        gap: 8px;
    }
</style>