package dev.brella.ktornea.spotify.data.tracks

import dev.brella.ktornea.spotify.data.enums.EnumPitchClass

public class SpotifyTrackRecommendationBuilder {
    public data class Tunable<T>(var min: T? = null, var max: T? = null, var target: T? = null) {
        public fun clear() {
            this.min = null
            this.max = null
            this.target = null
        }

        public inline operator fun invoke(block: Tunable<T>.() -> Unit): Tunable<T> = apply(block)
        public operator fun invoke(min: T? = null, max: T? = null, target: T? = null): Tunable<T> = apply {
            min?.let { this.min = it }
            max?.let { this.max = it }
            target?.let { this.target = it }
        }
    }

    public var limit: Int? = null
    public var market: String? = null

    public val seedArtists: MutableList<String> = ArrayList()
    public val seedGenres: MutableList<String> = ArrayList()
    public val seedTracks: MutableList<String> = ArrayList()

    public val acousticness: Tunable<Double> = Tunable()
    public val danceability: Tunable<Double> = Tunable()
    public val durationMillis: Tunable<Int> = Tunable()
    public val energy: Tunable<Double> = Tunable()
    public val instrumentalness: Tunable<Double> = Tunable()
    public val key: Tunable<EnumPitchClass> = Tunable()
    public val liveness: Tunable<Double> = Tunable()
    public val loudness: Tunable<Double> = Tunable()
    public val mode: Tunable<Int> = Tunable()
    public val popularity: Tunable<Int> = Tunable()
    public val speechiness: Tunable<Double> = Tunable()
    public val tempo: Tunable<Double> = Tunable()
    public val timeSignature: Tunable<Int> = Tunable()
    public val valence: Tunable<Double> = Tunable()

    public fun seedArtist(id: String): SpotifyTrackRecommendationBuilder =
        apply { seedArtists.add(id) }

    public fun seedArtists(vararg ids: String): SpotifyTrackRecommendationBuilder =
        apply { seedArtists.addAll(ids) }

    public fun seedArtists(ids: Iterable<String>): SpotifyTrackRecommendationBuilder =
        apply { seedArtists.addAll(ids) }

    public fun seedGenre(id: String): SpotifyTrackRecommendationBuilder =
        apply { seedGenres.add(id) }

    public fun seedGenres(vararg ids: String): SpotifyTrackRecommendationBuilder =
        apply { seedGenres.addAll(ids) }

    public fun seedGenres(ids: Iterable<String>): SpotifyTrackRecommendationBuilder =
        apply { seedGenres.addAll(ids) }

    public fun seedTrack(id: String): SpotifyTrackRecommendationBuilder =
        apply { seedTracks.add(id) }

    public fun seedTracks(vararg ids: String): SpotifyTrackRecommendationBuilder =
        apply { seedTracks.addAll(ids) }

    public fun seedTracks(ids: Iterable<String>): SpotifyTrackRecommendationBuilder =
        apply { seedTracks.addAll(ids) }

    public fun clear() {
        limit = null
        market = null

        seedArtists.clear()
        seedGenres.clear()
        seedTracks.clear()

        acousticness.clear()
        danceability.clear()
        durationMillis.clear()
        energy.clear()
        instrumentalness.clear()
        key.clear()
        liveness.clear()
        loudness.clear()
        mode.clear()
        popularity.clear()
        speechiness.clear()
        tempo.clear()
        timeSignature.clear()
        valence.clear()
    }
}

public operator fun <T> SpotifyTrackRecommendationBuilder.Tunable<T>.invoke(range: ClosedRange<T>): SpotifyTrackRecommendationBuilder.Tunable<T> where T : Comparable<T> =
    apply {
        this.min = range.start
        this.max = range.endInclusive
    }

public infix fun <T> SpotifyTrackRecommendationBuilder.Tunable<T>.between(range: ClosedRange<T>): SpotifyTrackRecommendationBuilder.Tunable<T> where T : Comparable<T> =
    apply {
        this.min = range.start
        this.max = range.endInclusive
    }

public inline fun buildSpotifyTrackRecommendationsRequest(build: SpotifyTrackRecommendationBuilder.() -> Unit): SpotifyTrackRecommendationBuilder =
    SpotifyTrackRecommendationBuilder().apply(build)