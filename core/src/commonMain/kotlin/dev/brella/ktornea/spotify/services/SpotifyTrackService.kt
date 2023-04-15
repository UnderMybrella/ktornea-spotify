package dev.brella.ktornea.spotify.services

import dev.brella.kornea.errors.common.KorneaResult
import dev.brella.ktornea.spotify.chunkEmptyToResults
import dev.brella.ktornea.spotify.chunkFlatToResults
import dev.brella.ktornea.spotify.data.SpotifyPaginatedData
import dev.brella.ktornea.spotify.data.enums.EnumPitchClass
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrack
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeatures
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackRecommendationBuilder
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackRecommendationResponse
import dev.brella.ktornea.spotify.data.tracks.analysis.SpotifyTrackAudioAnalysis
import io.ktor.client.statement.*

public interface SpotifyTrackService {
    public suspend fun getTrack(id: String, market: String? = null): KorneaResult<SpotifyTrack>

    public suspend fun getSeveralTracks(vararg ids: String): KorneaResult<List<SpotifyTrack?>> =
        ids.chunkFlatToResults(50) { chunk -> getSeveralTracksJoined(chunk.joinToString(",")) }

    public suspend fun getSeveralTracks(market: String? = null, vararg ids: String): KorneaResult<List<SpotifyTrack?>> =
        ids.chunkFlatToResults(50) { chunk -> getSeveralTracksJoined(chunk.joinToString(","), market) }

    public suspend fun getSeveralTracks(
        ids: Iterable<String>,
        market: String? = null,
    ): KorneaResult<List<SpotifyTrack?>> =
        ids.chunkFlatToResults(50) { chunk -> getSeveralTracksJoined(chunk.joinToString(","), market) }

    public suspend fun getSeveralTracksJoined(
        idString: String,
        market: String? = null,
    ): KorneaResult<List<SpotifyTrack?>>

    public suspend fun getUserSavedTracks(
        offset: Int? = null,
        limit: Int? = null,
        market: String? = null,
    ): KorneaResult<SpotifyPaginatedData<SpotifyTrack>>

    public suspend fun saveTracksForCurrentUser(vararg ids: String): KorneaResult<Unit> =
        ids.chunkEmptyToResults(50) { chunk -> saveTracksForCurrentUserJoined(chunk.joinToString(",")) }

    public suspend fun saveTracksForCurrentUser(ids: Iterable<String>): KorneaResult<Unit> =
        ids.chunkEmptyToResults(50) { chunk -> saveTracksForCurrentUserJoined(chunk.joinToString(",")) }

    public suspend fun saveTracksForCurrentUserJoined(idString: String): KorneaResult<HttpResponse>

    public suspend fun removeUserSavedTracks(vararg ids: String): KorneaResult<Unit> =
        ids.chunkEmptyToResults(50) { chunk -> removeUserSavedTracksJoined(chunk.joinToString(",")) }

    public suspend fun removeUserSavedTracks(ids: Iterable<String>): KorneaResult<Unit> =
        ids.chunkEmptyToResults(50) { chunk -> removeUserSavedTracksJoined(chunk.joinToString(",")) }

    public suspend fun removeUserSavedTracksJoined(idString: String): KorneaResult<HttpResponse>

    public suspend fun checkUserSavedTracks(vararg ids: String): KorneaResult<List<Boolean>> =
        ids.chunkFlatToResults(50) { chunk -> checkUserSavedTracksJoined(chunk.joinToString(",")) }

    public suspend fun checkUserSavedTracks(ids: Iterable<String>): KorneaResult<List<Boolean>> =
        ids.chunkFlatToResults(50) { chunk -> checkUserSavedTracksJoined(chunk.joinToString(",")) }

    public suspend fun checkUserSavedTracksJoined(idString: String): KorneaResult<List<Boolean>>

    public suspend fun getSeveralTracksAudioFeatures(vararg ids: String): KorneaResult<List<SpotifyTrackAudioFeatures>> =
        ids.chunkFlatToResults(50) { chunk -> getSeveralTracksAudioFeaturesJoined(chunk.joinToString(",")) }

    public suspend fun getSeveralTracksAudioFeatures(ids: Iterable<String>): KorneaResult<List<SpotifyTrackAudioFeatures>> =
        ids.chunkFlatToResults(50) { chunk -> getSeveralTracksAudioFeaturesJoined(chunk.joinToString(",")) }

    public suspend fun getSeveralTracksAudioFeaturesJoined(idString: String): KorneaResult<List<SpotifyTrackAudioFeatures>>

    public suspend fun getTrackAudioFeatures(id: String): KorneaResult<SpotifyTrackAudioFeatures>

    public suspend fun getTrackAudioAnalysis(id: String): KorneaResult<SpotifyTrackAudioAnalysis>

