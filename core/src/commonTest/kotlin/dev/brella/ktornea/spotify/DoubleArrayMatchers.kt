package dev.brella.ktornea.spotify

import io.kotest.assertions.print.print
import io.kotest.matchers.ComparableMatcherResult
import io.kotest.matchers.Matcher
import io.kotest.matchers.equalityMatcher

fun doubleArrayAtLeastSize(n: Int) = Matcher<DoubleArray> { value ->
    ComparableMatcherResult(
        value.size >= n,
        { "Array ${value.print().value} should contain at least $n elements" },
        { "Array ${value.print().value} should contain less than $n elements" },
        actual = value.size.toString(),
        expected = n.toString()
    )
}

public fun doubleArrayShouldStartWith(first: Any?): Matcher<DoubleArray> {
    val sizeMatcher = doubleArrayAtLeastSize(1)

    @Suppress("UNCHECKED_CAST")
    val matcher =
        if (first is Matcher<*>) first as Matcher<Any?>
        else equalityMatcher(first)

    return Matcher { value ->
        chainMatcherResults(
            { sizeMatcher.test(value) },
            { matcher.test(value[0]) }
        )
    }
}

public fun doubleArrayShouldStartWith(first: Any?, second: Any?): Matcher<DoubleArray> {
    val sizeMatcher = doubleArrayAtLeastSize(1)

    @Suppress("UNCHECKED_CAST")
    val first =
        if (first is Matcher<*>) first as Matcher<Any?>
        else equalityMatcher(first)

    @Suppress("UNCHECKED_CAST")
    val second =
        if (second is Matcher<*>) second as Matcher<Any?>
        else equalityMatcher(second)

    return Matcher { value ->
        chainMatcherResults(
            { sizeMatcher.test(value) },
            { first.test(value[0]) },
            { second.test(value[0]) }
        )
    }
}

public fun doubleArrayShouldStartWith(vararg elements: Any?): Matcher<DoubleArray> {
    val sizeMatcher = doubleArrayAtLeastSize(elements.size)

    val matchers = elements.map {
        @Suppress("UNCHECKED_CAST")
        if (it is Matcher<*>) it as Matcher<Any?>
        else equalityMatcher(it)
    }

    return Matcher { value ->
        matchers.foldIndexed(passOrBreak(sizeMatcher.test(value)) { return@Matcher it }) { index, _, matcher ->
            passOrBreak(matcher.test(value[index])) { return@Matcher it }
        }
    }
}