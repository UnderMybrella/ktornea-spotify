package dev.brella.ktornea.spotify.services

import dev.brella.kornea.errors.common.KorneaResult
import dev.brella.ktornea.spotify.chunkEmptyToResults
import dev.brella.ktornea.spotify.chunkFlatToResults
import dev.brella.ktornea.spotify.data.SpotifyPaginatedData
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrack
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackAudioFeature
import io.ktor.client.statement.*

interface SpotifyTrackService {
    suspend fun getTrack(id: String, market: String? = null): KorneaResult<SpotifyTrack>

    suspend fun getSeveralTracks(vararg ids: String): KorneaResult<List<SpotifyTrack>> =
        ids.chunkFlatToResults(50) { chunk -> getSeveralTracksJoined(chunk.joinToString(",")) }

    suspend fun getSeveralTracks(market: String? = null, vararg ids: String): KorneaResult<List<SpotifyTrack>> =
        ids.chunkFlatToResults(50) { chunk -> getSeveralTracksJoined(chunk.joinToString(","), market) }

    suspend fun getSeveralTracks(ids: Iterable<String>, market: String? = null): KorneaResult<List<SpotifyTrack>> =
        ids.chunkFlatToResults(50) { chunk -> getSeveralTracksJoined(chunk.joinToString(","), market) }

    suspend fun getSeveralTracksJoined(idString: String, market: String? = null): KorneaResult<List<SpotifyTrack>>

    suspend fun getUserSavedTracks(
        offset: Int? = null,
        limit: Int? = null,
        market: String? = null,
    ): KorneaResult<SpotifyPaginatedData<SpotifyTrack>>

    suspend fun saveTracksForCurrentUser(vararg ids: String): KorneaResult<Unit> =
        ids.chunkEmptyToResults(50) { chunk -> saveTracksForCurrentUserJoined(chunk.joinToString(",")) }

    suspend fun saveTracksForCurrentUser(ids: Iterable<String>): KorneaResult<Unit> =
        ids.chunkEmptyToResults(50) { chunk -> saveTracksForCurrentUserJoined(chunk.joinToString(",")) }

    suspend fun saveTracksForCurrentUserJoined(idString: String): KorneaResult<HttpResponse>

    suspend fun removeUserSavedTracks(vararg ids: String): KorneaResult<Unit> =
        ids.chunkEmptyToResults(50) { chunk -> removeUserSavedTracksJoined(chunk.joinToString(",")) }

    suspend fun removeUserSavedTracks(ids: Iterable<String>): KorneaResult<Unit> =
        ids.chunkEmptyToResults(50) { chunk -> removeUserSavedTracksJoined(chunk.joinToString(",")) }

    suspend fun removeUserSavedTracksJoined(idString: String): KorneaResult<HttpResponse>

    suspend fun checkUserSavedTracks(vararg ids: String): KorneaResult<List<Boolean>> =
        ids.chunkFlatToResults(50) { chunk -> checkUserSavedTracksJoined(chunk.joinToString(",")) }

    suspend fun checkUserSavedTracks(ids: Iterable<String>): KorneaResult<List<Boolean>> =
        ids.chunkFlatToResults(50) { chunk -> checkUserSavedTracksJoined(chunk.joinToString(",")) }

    suspend fun checkUserSavedTracksJoined(idString: String): KorneaResult<List<Boolean>>

    suspend fun getSeveralTracksAudioFeatures(vararg ids: String): KorneaResult<List<SpotifyTrackAudioFeature>> =
        ids.chunkFlatToResults(50) { chunk -> getSeveralTracksAudioFeaturesJoined(chunk.joinToString(",")) }
    suspend fun getSeveralTracksAudioFeatures(ids: Iterable<String>): KorneaResult<List<SpotifyTrackAudioFeature>> =
        ids.chunkFlatToResults(50) { chunk -> getSeveralTracksAudioFeaturesJoined(chunk.joinToString(",")) }
    suspend fun getSeveralTracksAudioFeaturesJoined(idString: String): KorneaResult<List<SpotifyTrackAudioFeature>>

    suspend fun getTrackAudioFeatures(id: String): KorneaResult<SpotifyTrackAudioFeature>
}