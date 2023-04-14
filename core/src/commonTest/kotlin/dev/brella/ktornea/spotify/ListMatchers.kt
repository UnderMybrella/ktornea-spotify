package dev.brella.ktornea.spotify

import io.kotest.assertions.print.print
import io.kotest.matchers.ComparableMatcherResult
import io.kotest.matchers.Matcher
import io.kotest.matchers.equalityMatcher

@Suppress("UNCHECKED_CAST")
public fun listShouldMatchExactly(single: Any?): Matcher<List<*>> {
    val matcher =
        if (single is Matcher<*>) single as Matcher<List<*>>
        else equalityMatcher(single)

    return Matcher { value ->
        when (val size = value.size) {
            0 -> ComparableMatcherResult(
                false,
                { "${value.print().value} should not be empty" },
                { "${value.print().value} should be empty" },
                "0",
                "1"
            )

            1 -> matcher.test(value)

            else -> ComparableMatcherResult(
                false,
                { "${value.print().value} should only have one element" },
                { "${value.print().value} should not only have one element" },
                size.toString(),
                "1"
            )
        }
    }
}