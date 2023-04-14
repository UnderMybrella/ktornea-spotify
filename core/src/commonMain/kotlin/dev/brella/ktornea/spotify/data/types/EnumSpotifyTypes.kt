package dev.brella.ktornea.spotify.data.types

import dev.brella.ktornea.spotify.data.enums.EnumSpotifyType
import kotlinx.serialization.Serializable

public interface SpotifyTrackType
public interface SpotifyAudioFeatureType
public interface SpotifySeedType


public typealias EnumSpotifyTrackType = @Serializable(EnumSpotifyType.Serialiser::class) SpotifyTrackType
public typealias EnumSpotifyAudioFeatureType = @Serializable(EnumSpotifyType.Serialiser::class) SpotifyAudioFeatureType
public typealias EnumSpotifySeedType = @Serializable(EnumSpotifyType.Serialiser::class) SpotifySeedType