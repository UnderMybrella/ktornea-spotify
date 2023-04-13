package dev.brella.ktornea.spotify.data

import kotlinx.serialization.Serializable

@Serializable
public data class SpotifyPaginatedData<T>(
    val href: String,
    val previous: String,
    val next: String,

    val offset: Int,
    val limit: Int,
    val total: Int,

    val items: List<T>,
)