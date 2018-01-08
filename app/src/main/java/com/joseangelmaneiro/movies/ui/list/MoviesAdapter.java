package com.joseangelmaneiro.movies.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.joseangelmaneiro.movies.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder>{

    private MovieListPresenter mPresenter;


    public MoviesAdapter(MovieListPresenter presenter){
        this.mPresenter = presenter;
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
        mPresenter.configureCell(movieHolder, position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getItemsCount();
    }

    public void refreshData(){
        notifyDataSetChanged();
    }


    public class MovieHolder extends RecyclerView.ViewHolder implements MovieCellView,
            View.OnClickListener{

        @BindView(R.id.image) ImageView imageView;

        public MovieHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void displayImage(String url) {
            Picasso.with(imageView.getContext())
                    .load(url)
                    .placeholder(R.drawable.movie_placeholder)
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
            mPresenter.onItemClick(getAdapterPosition());
        }

    }

}