    /** Recommendations are generated based on the available information for a given seed entity and matched against similar artists and tracks.
     *
     *  If there is sufficient information about the provided seeds, a list of tracks will be returned together with pool size details.
     *
     *  For artists and tracks that are very new or obscure there might not be enough data to generate a list of tracks. */
    public suspend fun getRecommendations(
        limit: Int? = null,
        market: String? = null,
        seedArtists: List<String>? = null,
        seedGenres: List<String>? = null,
        seedTracks: List<String>? = null,
        minAcousticness: Double? = null,
        maxAcousticness: Double? = null,
        targetAcousticness: Double? = null,
        minDanceability: Double? = null,
        maxDanceability: Double? = null,
        targetDanceability: Double? = null,
        minDurationMillis: Int? = null,
        maxDurationMillis: Int? = null,
        targetDurationMillis: Int? = null,
        minEnergy: Double? = null,
        maxEnergy: Double? = null,
        targetEnergy: Double? = null,
        minInstrumentalness: Double? = null,
        maxInstrumentalness: Double? = null,
        targetInstrumentalness: Double? = null,
        minKey: EnumPitchClass? = null,
        maxKey: EnumPitchClass? = null,
        targetKey: EnumPitchClass? = null,
        minLiveness: Double? = null,
        maxLiveness: Double? = null,
        targetLiveness: Double? = null,
        minLoudness: Double? = null,
        maxLoudness: Double? = null,
        targetLoudness: Double? = null,
        minMode: Int? = null,
        maxMode: Int? = null,
        targetMode: Int? = null,
        minPopularity: Int? = null,
        maxPopularity: Int? = null,
        targetPopularity: Int? = null,
        minSpeechiness: Double? = null,
        maxSpeechiness: Double? = null,
        targetSpeechiness: Double? = null,
        minTempo: Double? = null,
        maxTempo: Double? = null,
        targetTempo: Double? = null,
        minTimeSignature: Int? = null,
        maxTimeSignature: Int? = null,
        targetTimeSignature: Int? = null,
        minValence: Double? = null,
        maxValence: Double? = null,
        targetValence: Double? = null,
    ): KorneaResult<SpotifyTrackRecommendationResponse>
}

/** Recommendations are generated based on the available information for a given seed entity and matched against similar artists and tracks.
 *
 *  If there is sufficient information about the provided seeds, a list of tracks will be returned together with pool size details.
 *
 *  For artists and tracks that are very new or obscure there might not be enough data to generate a list of tracks. */
public suspend fun SpotifyTrackService.getRecommendations(builder: SpotifyTrackRecommendationBuilder): KorneaResult<SpotifyTrackRecommendationResponse> =
    getRecommendations(
        builder.limit,
        builder.market,
        builder.seedArtists.takeIf(List<String>::isNotEmpty),
        builder.seedGenres.takeIf(List<String>::isNotEmpty),
        builder.seedTracks.takeIf(List<String>::isNotEmpty),

        builder.acousticness.min,
        builder.acousticness.max,
        builder.acousticness.target,

        builder.danceability.min,
        builder.danceability.max,
        builder.danceability.target,

        builder.durationMillis.min,
        builder.durationMillis.max,
        builder.durationMillis.target,

        builder.energy.min,
        builder.energy.max,
        builder.energy.target,

        builder.instrumentalness.min,
        builder.instrumentalness.max,
        builder.instrumentalness.target,

        builder.key.min,
        builder.key.max,
        builder.key.target,

        builder.liveness.min,
        builder.liveness.max,
        builder.liveness.target,

        builder.loudness.min,
        builder.loudness.max,
        builder.loudness.target,

        builder.mode.min,
        builder.mode.max,
        builder.mode.target,

        builder.popularity.min,
        builder.popularity.max,
        builder.popularity.target,

        builder.speechiness.min,
        builder.speechiness.max,
        builder.speechiness.target,

        builder.tempo.min,
        builder.tempo.max,
        builder.tempo.target,

        builder.timeSignature.min,
        builder.timeSignature.max,
        builder.timeSignature.target,

        builder.valence.min,
        builder.valence.max,
        builder.valence.target
    )

/** Recommendations are generated based on the available information for a given seed entity and matched against similar artists and tracks.
 *
 *  If there is sufficient information about the provided seeds, a list of tracks will be returned together with pool size details.
 *
 *  For artists and tracks that are very new or obscure there might not be enough data to generate a list of tracks. */
public suspend fun SpotifyTrackService.getRecommendations(build: SpotifyTrackRecommendationBuilder.() -> Unit): KorneaResult<SpotifyTrackRecommendationResponse> =
    getRecommendations(SpotifyTrackRecommendationBuilder().apply(build))