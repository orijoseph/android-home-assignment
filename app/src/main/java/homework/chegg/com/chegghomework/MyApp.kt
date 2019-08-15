package homework.chegg.com.chegghomework

import android.app.Application
import homework.chegg.com.chegghomework.di.dataRepository
import homework.chegg.com.chegghomework.di.viewModulesModul
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(listOf(dataRepository, viewModulesModul))
        }
    }
}