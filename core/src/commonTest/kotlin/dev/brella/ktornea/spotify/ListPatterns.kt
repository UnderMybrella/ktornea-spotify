package dev.brella.ktornea.spotify

import io.kotest.assertions.withClue
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

public fun <T> listShouldTestExactly(a: T.() -> Unit): List<T>.() -> Unit = {
    withClue("List must contain exactly one element") { size shouldBe 1 }
    get(0).a()
}

public fun <T> listShouldTestExactly(a: T.() -> Unit, b: T.() -> Unit): List<T>.() -> Unit = {
    withClue("List must contain exactly two element") { size shouldBe 2 }
    get(0).a()
    get(1).b()
}

public fun <T> listShouldTestExactly(a: T.() -> Unit, b: T.() -> Unit, c: T.() -> Unit): List<T>.() -> Unit = {
    withClue("List must contain exactly one element") { size shouldBe 3 }
    get(0).a()
    get(1).b()
    get(2).c()
}

public fun <T> listShouldTestExactly(a: T.() -> Unit, b: T.() -> Unit, c: T.() -> Unit, d: T.() -> Unit): List<T>.() -> Unit = {
    withClue("List must contain exactly one element") { size shouldBe 4 }
    get(0).a()
    get(1).b()
    get(2).c()
    get(3).d()
}

public fun <T> listShouldTestExactly(vararg tests: T.() -> Unit): List<T>.() -> Unit = {
    withClue({ "List must contain exactly ${tests.size} element" }) { size shouldBe tests.size }
    tests.forEachIndexed { index, test -> get(index).test() }
}

public fun <T> listShouldTestExactly(tests: Iterable<T.() -> Unit>): List<T>.() -> Unit = {
    val list = tests.toList()
    withClue({ "List must contain exactly ${list.size} element" }) { size shouldBe list.size }
    list.forEachIndexed { index, test -> get(index).test() }
}

public fun <T> listShouldStartWithTest(testFirst: T.() -> Unit): List<T>.() -> Unit = {
    withClue("List must contain at least one element") { size shouldBeGreaterThan 1 }
    get(0).testFirst()
}

public fun <T> listShouldStartWithTests(testFirst: T.() -> Unit, testSecond: T.() -> Unit): List<T>.() -> Unit = {
    withClue("List must contain at least two elements") { size shouldBeGreaterThan 2 }
    get(0).testFirst()
    get(1).testSecond()
}

public fun <T> listShouldStartWithTests(vararg tests: T.() -> Unit): List<T>.() -> Unit = {
    withClue("List must contain at least ${tests.size} element") { size shouldBeGreaterThan tests.size }
    tests.forEachIndexed { i, test -> test(get(i)) }
}