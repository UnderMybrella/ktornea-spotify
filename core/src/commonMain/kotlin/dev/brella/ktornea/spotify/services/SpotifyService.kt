package dev.brella.ktornea.spotify.services

interface SpotifyService : SpotifyAlbumService, SpotifyArtistService,
    SpotifyAudiobookService, SpotifyCategoryService, SpotifyChapterService,
    SpotifyEpisodeService, SpotifyGenreService, SpotifyMarketService,
    SpotifyPlayerService, SpotifyPlaylistService, SpotifySearchService,
    SpotifyShowService, SpotifyTrackService, SpotifyUserService {
}