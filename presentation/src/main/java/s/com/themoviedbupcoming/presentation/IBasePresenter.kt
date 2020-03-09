package s.com.themoviedbupcoming.presentation

interface IBasePresenter<View> {
    fun attachView(view: View) {}
    fun detachView() {}
    fun onViewHidden() {}
}