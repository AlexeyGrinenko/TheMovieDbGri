package s.com.themoviedbupcoming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.domain.model.MovieInList

class MovieAdapter(private val movieFilds: ArrayList<String>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_movie, parent, false))


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieFilds[position])
    }

    override fun getItemCount() = movieFilds.size
}
