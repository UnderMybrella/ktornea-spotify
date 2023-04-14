package dev.brella.ktornea.spotify.data

import dev.brella.kornea.base.common.Optional
import dev.brella.kornea.base.common.empty
import dev.brella.ktornea.spotify.data.tracks.SpotifyExternalIDs
import dev.brella.ktornea.spotify.testIfPresentShouldBe
import io.kotest.assertions.assertSoftly

public fun SpotifyExternalIDs.shouldBe(
    testISRC: Optional<Any?> = Optional.empty(),
    testEAN: Optional<Any?> = Optional.empty(),
    testUPC: Optional<Any?> = Optional.empty()
) = assertSoftly {
    testISRC.testIfPresentShouldBe("isrc", this.isrc)
    testEAN.testIfPresentShouldBe("ean", this.ean)
    testUPC.testIfPresentShouldBe("upc", this.upc)
}