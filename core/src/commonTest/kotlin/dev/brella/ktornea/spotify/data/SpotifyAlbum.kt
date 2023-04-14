package dev.brella.ktornea.spotify.data

import dev.brella.kornea.base.common.Optional
import dev.brella.kornea.base.common.empty
import dev.brella.ktornea.spotify.data.albums.SpotifyAlbum
import dev.brella.ktornea.spotify.data.albums.SpotifySimplifiedArtist
import dev.brella.ktornea.spotify.data.tracks.SpotifyExternalUrls
import dev.brella.ktornea.spotify.data.tracks.SpotifyImage
import dev.brella.ktornea.spotify.data.tracks.SpotifyRestrictions
import dev.brella.ktornea.spotify.testIfNotNull
import dev.brella.ktornea.spotify.testIfNotNullShouldBe
import dev.brella.ktornea.spotify.testIfPresentShouldBe
import io.kotest.assertions.assertSoftly

public fun SpotifyAlbum.shouldBe(
    testAlbumGroup: Optional<Any?> = Optional.empty(),
    testAlbumType: Any? = null,
    testArtists: Any? = null,
    testAvailableMarkets: Any? = null,
    testCopyrights: Optional<Any?> = Optional.empty(),
    testExternalIDs: Optional<Any?> = Optional.empty(),
    testExternalUrls: Any? = null,
    testGenres: Optional<Any?> = Optional.empty(),
    testHref: Any? = null,
    testID: Any? = null,
    testImages: Any? = null,
    testLabel: Optional<Any?> = Optional.empty(),
    testName: Any? = null,
    testPopularity: Optional<Any?> = Optional.empty(),
    testReleaseDate: Any? = null,
    testReleaseDatePrecision: Any? = null,
    testRestrictions: Optional<Any?> = Optional.empty(),
    testTotalTracks: Any? = null,
    testType: Any? = null,
    testUri: Any? = null,
) = assertSoftly {
    testAlbumGroup.testIfPresentShouldBe("Album Group", this.albumGroup)
    testAlbumType.testIfNotNullShouldBe("Album Type", this.albumType)
    testArtists.testIfNotNullShouldBe("Artists", this.artists)
    testAvailableMarkets.testIfNotNullShouldBe("Available Markets", this.availableMarkets)
    testCopyrights.testIfPresentShouldBe("Copyrights", this.copyrights)
    testExternalIDs.testIfPresentShouldBe("External IDs", this.externalIDs)
    testExternalUrls.testIfNotNullShouldBe("External Urls", this.externalUrls)
    testGenres.testIfPresentShouldBe("Genres", this.genres)
    testHref.testIfNotNullShouldBe("href", this.href)
    testID.testIfNotNullShouldBe("ID", this.id)
    testImages.testIfNotNullShouldBe("Images", this.images)
    testLabel.testIfPresentShouldBe("Label", this.label)
    testName.testIfNotNullShouldBe("Name", this.name)
    testPopularity.testIfPresentShouldBe("Popularity", this.popularity)
    testReleaseDate.testIfNotNullShouldBe("Release Date", this.releaseDate)
    testReleaseDatePrecision.testIfNotNullShouldBe("Release Date Precision", this.releaseDatePrecision)
    testRestrictions.testIfPresentShouldBe("Restrictions", this.restrictions)
    testTotalTracks.testIfNotNullShouldBe("Total Tracks", this.totalTracks)
    testType.testIfNotNullShouldBe("Type", this.type)
    testUri.testIfNotNullShouldBe("URI", this.uri)
}