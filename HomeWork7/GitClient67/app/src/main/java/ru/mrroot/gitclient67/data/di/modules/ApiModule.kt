package ru.mrroot.gitclient67.data.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.mrroot.gitclient67.data.api.GitHubApi
import ru.mrroot.gitclient67.data.api.GitHubApiInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("github_api")
    @Provides
    fun provideBaseUrlProd(): String = "https://api.github.com"

    @Singleton
    @Provides
    fun provideGitHubApi(@Named("github_api") baseUrl: String): GitHubApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(GitHubApiInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GitHubApi::class.java)

    private val gson: Gson =
        GsonBuilder()
            .create()

}