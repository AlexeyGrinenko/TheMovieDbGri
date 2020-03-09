package s.com.themoviedbupcoming

import androidx.multidex.MultiDexApplication
import s.com.themoviedbupcoming.di.koinAppModules
import org.koin.android.ext.android.setProperty
import org.koin.android.ext.android.startKoin
import s.com.themoviedbupcoming.remote.utils.KEY_APPLICATION_CONTEXT

class MovieUpcomingApplication : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        startKoin(this, koinAppModules)
        setProperty(KEY_APPLICATION_CONTEXT, applicationContext)

    }

}