package de.fhe.ai.pmc.acat.app.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.fhe.ai.pmc.acat.domain.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface RequestService {

    @GET("api/rooms")
    fun listRooms(
        @Header("Authorization") token: String
    ): Call<List<Room>>

    @GET("api/rooms/{id}")
    fun getRoomById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<Room>

    @GET("api/sessions/current-session")
    fun getCurrentSession(
        @Header("Authorization") token: String,
    ): Call<Session>

    @GET("api/sessions?includeRoom=true")
    fun listSessions(
        @Header("Authorization") token: String
    ): Call<List<Session>>

    @GET("api/sessions?includeRoom=true&limit=3")
    fun getDashboardSessions(
        @Header("Authorization") token: String
    ): Call<List<Session>>

    @GET("/api/cases")
    fun setInfected(
        @Header("Authorization") token: String
    ): Call<InfectedResponse>

    @GET("/api/cases/check-sessions")
    fun checkSessions(
        @Header("Authorization") token: String
    ): Call<InfectedResponse>

  @GET("/api/infection")
    fun checkInfection(
        @Header("Authorization") token: String
    ): Call<InfectedResponse>
    
    @GET("api/me")
    fun me(
        @Header("Authorization") token: String
    ): Call<MeResponse>

    @POST("api/login")
    fun login(
        @Body body: LoginBody
    ): Call<AuthResponse>

    @POST("api/register")
    fun register(
        @Body body: RegisterBody
    ): Call<AuthResponse>

    @POST("api/qr/scan")
    fun scan(
        @Header("Authorization") token: String,
        @Body body: ScanBody
    ): Call<ScanResponse>
}

class Network {
    companion object {
        private val interceptor = Interceptor { chain ->
            val request = chain.request()
            val requestBuilder = request.newBuilder()
            val url = request.url()
            val builder = url.newBuilder()
            requestBuilder.url(builder.build())
                .method(request.method(), request.body())
                .addHeader("clientType", "IOS")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
            chain.proceed(requestBuilder.build())
        }

        private val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(LocalDateTimeAdapter())
            .build()

        private const val BASE_URL =
            "https://coronacheckin-we.azurewebsites.net/"

        private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client.build())
            .build()

        var service: RequestService = retrofit.create(RequestService::class.java)
    }
}