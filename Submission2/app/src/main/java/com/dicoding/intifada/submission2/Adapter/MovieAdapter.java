package com.dicoding.intifada.submission2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicoding.intifada.submission2.R;
import com.dicoding.intifada.submission2.activity.DetailMovieActivity;
import com.dicoding.intifada.submission2.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewAdapter> {
    private ArrayList<Movie> listMovie;
    private Context context;

    public MovieAdapter(ArrayList<Movie> list) {
        this.listMovie = list;
    }

    @NonNull
    @Override
    public MovieViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        this.context = viewGroup.getContext();
        return new MovieViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewAdapter holder, int position) {
        final Movie movie = listMovie.get(position);

        Glide.with(holder.itemView.getContext())
                .load(movie.getPhoto())
                .into(holder.imgPhotoMovie);

        holder.tvNameMovie.setText(movie.getNameFilm() + " (" + movie.getYearFilm() + ")");
        holder.tvDateMovie.setText(movie.getDateFilm());

        holder.tvScoreMovie.setText(movie.getScore());
        holder.tvLanguageMovie.setText(movie.getLanguage());
        holder.tvRuntimeMovie.setText(movie.getRuntime());
        holder.tvDescriptionMovie.setText(movie.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Movie movie1 = new Movie();
                movie1.setPhoto(movie.getPhoto());
                movie1.setPhotoPoster(movie.getPhotoPoster());
                movie1.setNameFilm(movie.getNameFilm());
                movie1.setYearFilm(movie.getYearFilm());
                movie1.setOverview(movie.getOverview());

                movie1.setScore(movie.getScore());
                movie1.setLanguage(movie.getLanguage());
                movie1.setRuntime(movie.getRuntime());
                movie1.setDescription(movie.getDescription());

                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra("EXTRA_MOVIE", movie1);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class MovieViewAdapter extends RecyclerView.ViewHolder {
        ImageView imgPhotoMovie;
        TextView tvNameMovie;
        TextView tvDateMovie;

        TextView tvScoreMovie;
        TextView tvLanguageMovie;
        TextView tvRuntimeMovie;
        TextView tvDescriptionMovie;

        MovieViewAdapter(@NonNull View itemView) {
            super(itemView);
            imgPhotoMovie = itemView.findViewById(R.id.img_photo_movie);
            tvNameMovie = itemView.findViewById(R.id.tv_name_movie);
            tvDateMovie = itemView.findViewById(R.id.tv_date_movie);

            tvScoreMovie = itemView.findViewById(R.id.tv_score_movie);
            tvLanguageMovie = itemView.findViewById(R.id.tv_languange_movie);
            tvRuntimeMovie = itemView.findViewById(R.id.tv_runtime_movie);
            tvDescriptionMovie = itemView.findViewById(R.id.tv_description_movie);
        }
    }
}