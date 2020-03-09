package s.com.themoviedbupcoming.domain.usecase.movie

import s.com.themoviedbupcoming.domain.usecase.AbsUseCaseSingle
import io.reactivex.Scheduler
import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.MovieInList

class ShowsUseCase(
    private val moviesRepository: IMoviesRepository,
    postExecutionThread: Scheduler
) : AbsUseCaseSingle<List<MovieInList>, ShowsUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Single<List<MovieInList>> {
        return moviesRepository.loadShows(params.isRefreshing)
    }

    class Params(val isRefreshing: Boolean)
}