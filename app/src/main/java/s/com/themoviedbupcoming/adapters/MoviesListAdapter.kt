package s.com.themoviedbupcoming.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.domain.model.MovieInList

class MoviesListAdapter(private val movies: ArrayList<MovieInList>, private val clickListener: (MovieInList) -> Unit) : RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_movie_in_list, parent, false))


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position], clickListener)
    }

    override fun getItemCount() = movies.size
}
