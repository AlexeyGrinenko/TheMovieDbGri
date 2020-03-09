package s.com.themoviedbupcoming.remote.http

import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import s.com.themoviedbupcoming.remote.utils.API_KEY
import s.com.themoviedbupcoming.remote.utils.BASE_URL_HTTPS
import java.util.concurrent.TimeUnit

val remoteDatasourceModule = module {
    // provided web components
    single { createOkHttpClient() }
    // Fill property
    single { createWebService<MoviesService>(get(), BASE_URL_HTTPS) }
}


const val TIMEOUT = 120L

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val headerInterceptor = Interceptor { chain ->
        val url = chain.request().url().newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()
        val request =
            chain.request()
                .newBuilder().apply {
//                    addHeader("api_key", API_KEY)
                }
            .url(url)
            .build()
        chain.proceed(request)
    }
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor(headerInterceptor)
        .addInterceptor(RemoteErrorInterceptor())
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}