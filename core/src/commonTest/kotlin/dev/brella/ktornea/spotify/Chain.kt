package dev.brella.ktornea.spotify

import io.kotest.matchers.MatcherResult

public inline fun chainMatcherResults(
    a: () -> MatcherResult,
    b: () -> MatcherResult,
): MatcherResult =
    a().takeIfFailed()
        ?: b()

public inline fun chainMatcherResults(
    a: () -> MatcherResult,
    b: () -> MatcherResult,
    c: () -> MatcherResult,
): MatcherResult =
    a().takeIfFailed()
        ?: b().takeIfFailed()
        ?: c()

public inline fun chainMatcherResults(
    a: () -> MatcherResult,
    b: () -> MatcherResult,
    c: () -> MatcherResult,
    d: () -> MatcherResult,
): MatcherResult =
    a().takeIfFailed()
        ?: b().takeIfFailed()
        ?: c().takeIfFailed()
        ?: d()