package de.fhe.ai.pmc.acat.domain

import com.squareup.moshi.Json
import java.time.LocalDateTime

enum class AsyncOperationState {
    LOADING,
    SAVING,
    SUCCESS,
    ERROR,
    UNDEFINED;
}

data class AsyncOperation(val status: AsyncOperationState, val message: String, val payload: Any = Unit) {

    companion object {

        fun success( message: String = "Async Op Successful", payload: Any = Unit ): AsyncOperation {
            return AsyncOperation( AsyncOperationState.SUCCESS, message, payload )
        }

        fun saving( message: String = "Async Saving"): AsyncOperation {
            return AsyncOperation(AsyncOperationState.SAVING, message)
        }

        fun error( message: String = "Error on Async Op", payload: Any = Unit ): AsyncOperation {
            return AsyncOperation(AsyncOperationState.ERROR, message, payload)
        }

        fun loading(message: String = "Async Loading"): AsyncOperation {
            return AsyncOperation(AsyncOperationState.LOADING, message)
        }

        fun undefined(message: String = "No Async Op / Undefined"): AsyncOperation {
            return AsyncOperation(AsyncOperationState.UNDEFINED, message)
        }
    }
}

data class User(
    val name: String,
    val id: Long = 0
)

data class Session (
    @Json(name = "Id")
    val id: String,
    @Json(name = "StartTime")
    val startTime: LocalDateTime,
    @Json(name = "EndTime")
    val endTime: LocalDateTime,
    @Json(name = "Infected")
    val infected: Boolean,
    @Json(name = "RoomId")
    val roomId: String,
    @Json(name = "Room")
    val room : Room?,
    @Json(name = "UserId")
    val userId: String,
    @Json(name = "User")
    val user : String?,
)

data class Room(
    @Json(name = "Id")
    val id: String,
    @Json(name = "Name")
    val name: String,
    @Json(name = "MaxParticipants")
    val maxParticipants: Int,
    @Json(name = "MaxDuration")
    val maxDuration: Int,
    @Json(name = "Faculty")
    val faculty: Int,
    @Json(name = "QrCode")
    val qrCode : String?,
    @Json(name = "Sessions")
    val sessions: String?,
)