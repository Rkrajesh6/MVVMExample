package com.example.demomvvmexample.network

import com.example.demomvvmexample.utils.Util
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by rajeshkumar07 on 27-02-2020.
 */
object RetrofitService {
    private lateinit var mApiInterface: Api

    /**
     * Created by : rajeshkumar07
     * Created Date/Time : 27-02-2020 12:04
     * Description : This mehtod is used to create the Retrofit instance and perform the retrofit related
     * opration.
     */
    fun create(): Api {
        val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(Util.BASE_URL1)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.readTimeout(Util.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClient.connectTimeout(Util.REQUEST_TIMEOUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        mApiInterface = retrofit.create(Api::class.java)
        return mApiInterface
    }
    /**
     * Created by : rajeshkumar07
     * Created Date/Time : 27-02-2020 12:04
     * Description : This method is used to see the request and response logger
     */
    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}