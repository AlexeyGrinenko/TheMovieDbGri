package s.com.themoviedbupcoming.domain.exceptions

class TheMovieDbHttpException(override val message: String?, val httpStatus: Int) : RuntimeException(message)