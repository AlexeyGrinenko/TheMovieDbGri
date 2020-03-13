package s.com.themoviedbupcoming.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*
import s.com.themoviedbupcoming.domain.model.Genre

class GenreViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
    fun bind(genre: Genre) {
        itemView.apply {
            tvMovieItem?.text = genre.name

        }
    }
}