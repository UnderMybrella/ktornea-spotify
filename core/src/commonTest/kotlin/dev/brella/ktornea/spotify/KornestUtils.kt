package dev.brella.ktornea.spotify

import dev.brella.kornea.base.common.Optional
import dev.brella.kornea.base.common.doOnPresent
import dev.brella.kornea.errors.common.KorneaResult
import dev.brella.kornea.errors.common.doOnFailure
import io.kotest.assertions.*
import io.kotest.assertions.print.print
import io.kotest.matchers.*

public infix fun <T> KorneaResult<T>.testSuccess(message: Any): KorneaResult<T> {
    assertionCounter.inc()
    return doOnFailure { failure ->
        errorCollector.collectOrThrow(
            failure(
                message.print().value,
                failure.asException()
            )
        )
    }
}


public inline fun <T> T?.testIfNotNull(clue: Any?, test: (T) -> Unit) {
    if (this != null) withClue(clue) { test(this) }
}

public inline fun <T> T?.testIfNotNull(crossinline clue: () -> Any?, test: (T) -> Unit) {
    if (this != null) withClue(clue) { test(this) }
}

public inline fun <R, T : (R) -> Unit> T?.testIfNotNullApply(clue: Any?, on: R) {
    if (this != null) withClue(clue) { this(on) }
}

public inline fun <R, T : (R) -> Unit> T?.testIfNotNullApply(crossinline clue: () -> Any?, on: R) {
    if (this != null) withClue(clue) { this(on) }
}

@Suppress("UNCHECKED_CAST")
public inline fun <T> T?.testIfNotNullShouldBe(clue: Any?, source: T) {
    if ((this ?: return) is Function1<*, *>) withClue(clue) { (this as Function1<T, Unit>)(source) }
    else withClue(clue) { source shouldBe this }
}

@Suppress("UNCHECKED_CAST")
public inline fun <T> T?.testIfNotNullShouldBe(crossinline clue: () -> Any?, source: T) {
    if ((this ?: return) is Function1<*, *>) withClue(clue) { (this as Function1<T, Unit>)(source) }
    else withClue(clue) { source shouldBe this }
}


public inline fun <T> Optional<T>.testIfPresent(clue: Any?, test: (T) -> Unit) =
    doOnPresent { withClue(clue) { test(it) } }

public inline fun <T> Optional<T>.testIfPresent(crossinline clue: () -> Any?, test: (T) -> Unit) =
    doOnPresent { withClue(clue) { test(it) } }

public inline fun <R, T : (R) -> Unit> Optional<T>.testIfPresentApply(clue: Any?, on: R) =
    doOnPresent { withClue(clue) { it(on) } }

public inline fun <R, T : (R) -> Unit> Optional<T>.testIfPresentApply(crossinline clue: () -> Any?, on: R) =
    doOnPresent { withClue(clue) { it(on) } }

@Suppress("UNCHECKED_CAST")
public inline fun <T> Optional<T>.testIfPresentShouldBe(clue: Any?, source: T) =
    doOnPresent { test ->
        if ((test ?: return@doOnPresent) is Function1<*, *>) withClue(clue) { (test as Function1<T, Unit>)(source) }
        else withClue(clue) { source shouldBe test }
    }

@Suppress("UNCHECKED_CAST")
public inline fun <T> Optional<T>.testIfPresentShouldBe(crossinline clue: () -> Any?, source: T) =
    doOnPresent { test ->
        if ((test ?: return@doOnPresent) is Function1<*, *>) withClue(clue) { (test as Function1<T, Unit>)(source) }
        else withClue(clue) { source shouldBe test }
    }

public fun <T> go(block: T.() -> Unit): T.() -> Unit = block
