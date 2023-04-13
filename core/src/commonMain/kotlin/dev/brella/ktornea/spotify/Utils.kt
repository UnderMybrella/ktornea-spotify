package dev.brella.ktornea.spotify

import dev.brella.kornea.errors.common.KorneaResult
import dev.brella.kornea.errors.common.cast
import dev.brella.kornea.errors.common.getOrBreak
import dev.brella.kornea.errors.common.success
import kotlin.math.min

internal inline fun <T, R> Array<T>.chunkToResults(
    chunkSize: Int,
    transform: (List<T>) -> KorneaResult<R>,
): KorneaResult<List<R>> = asList().chunkToResults(chunkSize, transform)

internal inline fun <T, R> Iterable<T>.chunkToResults(
    chunkSize: Int,
    transform: (List<T>) -> KorneaResult<R>,
): KorneaResult<List<R>> = toList().chunkToResults(chunkSize, transform)

internal inline fun <T, R> List<T>.chunkToResults(
    chunkSize: Int,
    transform: (List<T>) -> KorneaResult<R>,
): KorneaResult<List<R>> {
    val results: MutableList<R> = ArrayList()

    for (i in indices step chunkSize) {
        results.add(transform(subList(i, min(i + chunkSize, size)))
            .getOrBreak { return it.cast() })
    }

    return KorneaResult.success(results)
}


internal inline fun <T, R> Array<T>.chunkFlatToResults(
    chunkSize: Int,
    transform: (List<T>) -> KorneaResult<Iterable<R>>,
): KorneaResult<List<R>> = asList().chunkFlatToResults(chunkSize, transform)

internal inline fun <T, R> Iterable<T>.chunkFlatToResults(
    chunkSize: Int,
    transform: (List<T>) -> KorneaResult<Iterable<R>>,
): KorneaResult<List<R>> = toList().chunkFlatToResults(chunkSize, transform)

internal inline fun <T, R> List<T>.chunkFlatToResults(
    chunkSize: Int,
    transform: (List<T>) -> KorneaResult<Iterable<R>>,
): KorneaResult<List<R>> {
    val results: MutableList<R> = ArrayList()

    for (i in indices step chunkSize) {
        results.addAll(transform(subList(i, min(i + chunkSize, size)))
            .getOrBreak { return it.cast() })
    }

    return KorneaResult.success(results)
}



internal inline fun <T> Array<T>.chunkEmptyToResults(
    chunkSize: Int,
    transform: (List<T>) -> KorneaResult<*>,
): KorneaResult<Unit> = asList().chunkEmptyToResults(chunkSize, transform)

internal inline fun <T> Iterable<T>.chunkEmptyToResults(
    chunkSize: Int,
    transform: (List<T>) -> KorneaResult<*>,
): KorneaResult<Unit> = toList().chunkEmptyToResults(chunkSize, transform)

internal inline fun <T> List<T>.chunkEmptyToResults(
    chunkSize: Int,
    transform: (List<T>) -> KorneaResult<*>,
): KorneaResult<Unit> {
    for (i in indices step chunkSize) {
        transform(subList(i, min(i + chunkSize, size)))
            .getOrBreak { return it.cast() }
    }

    return KorneaResult.success()
}