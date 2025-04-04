package myapplication.android.musicplayerapp.di

import myapplication.android.musicplayerapp.di.RetrofitModule.Companion.CLIENT_ID
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthQueryInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url
            .newBuilder()
            .addQueryParameter(ID, CLIENT_ID)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object{
        const val ID = "client_id"
    }
}