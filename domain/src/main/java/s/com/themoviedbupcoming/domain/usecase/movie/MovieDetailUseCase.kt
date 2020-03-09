package s.com.themoviedbupcoming.domain.usecase.movie

import s.com.themoviedbupcoming.domain.usecase.AbsUseCaseSingle
import io.reactivex.Scheduler
import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.Movie

class MovieDetailUseCase(
    private val movieDetailRepository: IMovieDetailRepository,
    postExecutionThread: Scheduler
) : AbsUseCaseSingle<Movie, MovieDetailUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Single<Movie> {
        return movieDetailRepository.loadMovie(params.showId)
    }

    class Params(val showId: Int)
}