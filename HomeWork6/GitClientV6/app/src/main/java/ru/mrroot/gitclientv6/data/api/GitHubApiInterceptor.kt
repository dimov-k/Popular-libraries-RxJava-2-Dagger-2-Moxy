package ru.mrroot.gitclientv6.data.api

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import ru.mrroot.gitclientv6.BuildConfig.GITHUB_USER_NAME
import ru.mrroot.gitclientv6.BuildConfig.GITHUB_USER_PASSWORD

object GitHubApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .header("Authorization", Credentials.basic(GITHUB_USER_NAME, GITHUB_USER_PASSWORD))
                .build()
        )

}