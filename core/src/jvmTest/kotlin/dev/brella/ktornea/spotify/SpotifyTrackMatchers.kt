package dev.brella.ktornea.spotify

import dev.brella.kornea.base.common.Optional
import dev.brella.ktornea.spotify.data.albums.SpotifyAlbum
import dev.brella.ktornea.spotify.data.albums.SpotifySimplifiedArtist
import dev.brella.ktornea.spotify.data.enums.EnumDatePrecision
import dev.brella.ktornea.spotify.data.enums.EnumSpotifyType
import dev.brella.ktornea.spotify.data.shouldBe
import dev.brella.ktornea.spotify.data.tracks.SpotifyExternalIDs
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrack
import io.kotest.matchers.collections.atLeastSize
import io.kotest.matchers.ints.between

internal fun SpotifyTrack.shouldBeGangnamStyle() =
    shouldBe(
        testAlbum = go<SpotifyAlbum> {
            shouldBe(
                testAlbumGroup = Optional.of(EnumSpotifyType.Single),
                testAlbumType = EnumSpotifyType.Single,
                testArtists = listShouldTestExactly<SpotifySimplifiedArtist> {
                    shouldBe(
                        testID = "2dd5mrQZvg6SmahdgVKDzh",
                        testName = "PSY",
                        testType = EnumSpotifyType.Artist
                    )
                },
                testAvailableMarkets = atLeastSize<Any?>(5),
                testImages = atLeastSize<Any?>(1),
                testName = "Gangnam Style (강남스타일)",
                testReleaseDatePrecision = EnumDatePrecision.Day,
                testType = EnumSpotifyType.Album,
            )
        },
        testExternalIDs = go<SpotifyExternalIDs> {
            shouldBe(testISRC = Optional.of("USUM71210283"))
        },
        testAvailableMarkets = atLeastSize<Any?>(5),
        testDurationMillis = 219493,
        testExplicit = false,
        testIsLocal = false,
        testName = "Gangnam Style (강남스타일)",
        testPopularity = between(0, 100),
        testType = EnumSpotifyType.Track,
    )

internal fun SpotifyTrack.shouldBeCallMeMaybe() =
    shouldBe(
        testAlbum = go<SpotifyAlbum> {
            shouldBe(
                testAlbumGroup = Optional.of(EnumSpotifyType.Album),
                testAlbumType = EnumSpotifyType.Album,
                testArtists = listShouldTestExactly<SpotifySimplifiedArtist> {
                    shouldBe(
                        testID = "6sFIWsNpZYqfjUpaCgueju",
                        testName = "Carly Rae Jepsen",
                        testType = EnumSpotifyType.Artist
                    )
                },
                testAvailableMarkets = atLeastSize<Any?>(5),
                testImages = atLeastSize<Any?>(1),
                testName = "Kiss",
                testReleaseDatePrecision = EnumDatePrecision.Day,
                testType = EnumSpotifyType.Album,
            )
        },
        testExternalIDs = go<SpotifyExternalIDs> {
            shouldBe(testISRC = Optional.of("CAB391100615"))
        },
        testAvailableMarkets = atLeastSize<Any?>(5),
        testDurationMillis = 193400,
        testExplicit = false,
        testIsLocal = false,
        testName = "Call Me Maybe",
        testPopularity = between(0, 100),
        testType = EnumSpotifyType.Track,
    )

internal fun SpotifyTrack.shouldBeALongFall() =
    shouldBe(
        testAlbum = go<SpotifyAlbum> {
            shouldBe(
                testAlbumGroup = Optional.of(EnumSpotifyType.Album),
                testAlbumType = EnumSpotifyType.Album,
                testArtists = listShouldTestExactly<SpotifySimplifiedArtist> {
                    shouldBe(
                        testID = "6NtwaHZLhTUvERKFbFqu8S",
                        testName = "Masayoshi Soken",
                        testType = EnumSpotifyType.Artist
                    )
                },
                testAvailableMarkets = atLeastSize<Any?>(5),
                testImages = atLeastSize<Any?>(1),
                testName = "SHADOWBRINGERS: FINAL FANTASY XIV Original Soundtrack",
                testReleaseDatePrecision = EnumDatePrecision.Day,
                testType = EnumSpotifyType.Album,
            )
        },
        testExternalIDs = go<SpotifyExternalIDs> {
            shouldBe(testISRC = Optional.of("JPA841900604"))
        },
        testAvailableMarkets = atLeastSize<Any?>(5),
        testDurationMillis = 292522,
        testExplicit = false,
        testIsLocal = false,
        testName = "A Long Fall",
        testPopularity = between(0, 100),
        testType = EnumSpotifyType.Track,
    )