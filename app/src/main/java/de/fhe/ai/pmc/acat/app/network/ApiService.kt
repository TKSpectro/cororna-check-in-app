package de.fhe.ai.pmc.acat.app.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.fhe.ai.pmc.acat.domain.Room
import de.fhe.ai.pmc.acat.domain.Session
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface RequestService {

    @GET("rooms")
    fun listRooms(): Call<List<Room>>

    @GET("sessions")
    fun listSessions(): Call<List<Session>>

//    @POST(value = "login")
//    fun login(
//        @Query("email") email: String,
//        @Query("password") password: String
//    ): Call<LogInBackData>
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
            "https://corona-check-in.azurewebsites.net/api/"

        private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl (BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client.build())
            .build()

        var service: RequestService = retrofit.create(RequestService::class.java)
    }
}