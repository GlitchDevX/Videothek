<#import "Base.ftl" as base>
<#import "components/MovieBlock.ftl" as movieBlock>

<@base.layout>
    <h1 class="page-title">
        All movies:
    </h1>
    <div class="movies-wrapper">
    <div class="ad-container">
        <img src="/bannerAd.png" alt="Sapphire Games Ad">
    </div>
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

    position: relative;
}
.ad-container {
    position: absolute;
    right: -240px;
    height: 100%;
}
.ad-container > img {
    height: 500px;
    border-radius: 20px;
    position: sticky;
    top: 10px;
}
</style>