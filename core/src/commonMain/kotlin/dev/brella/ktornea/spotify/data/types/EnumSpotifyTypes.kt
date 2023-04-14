package dev.brella.ktornea.spotify.data.types

import dev.brella.ktornea.spotify.data.enums.EnumSpotifyType
import kotlinx.serialization.Serializable

public interface SpotifyTypeOfTrack
public interface SpotifyTypeOfAlbum
public interface SpotifyAlbumType : SpotifyAlbumGroup
public interface SpotifyAlbumGroup
public interface SpotifyArtistType
public interface SpotifyAudioFeatureType
public interface SpotifySeedType


public typealias EnumSpotifyTypeOfTrack = @Serializable(EnumSpotifyType.Serialiser::class) SpotifyTypeOfTrack
public typealias EnumSpotifyTypeOfAlbum = @Serializable(EnumSpotifyType.Serialiser::class) SpotifyTypeOfAlbum
public typealias EnumSpotifyAlbumType = @Serializable(EnumSpotifyType.Serialiser::class) SpotifyAlbumType
public typealias EnumSpotifyAlbumGroup = @Serializable(EnumSpotifyType.Serialiser::class) SpotifyAlbumGroup
public typealias EnumSpotifyArtistType = @Serializable(EnumSpotifyType.Serialiser::class) SpotifyArtistType
public typealias EnumSpotifyAudioFeatureType = @Serializable(EnumSpotifyType.Serialiser::class) SpotifyAudioFeatureType
public typealias EnumSpotifySeedType = @Serializable(EnumSpotifyType.Serialiser::class) SpotifySeedType