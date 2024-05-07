package com.example.publicdomainfilms.di

import androidx.media3.ui.BuildConfig
import com.example.publicdomainfilms.data.Repository
import com.example.publicdomainfilms.data.remote.InternetArchiveApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logLevel =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return HttpLoggingInterceptor().setLevel(logLevel)
    }

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): InternetArchiveApi {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://archive.org/advancedsearch.php?")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(InternetArchiveApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(internetArchiveApi: InternetArchiveApi): Repository =
        Repository(internetArchiveApi)
}