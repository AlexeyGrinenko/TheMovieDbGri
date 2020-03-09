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
    fun bind(show: MovieInList, clickListener: (MovieInList) -> Unit) {
        itemView.apply {
            tvTitleItemList?.text = show.title
            tvReleaseDateItemList?.text =
                "${context.getString(R.string.release_on)} ${show.releaseDate}"
            tvPopularityItemList?.text =
                "${context.getString(R.string.popularity)} ${show.popularity}"
            tvVotesItemList?.text = "${context.getString(R.string.votes)} ${show.voteCount ?: 0}"

            Glide.with(this)
                .load(BASE_IMAGE_URL + show.posterPath)
                .apply(RequestOptions()
                    .placeholder(R.drawable.ic_the_movie_logo))
                .into(ivImageItemList)

        }
        itemView.setOnClickListener { clickListener(show) }
    }
}