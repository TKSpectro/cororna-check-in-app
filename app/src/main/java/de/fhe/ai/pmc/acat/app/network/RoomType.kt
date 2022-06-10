package de.fhe.ai.pmc.acat.app.network

data class RoomType(
    val Id: String,
    val Name: String,
    val MaxParticipants: Int,
    val MaxDuration: Int,
    val Faculty: Int,
    val QrCode : String?,
    val Sessions: String?,
)