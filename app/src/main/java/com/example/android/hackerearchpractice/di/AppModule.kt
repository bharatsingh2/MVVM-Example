package com.example.android.hackerearchpractice.di

import android.app.Application
import android.content.Context
import com.example.android.hackerearchpractice.api.ApiService
import com.example.android.hackerearchpractice.db.AppDatabase
import com.example.android.hackerearchpractice.db.WeatherDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return AppDatabase.buildDatabase(app)
    }

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase): WeatherDao {
        return db.dao()
    }


    @Singleton
    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        val httpClient = OkHttpClient.Builder()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .writeTimeout(30, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl("https://www.mocky.io/v2/")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
