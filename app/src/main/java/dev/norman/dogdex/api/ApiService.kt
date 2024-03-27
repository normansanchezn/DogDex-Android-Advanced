package dev.norman.dogdex.api

import com.google.gson.Gson
import dev.norman.dogdex.Constants
import dev.norman.dogdex.api.responses.DogListApiResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(Gson()))
    .client(
        OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    )
    .build()

interface ApiService {
    @GET(Constants.PATH_GET_ALL_DOGS)
    suspend fun getAllDogs(): DogListApiResponse
}

object DogsApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}