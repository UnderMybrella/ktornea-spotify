package dev.brella.ktornea.spotify.data

import dev.brella.ktornea.spotify.data.albums.SpotifySimplifiedArtist
import dev.brella.ktornea.spotify.testIfNotNullShouldBe
import io.kotest.assertions.assertSoftly

public fun SpotifySimplifiedArtist.shouldBe(
    testExternalUrls: Any? = null,
    testHref: Any? = null,
    testID: Any? = null,
    testName: Any? = null,
    testType: Any? = null,
    testUri: Any? = null
) = assertSoftly {
    testExternalUrls.testIfNotNullShouldBe("External Urls", this.externalUrls)
    testHref.testIfNotNullShouldBe("href", this.href)
    testID.testIfNotNullShouldBe("ID", this.id)
    testName.testIfNotNullShouldBe("Name", this.name)
    testType.testIfNotNullShouldBe("Type", this.type)
    testUri.testIfNotNullShouldBe("Uri", this.uri)
}