package com.joseangelmaneiro.movies.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.joseangelmaneiro.movies.ui.MovieViewModel;
import com.squareup.picasso.Picasso;
import com.joseangelmaneiro.movies.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder>{

    private ItemClickListener listener;
    private List<MovieViewModel> items;


    public MoviesAdapter(ItemClickListener listener, List<MovieViewModel> items){
        this.listener = listener;
        this.items = items;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewGroup instanceof RecyclerView ) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_movie, viewGroup, false);
            return new MovieHolder(view);
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    @Override
    public void onBindViewHolder(MovieHolder movieHolder, int position) {
        movieHolder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ItemClickListener{
        void onItemClick(MovieViewModel movieViewModel);
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.image) ImageView imageView;

        public MovieHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void bind(MovieViewModel movieViewModel){
            Picasso.with(imageView.getContext())
                    .load(movieViewModel.getPosterPath())
                    .placeholder(R.drawable.movie_placeholder)
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(items.get(getAdapterPosition()));
        }

    }

    public void refreshData(List<MovieViewModel> items){
        this.items = items;
        notifyDataSetChanged();
    }

}
