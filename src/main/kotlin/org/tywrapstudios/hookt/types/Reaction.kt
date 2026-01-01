package org.tywrapstudios.hookt.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reaction(
    val count: Int,
    @SerialName("count_details")
    val countDetails: CountDetails,
    val me: Boolean,
    @SerialName("me_burst")
    val meBurst: Boolean,
    val emoji: PartialEmoji,
    @SerialName("burst_colors")
    val burstColors: List<String>,
)

@Serializable
data class CountDetails(
    val burst: Int,
    val normal: Int,
)

@Serializable
data class PartialEmoji(
    val id: ULong?,
    val name: String?,
    val roles: List<ULong>?,
    @SerialName("require_colons")
    val requireColons: Boolean?,
    val managed: Boolean?,
    val animated: Boolean?,
    val available: Boolean?,
)