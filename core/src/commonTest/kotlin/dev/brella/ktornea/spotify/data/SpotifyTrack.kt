package dev.brella.ktornea.spotify.data

import dev.brella.kornea.base.common.Optional
import dev.brella.ktornea.spotify.data.tracks.*
import dev.brella.ktornea.spotify.testIfNotNullShouldBe
import dev.brella.ktornea.spotify.testIfPresentShouldBe
import io.kotest.assertions.assertSoftly

public fun SpotifyTrack.shouldBe(
//    testAlbum: (SpotifyAlbum.() -> Unit)? = null,
    testAlbum: Any? = null,
    testArtists: Any? = null,
    testAvailableMarkets: Any? = null,
    testDiscNumber: Any? = null,
    testDurationMillis: Any? = null,
    testExplicit: Any? = null,
    testExternalIDs: Any? = null,
    testExternalUrls: Any? = null,
    testHref: Any? = null,
    testID: Any? = null,
    testIsPlayable: Optional<Any?> = Optional.empty(),
    testLinkedFrom: Optional<Any?> = Optional.empty(),
    testRestrictions: Optional<Any?> = Optional.empty(),
    testName: Any? = null,
    testPopularity: Any? = null,
    testPreviewUrl: Optional<Any?> = Optional.empty(),
    testTrackNumber: Any? = null,
    testType: Any? = null,
    testUri: Any? = null,
    testIsLocal: Any? = null
) = assertSoftly {
//    testAlbum.testIfNotNullApply("Album", this.album)
    testAlbum.testIfNotNullShouldBe("Album", this.album)
    testArtists.testIfNotNullShouldBe("Artists", this.artists)
    testAvailableMarkets.testIfNotNullShouldBe("Available Markets", this.availableMarkets)

    testDiscNumber.testIfNotNullShouldBe("Disc Number", this.discNumber)
    testDurationMillis.testIfNotNullShouldBe("Duration Millis", this.durationMillis)
    testExplicit.testIfNotNullShouldBe("Is Explicit", this.explicit)
    testExternalIDs.testIfNotNullShouldBe("External IDs", this.externalIDs)
    testExternalUrls.testIfNotNullShouldBe("External Urls", this.externalUrls)
    testHref.testIfNotNullShouldBe("href", this.href)
    testID.testIfNotNullShouldBe("ID", this.id)
    testIsPlayable.testIfPresentShouldBe("Is Playable", this.isPlayable)
    testLinkedFrom.testIfPresentShouldBe("Linked From", this.linkedFrom)
    testRestrictions.testIfPresentShouldBe("Restrictions", this.restrictions)
    testName.testIfNotNullShouldBe("Name", this.name)
    testPopularity.testIfNotNullShouldBe("Popularity", this.popularity)
    testPreviewUrl.testIfPresentShouldBe("Preview Url", this.previewUrl)
    testTrackNumber.testIfNotNullShouldBe("Track Number", this.trackNumber)
    testType.testIfNotNullShouldBe("Type", this.type)
    testUri.testIfNotNullShouldBe("URI", this.uri)
    testIsLocal.testIfNotNullShouldBe("Is Local", this.isLocal)
}