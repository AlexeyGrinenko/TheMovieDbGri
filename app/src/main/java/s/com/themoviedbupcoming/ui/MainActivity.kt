package s.com.themoviedbupcoming.ui

import android.os.Bundle
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.setProperty
import s.com.themoviedbupcoming.BaseActivity
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.di.KOIN_KEY_SCOPE_MAIN_ACTIVITY
import s.com.themoviedbupcoming.presentation.main.MainScreenContract
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import s.com.themoviedbupcoming.presentation.KEY_ACTIVITY

class MainActivity : BaseActivity(), MainScreenContract.MainScreenView  {
    private val scopeKoin = getKoin().getOrCreateScope(KOIN_KEY_SCOPE_MAIN_ACTIVITY)

    private val presenter: MainScreenContract.Presenter by inject { parametersOf(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setProperty(KEY_ACTIVITY, this)
        presenter.attachView(this)
        presenter.onShowsHomeClicked()
    }

    override fun onDestroy() {
        presenter.detachView()
        presenter.onViewHidden()
        scopeKoin.close()
        super.onDestroy()
    }
}
