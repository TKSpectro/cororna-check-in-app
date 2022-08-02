package de.fhe.ai.pmc.acat.domain

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
    val id: String,
    val firstname: String,
    val lastname: String,
    val userName: String,
    val normalizedUserName: String,
    val email: String,
    val normalizedEmail: String,
    val emailConfirmed: Boolean,
    val passwordHash: String?,
    val securityStamp: String?,
    val concurrencyStamp: String?,
    val phoneNumber: String,
    val phoneNumberConfirmed: Boolean,
    val twoFactorEnabled: Boolean,
    val lockoutEnd: String,
    val lockoutEnabled: Boolean,
    val accessFailedCount: Int,
    val sessions: List<Session>,
    val infections: List<Infection>
)

data class Session (
    val id: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime?,
    val infected: Boolean,
    val roomId: String,
    val room : Room?,
    val userId: String,
    val user : String?,
)

data class Room(
    val id: String,
    val name: String,
    val maxParticipants: Int,
    val maxDuration: Int,
    val faculty: Int,
    val qrCode : String?,
    val sessions: String?,
)

data class Infection(
    val id: String,
    val date: LocalDateTime,
    val userId: String,
    val user: User?
)

data class LoginBody(
    val email: String,
    val password: String
)

data class AuthResponse(
    val token: String,
)

data class InfectedResponse(
    val message: String,
)

data class RegisterBody(
    val firstName : String,
    val lastName : String,
    val email : String,
    val password : String)

data class ScanBody(
    val roomId: String,
    val date: String,
)

data class ScanResponse(
    val message: String,
)