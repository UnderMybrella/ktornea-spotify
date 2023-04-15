package dev.brella.ktornea.spotify

import dev.brella.kornea.BuildConstants
import dev.brella.kornea.errors.common.KorneaResult
import dev.brella.kornea.errors.common.map
import dev.brella.ktornea.results.deleteResult
import dev.brella.ktornea.results.getResult
import dev.brella.ktornea.results.putResult
import dev.brella.ktornea.results.withPayloadForHttpResult
import dev.brella.ktornea.spotify.data.SpotifyErrorResponse
import dev.brella.ktornea.spotify.data.SpotifyPaginatedData
import dev.brella.ktornea.spotify.data.enums.EnumPitchClass
import dev.brella.ktornea.spotify.data.tracks.*
import dev.brella.ktornea.spotify.data.tracks.analysis.SpotifyTrackAudioAnalysis
import dev.brella.ktornea.spotify.data.tracks.collections.SpotifyTrackAudioFeatureCollection
import dev.brella.ktornea.spotify.data.tracks.collections.SpotifyTrackCollection
import dev.brella.ktornea.spotify.services.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

public class SpotifyApi(
    public val client: HttpClient,
    public val apiUrl: String = "https://api.spotify.com/v1",
) : SpotifyService {
    private fun HttpRequestBuilder.setup() {
        header(
            HttpHeaders.UserAgent,
            "ktornea-spotify/${BuildConstants.GIT_BRANCH} (https://github.com/UnderMybrella/ktornea-spotify, ${BuildConstants.GRADLE_VERSION})"
        )
    }

    private suspend inline fun <reified T> KorneaResult<HttpResponse>.mapBodyOrError(): KorneaResult<T> =
        withPayloadForHttpResult { result -> result.response.body<SpotifyErrorResponse>() }
            .map { response -> response.body<T>() }


    private suspend inline fun <reified T, R> KorneaResult<HttpResponse>.mapBodyOrError(transform: (T) -> R): KorneaResult<R> =
        withPayloadForHttpResult { result -> result.response.body<SpotifyErrorResponse>() }
            .map { response -> transform(response.body<T>()) }

    // Tracks
    override suspend fun getTrack(id: String, market: String?): KorneaResult<SpotifyTrack> =
        client.getResult("$apiUrl/tracks/$id") {
            setup()

            parameter("market", market)
        }.mapBodyOrError()

    override suspend fun getSeveralTracksJoined(idString: String, market: String?): KorneaResult<List<SpotifyTrack?>> =
        client.getResult("$apiUrl/tracks") {
            setup()

            parameter("ids", idString)
            parameter("market", market)
        }.mapBodyOrError(SpotifyTrackCollection::tracks)

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
        }.mapBodyOrError()

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
        }.mapBodyOrError()

    override suspend fun getSeveralTracksAudioFeaturesJoined(idString: String): KorneaResult<List<SpotifyTrackAudioFeatures>> =
        client.getResult("$apiUrl/audio-features") {
            setup()

            parameter("ids", idString)
        }.mapBodyOrError(SpotifyTrackAudioFeatureCollection::audioFeatures)

    override suspend fun getTrackAudioFeatures(id: String): KorneaResult<SpotifyTrackAudioFeatures> =
        client.getResult("$apiUrl/audio-features/$id") {
            setup()
        }.mapBodyOrError()

    override suspend fun getTrackAudioAnalysis(id: String): KorneaResult<SpotifyTrackAudioAnalysis> =
        client.getResult("$apiUrl/audio-analysis/$id") {
            setup()
        }.mapBodyOrError()

    override suspend fun getRecommendations(
        limit: Int?,
        market: String?,
        seedArtists: List<String>?,
        seedGenres: List<String>?,
        seedTracks: List<String>?,
        minAcousticness: Double?,
        maxAcousticness: Double?,
        targetAcousticness: Double?,
        minDanceability: Double?,
        maxDanceability: Double?,
        targetDanceability: Double?,
        minDurationMillis: Int?,
        maxDurationMillis: Int?,
        targetDurationMillis: Int?,
        minEnergy: Double?,
        maxEnergy: Double?,
        targetEnergy: Double?,
        minInstrumentalness: Double?,
        maxInstrumentalness: Double?,
        targetInstrumentalness: Double?,
        minKey: EnumPitchClass?,
        maxKey: EnumPitchClass?,
        targetKey: EnumPitchClass?,
        minLiveness: Double?,
        maxLiveness: Double?,
        targetLiveness: Double?,
        minLoudness: Double?,
        maxLoudness: Double?,
        targetLoudness: Double?,
        minMode: Int?,
        maxMode: Int?,
        targetMode: Int?,
        minPopularity: Int?,
        maxPopularity: Int?,
        targetPopularity: Int?,
        minSpeechiness: Double?,
        maxSpeechiness: Double?,
        targetSpeechiness: Double?,
        minTempo: Double?,
        maxTempo: Double?,
        targetTempo: Double?,
        minTimeSignature: Int?,
        maxTimeSignature: Int?,
        targetTimeSignature: Int?,
        minValence: Double?,
        maxValence: Double?,
        targetValence: Double?,
    ): KorneaResult<SpotifyTrackRecommendationResponse> =
        client.getResult("$apiUrl/recommendations") {
            setup()

            parameter("limit", limit)
            parameter("market", market)
            parameter("seed_artists", seedArtists?.joinToString(","))
            parameter("seed_genres", seedGenres?.joinToString(","))
            parameter("seed_tracks", seedTracks?.joinToString(","))

            parameter("min_acousticness", minAcousticness)
            parameter("max_acousticness", maxAcousticness)
            parameter("target_acousticness", targetAcousticness)
            parameter("min_danceability", minDanceability)
            parameter("max_danceability", maxDanceability)
            parameter("target_danceability", targetDanceability)
            parameter("min_duration_ms", minDurationMillis)
            parameter("max_duration_ms", maxDurationMillis)
            parameter("target_duration_ms", targetDurationMillis)
            parameter("min_energy", minEnergy)
            parameter("max_energy", maxEnergy)
            parameter("target_energy", targetEnergy)
            parameter("min_instrumentalness", minInstrumentalness)
            parameter("max_instrumentalness", maxInstrumentalness)
            parameter("target_instrumentalness", targetInstrumentalness)
            parameter("min_key", minKey?.type)
            parameter("max_key", maxKey?.type)
            parameter("target_key", targetKey?.type)
            parameter("min_liveness", minLiveness)
            parameter("max_liveness", maxLiveness)
            parameter("target_liveness", targetLiveness)
            parameter("min_loudness", minLoudness)
            parameter("max_loudness", maxLoudness)
            parameter("target_loudness", targetLoudness)
            parameter("min_mode", minMode)
            parameter("max_mode", maxMode)
            parameter("target_mode", targetMode)
            parameter("min_popularity", minPopularity)
            parameter("max_popularity", maxPopularity)
            parameter("target_popularity", targetPopularity)
            parameter("min_speechiness", minSpeechiness)
            parameter("max_speechiness", maxSpeechiness)
            parameter("target_speechiness", targetSpeechiness)
            parameter("min_tempo", minTempo)
            parameter("max_tempo", maxTempo)
            parameter("target_tempo", targetTempo)
            parameter("min_time_signature", minTimeSignature)
            parameter("max_time_signature", maxTimeSignature)
            parameter("target_time_signature", targetTimeSignature)
            parameter("min_valence", minValence)
            parameter("max_valence", maxValence)
            parameter("target_valence", targetValence)
        }.mapBodyOrError()
}