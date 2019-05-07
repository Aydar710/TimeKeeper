package ru.timekeeper.data.di.facebook.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.timekeeper.data.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    @Named(NAME_FACEBOOK_OK_HTTP)
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    @Named(NAME_FACEBOOK_BASE_URL)
    fun provideBaseUrl(): String = FACEBOOK_API_BASE_URL

    @Provides
    @Singleton
    @Named(NAME_FACEBOOK_CONVERTER_FACTORY)
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    @Named(NAME_FACEBOOK_CALL_ADAPTER_FACTORY)
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    @Named(NAME_FACEBOOK_RETROFIT)
    fun provideRetrofit(
        @Named(NAME_FACEBOOK_OK_HTTP) client: OkHttpClient,
        @Named(NAME_FACEBOOK_CONVERTER_FACTORY) converterFactory: GsonConverterFactory,
        @Named(NAME_FACEBOOK_CALL_ADAPTER_FACTORY) callAdapterFactory: RxJava2CallAdapterFactory,
        @Named(NAME_FACEBOOK_BASE_URL) baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
}
