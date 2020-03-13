package s.com.themoviedbupcoming.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_movie_in_list.view.*
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.domain.model.MovieInList
import s.com.themoviedbupcoming.presentation.BASE_IMAGE_URL

class MoviesViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
    fun bind(movie: MovieInList, clickListener: (MovieInList) -> Unit) {
        itemView.apply {
            tvTitleItemList?.text = movie.title
            tvReleaseDateItemList?.text =
                "${movie.releaseDate?.subSequence(0,4)}"



        }
        itemView.setOnClickListener { clickListener(movie) }
    }
}