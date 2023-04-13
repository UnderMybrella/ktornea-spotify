package dev.brella.ktornea.spotify

import dev.brella.kornea.BuildConstants
import dev.brella.kornea.errors.common.KorneaResult
import dev.brella.kornea.errors.common.map
import dev.brella.ktornea.results.deleteResult
import dev.brella.ktornea.results.getResult
import dev.brella.ktornea.results.putResult
import dev.brella.ktornea.spotify.data.SpotifyPaginatedData
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrack
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeature
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeatures
import dev.brella.ktornea.spotify.data.tracks.SpotifyTracks
import dev.brella.ktornea.spotify.services.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class SpotifyApi(val client: HttpClient, val apiUrl: String = "https://api.spotify.com/v1") : SpotifyService {
    private inline fun HttpRequestBuilder.setup() {
        header(
            HttpHeaders.UserAgent,
            "ktornea-spotify/${BuildConstants.GIT_BRANCH} (https://github.com/UnderMybrella/ktornea-spotify, ${BuildConstants.GRADLE_VERSION})"
        )
    }

    private suspend inline fun <reified T> KorneaResult<HttpResponse>.mapBody(): KorneaResult<T> =
        map { response -> response.body<T>() }


    private suspend inline fun <reified T, R> KorneaResult<HttpResponse>.mapBody(transform: (T) -> R): KorneaResult<R> =
        map { response -> transform(response.body<T>()) }

    // Tracks
    override suspend fun getTrack(id: String, market: String?): KorneaResult<SpotifyTrack> =
        client.getResult("$apiUrl/tracks/$id") {
            setup()

            parameter("market", market)
        }.mapBody()

    override suspend fun getSeveralTracksJoined(idString: String, market: String?): KorneaResult<List<SpotifyTrack>> =
        client.getResult("$apiUrl/tracks") {
            setup()

            parameter("ids", idString)
            parameter("market", market)
        }.mapBody(SpotifyTracks::tracks)

    override suspend fun getUserSavedTracks(
        offset: Int?,
        limit: Int?,
        market: String?,
    ): KorneaResult<SpotifyPaginatedData<SpotifyTrack>> =
        client.getResult("$apiUrl/me/tracks") {
            setup()

            parameter("market", market)
            parameter("limit", limit)
            parameter("offset", offset)
        }.mapBody()

    override suspend fun saveTracksForCurrentUserJoined(idString: String): KorneaResult<HttpResponse> =
        client.putResult("$apiUrl/me/tracks") {
            setup()

            parameter("ids", idString)
        }

    override suspend fun removeUserSavedTracksJoined(idString: String): KorneaResult<HttpResponse> =
        client.deleteResult("$apiUrl/me/tracks") {
            setup()

            parameter("ids", idString)
        }

    override suspend fun checkUserSavedTracksJoined(idString: String): KorneaResult<List<Boolean>> =
        client.getResult("$apiUrl/me/tracks/contains") {
            setup()

            parameter("ids", idString)
        }.mapBody()

    override suspend fun getSeveralTracksAudioFeaturesJoined(idString: String): KorneaResult<List<SpotifyTrackAudioFeature>> =
        client.getResult("$apiUrl/audio-features") {
            setup()

            parameter("ids", idString)
        }.mapBody(SpotifyTrackAudioFeatures::audioFeatures)

    override suspend fun getTrackAudioFeatures(id: String): KorneaResult<SpotifyTrackAudioFeature> =
        client.getResult("$apiUrl/audio-features/$id") {
            setup()
        }.mapBody()
}