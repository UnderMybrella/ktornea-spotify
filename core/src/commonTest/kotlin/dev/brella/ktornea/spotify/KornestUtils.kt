package dev.brella.ktornea.spotify

import dev.brella.kornea.base.common.Optional
import dev.brella.kornea.base.common.doOnPresent
import dev.brella.kornea.errors.common.KorneaResult
import dev.brella.kornea.errors.common.doOnFailure
import dev.brella.kornea.errors.common.doOnSuccess
import io.kotest.assertions.*
import io.kotest.assertions.print.print
import io.kotest.matchers.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

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

public fun <T> KorneaResult<T>.testSuccess(message: Any, onSuccess: (T) -> Unit): KorneaResult<T> {
    assertionCounter.inc()
    return doOnFailure { failure ->
        errorCollector.collectOrThrow(
            failure(
                message.print().value,
                failure.asException()
            )
        )
    }.doOnSuccess(onSuccess)
}

public fun <T> KorneaResult<T>.testFailure(message: Any?): KorneaResult<T> {
    assertionCounter.inc()
    return doOnSuccess { errorCollector.collectOrThrow(failure(message.print().value)) }
}

public fun <T> KorneaResult<T>.testFailure(message: (T) -> Any?): KorneaResult<T> {
    assertionCounter.inc()
    return doOnSuccess { errorCollector.collectOrThrow(failure(message(it).print().value)) }
}

public fun <T> KorneaResult<T>.testFailure(message: Any?, onFailure: (KorneaResult.Failure) -> Unit): KorneaResult<T> {
    assertionCounter.inc()
    return doOnSuccess { errorCollector.collectOrThrow(failure(message.print().value)) }
        .doOnFailure(onFailure)
}

public fun <T> KorneaResult<T>.testFailure(message: (T) -> Any?, onFailure: (KorneaResult.Failure) -> Unit): KorneaResult<T> {
    assertionCounter.inc()
    return doOnSuccess { errorCollector.collectOrThrow(failure(message(it).print().value)) }
        .doOnFailure(onFailure)
}

public fun <T> KorneaResult<T>.testSuccessApply(message: Any, onSuccess: T.() -> Unit): KorneaResult<T> {
    assertionCounter.inc()
    return doOnFailure { failure ->
        errorCollector.collectOrThrow(
            failure(
                message.print().value,
                failure.asException()
            )
        )
    }.doOnSuccess(onSuccess)
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

public fun MatcherResult.takeIfPassed(): MatcherResult? =
    if (passed()) this else null

public fun MatcherResult.takeIfFailed(): MatcherResult? =
    if (passed()) null else this

@OptIn(ExperimentalContracts::class)
public inline fun <T> MatcherResult.ifPassed(block: (MatcherResult) -> T): T? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return if (passed()) block(this) else null
}

@OptIn(ExperimentalContracts::class)
public inline fun <T> MatcherResult.ifFailed(block: (MatcherResult) -> T): T? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return if (passed()) null else block(this)
}

@OptIn(ExperimentalContracts::class)
public inline fun MatcherResult.passedOrBreak(block: (MatcherResult) -> Nothing): MatcherResult {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }

    return if (passed()) this else block(this)
}

@OptIn(ExperimentalContracts::class)
public inline fun passOrBreak(result: MatcherResult, block: (MatcherResult) -> Nothing): MatcherResult =
    result.passedOrBreak(block)