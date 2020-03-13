package s.com.themoviedbupcoming.domain.usecase.movie

import io.reactivex.Scheduler
import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.Genre
import s.com.themoviedbupcoming.domain.usecase.AbsUseCaseSingle

class GenresUseCase(
    private val genresRepository: IGenriesRepository,
    postExecutionThread: Scheduler
) : AbsUseCaseSingle<List<Genre>, GenresUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Single<List<Genre>> {
        return genresRepository.loadGenries()
    }

    class Params()

}