package s.com.themoviedbupcoming.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
    fun bind(data: String) {
        itemView.apply {
            tvMovieItem?.text = data

        }
    }
}