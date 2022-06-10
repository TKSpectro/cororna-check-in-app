package de.fhe.ai.pmc.acat.app.network

data class SessionType(
    val Id: String,
    val StartTime: String,
    val EndTime: String,
    val Infected: Boolean,
    val RoomId: String,
    val Room : RoomType?,
    val UserId: String,
    val User : String?,
)