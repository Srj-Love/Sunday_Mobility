package com.example.sundaymobility.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var mRetrofit: Retrofit? = null
    private var mRetrofitInstance: Retrofit? = null
    //        return httpClient.build();
    private val okHttpClient: OkHttpClient
        private get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
            return httpClient.addInterceptor(logging).build()
            //        return httpClient.build();
        }

    private val gson = GsonBuilder()
        .serializeNulls()
        .setLenient()
        .create()

    fun getClient(base_url: String?): Retrofit? {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return mRetrofit
    }

    fun getMapClient(base_url: String?): Retrofit? {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return mRetrofit
    }

    fun getmRetrofit(base_url: String?): Retrofit? {
        if (mRetrofitInstance == null) {
            mRetrofitInstance = Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return mRetrofitInstance
    }

    // for SMS
    fun getSMSClient(base_url: String?): Retrofit? {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return mRetrofit
    }

    // for SMS
    fun getImageClient(base_url: String?): Retrofit? {
        if (mRetrofit != null) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return mRetrofit
    }
}