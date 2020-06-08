package com.dicoding.intifada.consumerapp.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.intifada.consumerapp.R;
import com.dicoding.intifada.consumerapp.recyclerview.model.Film;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private ArrayList<Film> moviesData = new ArrayList<>();

    public void setData(ArrayList<Film> items) {
        moviesData.clear();
        moviesData.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new MoviesViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MoviesViewHolder holder, final int position) {
        holder.bind(moviesData.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtRelease, txtOverview, txtScore;
        CardView cvFilm;
        ImageView imgPoster;

        MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtRelease = itemView.findViewById(R.id.txt_release);
            txtScore = itemView.findViewById(R.id.txt_score);
            txtOverview = itemView.findViewById(R.id.txt_overview);
            imgPoster = itemView.findViewById(R.id.img_poster);
            cvFilm = itemView.findViewById(R.id.cvFilm);
        }

        void bind(Film movie) {
            txtTitle.setText(movie.getTitle());
            txtRelease.setText(movie.getRelease());
            txtScore.setText(String.valueOf(movie.getScore()));
            txtOverview.setText(movie.getOverview());
            Glide.with(itemView.getContext())
                    .load(movie.getPoster())
                    .apply(new RequestOptions().override(350, 550))
                    .into(imgPoster);
        }
    }
}
