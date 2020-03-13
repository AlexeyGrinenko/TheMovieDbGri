package s.com.themoviedbupcoming.domain.usecase.movie

import s.com.themoviedbupcoming.domain.usecase.AbsUseCaseSingle
import io.reactivex.Scheduler
import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.MovieInList
import s.com.themoviedbupcoming.domain.model.SearchModel

class MoviesUseCase(
    private val moviesRepository: IMoviesRepository,
    postExecutionThread: Scheduler
) : AbsUseCaseSingle<List<MovieInList>, MoviesUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Single<List<MovieInList>> {
        return moviesRepository.loadShows(params.isRefreshing,params.searchModel)
    }

    class Params(
        val isRefreshing: Boolean,
       val searchModel: SearchModel
    )
}