package s.com.themoviedbupcoming.di

import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module.module
import s.com.themoviedbupcoming.BaseActivity
import s.com.themoviedbupcoming.data.repository.MovieDetailRepository
import s.com.themoviedbupcoming.ui.MainActivity
import s.com.themoviedbupcoming.data.repository.MoviesRepository
import s.com.themoviedbupcoming.data.repository.source.main.IMovieDetailDataSource
import s.com.themoviedbupcoming.data.repository.source.main.IMoviesDataSource
import s.com.themoviedbupcoming.domain.usecase.movie.IMovieDetailRepository
import s.com.themoviedbupcoming.domain.usecase.movie.IMoviesRepository
import s.com.themoviedbupcoming.domain.usecase.movie.MovieDetailUseCase
import s.com.themoviedbupcoming.domain.usecase.movie.ShowsUseCase
import s.com.themoviedbupcoming.navigation.MainScreenRouter
import s.com.themoviedbupcoming.navigation.MovieScreenRouter
import s.com.themoviedbupcoming.presentation.main.*
import s.com.themoviedbupcoming.presentation.router.IMainScreenRouter
import s.com.themoviedbupcoming.presentation.router.IMovieScreenRouter
import s.com.themoviedbupcoming.remote.ds.MovieDetailDataSource
import s.com.themoviedbupcoming.remote.ds.MoviesDataSource
import s.com.themoviedbupcoming.remote.http.MoviesService
import s.com.themoviedbupcoming.remote.http.remoteDatasourceModule
import s.com.themoviedbupcoming.remote.utils.PreferenceHelper
import s.com.themoviedbupcoming.ui.MovieDetailActivity

/**
 * Koin main module
 */
val KoinModule = module {

    single { AndroidSchedulers.mainThread() }

    single { PreferenceHelper() }

//Router
    scope<IMainScreenRouter>(KOIN_KEY_SCOPE_MAIN_ACTIVITY) { (activity: MainActivity) -> MainScreenRouter(activity) }
    scope<IMovieScreenRouter>(KOIN_KEY_SCOPE_MOVIE_ACTIVITY) { (activity: MovieDetailActivity) -> MovieScreenRouter(activity) }

// Presenter

    scope<MainScreenContract.Presenter>(scopeId = KOIN_KEY_SCOPE_MAIN_ACTIVITY) { (activity: BaseActivity) -> MainScreenPresenter( get { parametersOf(activity) }) }
    scope<MovieScreenContract.Presenter>(scopeId = KOIN_KEY_SCOPE_MOVIE_ACTIVITY) { (activity: BaseActivity) -> MovieScreenPresenter( get { parametersOf(activity) }) }

    scope<MoviesListContract.MoviesListPresenter>(scopeId = KOIN_KEY_SCOPE_MOVIES_LIST_FRAGMENT) { (activity: MainActivity) -> MoviesListPresenter(get(ShowsUseCase::class.java.name), get { parametersOf(activity) }) }
    scope<MovieDetailContract.Presenter>(scopeId = KOIN_KEY_SCOPE_MOVIE_FRAGMENT) { (activity: BaseActivity) ->
        MovieDetailPresenter(get(MovieDetailUseCase::class.java.name))
    }


//Use Case
    factory(name = ShowsUseCase::class.java.name) { ShowsUseCase(get(MoviesRepository::class.java.name), get()) }
    factory(name = MovieDetailUseCase::class.java.name) { MovieDetailUseCase(get(MovieDetailRepository::class.java.name), get()) }


//Repository
    factory<IMovieDetailRepository>(name = MovieDetailRepository::class.java.name) { MovieDetailRepository(get(MovieDetailDataSource::class.java.name)) }
    factory<IMoviesRepository>(name = MoviesRepository::class.java.name) { MoviesRepository(get(MoviesDataSource::class.java.name)) }

//Data source
    factory<IMoviesDataSource>(name = MoviesDataSource::class.java.name) { MoviesDataSource(get(MoviesService::class.java.simpleName)) }
    factory<IMovieDetailDataSource>(name = MovieDetailDataSource::class.java.name) { MovieDetailDataSource(get(MoviesService::class.java.simpleName)) }

}


/**
 * Module list
 */
val koinAppModules = listOf(KoinModule, remoteDatasourceModule)

enum class ScopeName {
    KOIN_KEY_SCOPE_MAIN_ACTIVITY(),
    KOIN_KEY_SCOPE_MOVIE_ACTIVITY(),

    KOIN_KEY_SCOPE_MOVIES_LIST_FRAGMENT(),
    KOIN_KEY_SCOPE_MOVIE_FRAGMENT(),
}

val KOIN_KEY_SCOPE_MAIN_ACTIVITY = ScopeName.KOIN_KEY_SCOPE_MAIN_ACTIVITY.name
val KOIN_KEY_SCOPE_MOVIE_ACTIVITY = ScopeName.KOIN_KEY_SCOPE_MOVIE_ACTIVITY.name

val KOIN_KEY_SCOPE_MOVIES_LIST_FRAGMENT = ScopeName.KOIN_KEY_SCOPE_MOVIES_LIST_FRAGMENT.name
val KOIN_KEY_SCOPE_MOVIE_FRAGMENT = ScopeName.KOIN_KEY_SCOPE_MOVIE_FRAGMENT.name
