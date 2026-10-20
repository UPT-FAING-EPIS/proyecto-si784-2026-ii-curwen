package com.upt.tareia.data.remote

import com.upt.tareia.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {
    @GET("health") suspend fun health(): Map<String, String>
    @GET("actividades") suspend fun activities(): List<ActivityDto>
    @GET("cursos") suspend fun courses(): List<CourseDto>
    @POST("actividades") suspend fun createActivity(@Body body: ActivityDto): ActivityDto
}

data class ActivityDto(val id: Int? = null, val titulo: String, val curso: String, val fecha_limite: String, val estado: String = "CONFIRMADA")
data class CourseDto(val id: Int? = null, val nombre: String, val abreviatura: String, val prioridad: String)

object ApiClient {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        .build()

    val service: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
