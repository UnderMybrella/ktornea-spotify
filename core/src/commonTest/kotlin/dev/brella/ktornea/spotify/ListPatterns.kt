package dev.brella.ktornea.spotify

import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe

public inline fun <T> listShouldTestExactly(crossinline testSingle: T.() -> Unit): List<T>.() -> Unit = {
    withClue("List must contain exactly one element") { size shouldBe 1 }
    get(0).testSingle()
}