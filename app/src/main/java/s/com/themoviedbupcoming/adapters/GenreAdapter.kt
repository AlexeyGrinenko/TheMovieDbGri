package s.com.themoviedbupcoming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.domain.model.Genre
import s.com.themoviedbupcoming.domain.model.MovieInList

class GenreAdapter(private val movieFilds: ArrayList<Genre>) : RecyclerView.Adapter<GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GenreViewHolder(LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_movie, parent, false))


    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(movieFilds[position])
    }

    override fun getItemCount() = movieFilds.size
}
