package homework.chegg.com.chegghomework.network

import homework.chegg.com.chegghomework.Consts
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object WebService {

    private var retrofit: Retrofit? = null

    val client: Retrofit
        get() {
            if (retrofit == null) {

                Timber.plant(Timber.DebugTree())
                val httpLoggingInterceptor = HttpLoggingInterceptor{message -> Timber.i(message)}

                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                val okHttpClient = OkHttpClient()
                        .newBuilder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .addNetworkInterceptor(httpLoggingInterceptor)
                        .build()

                retrofit = Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl(Consts.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return retrofit!!
        }

}