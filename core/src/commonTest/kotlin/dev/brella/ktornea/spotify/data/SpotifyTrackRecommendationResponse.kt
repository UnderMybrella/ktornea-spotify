package dev.brella.ktornea.spotify.data

import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackRecommendationResponse
import dev.brella.ktornea.spotify.data.tracks.SpotifyTrackRecommendationSeedObject
import dev.brella.ktornea.spotify.testIfNotNull
import dev.brella.ktornea.spotify.testIfNotNullShouldBe
import io.kotest.assertions.assertSoftly

public fun SpotifyTrackRecommendationResponse.shouldBe(
    testSeeds: Any? = null,
    testTracks: Any? = null,
) = assertSoftly {
    testSeeds.testIfNotNullShouldBe("Seeds", this.seeds)
    testTracks.testIfNotNullShouldBe("Tracks", this.tracks)
}

public fun SpotifyTrackRecommendationSeedObject.shouldBe(
    testAfterFilteringSize: Any? = null,
    testAfterRelinkingSize: Any? = null,
    testHref: Any? = null,
    testID: Any? = null,
    testInitialPoolSize: Any? = null,
    testType: Any? = null
) = assertSoftly {
    testAfterFilteringSize.testIfNotNullShouldBe("After Filtering Size", this.afterFilteringSize)
    testAfterRelinkingSize.testIfNotNullShouldBe("After Relinking Size", this.afterRelinkingSize)
    testHref.testIfNotNullShouldBe("href", this.href)
    testID.testIfNotNullShouldBe("ID", this.id)
    testInitialPoolSize.testIfNotNullShouldBe("Initial Pool Size", this.initialPoolSize)
    testType.testIfNotNullShouldBe("Type", this.type)
}