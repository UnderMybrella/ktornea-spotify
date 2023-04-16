package dev.brella.ktornea.spotify

import io.kotest.matchers.Matcher
import io.kotest.matchers.collections.atLeastSize
import io.kotest.matchers.equalityMatcher

@Suppress("UNCHECKED_CAST")
public fun listShouldMatchExactly(a: Any?): Matcher<List<*>> {
    val sizeMatcher = atLeastSize<Any?>(1)

    val matcher =
        if (a is Matcher<*>) a as Matcher<Any?>
        else equalityMatcher(a)

    return Matcher { value ->
        chainMatcherResults(
            { sizeMatcher.test(value) },
            { matcher.test(value[0]) }
        )
    }
}

@Suppress("UNCHECKED_CAST")
public fun listShouldMatchExactly(a: Any?, b: Any?): Matcher<List<*>> {
    val sizeMatcher = atLeastSize<Any?>(2)

    val a =
        if (a is Matcher<*>) a as Matcher<Any?>
        else equalityMatcher(a)

    val b =
        if (b is Matcher<*>) b as Matcher<Any?>
        else equalityMatcher(b)

    return Matcher { value ->
        chainMatcherResults(
            { sizeMatcher.test(value) },
            { a.test(value[0]) },
            { b.test(value[1]) }
        )
    }
}

@Suppress("UNCHECKED_CAST")
public fun listShouldMatchExactly(a: Any?, b: Any?, c: Any?): Matcher<List<*>> {
    val sizeMatcher = atLeastSize<Any?>(3)

    val a =
        if (a is Matcher<*>) a as Matcher<Any?>
        else equalityMatcher(a)

    val b =
        if (b is Matcher<*>) b as Matcher<Any?>
        else equalityMatcher(b)

    val c =
        if (c is Matcher<*>) c as Matcher<Any?>
        else equalityMatcher(c)

    return Matcher { value ->
        chainMatcherResults(
            { sizeMatcher.test(value) },
            { a.test(value[0]) },
            { b.test(value[1]) },
            { c.test(value[2]) }
        )
    }
}