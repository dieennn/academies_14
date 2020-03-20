package com.dicoding.intifada.submission5.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.intifada.submission5.R;
import com.dicoding.intifada.submission5.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> list;
    private OnItemClickCallback onItemClickCallback;
    private final ArrayList<Movie> listMovie = new ArrayList<>();

    public MovieAdapter() {
        list = new ArrayList<>();
    }

    public void setData(ArrayList<Movie> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setOnItemFavoriteCallback(OnItemFavoriteCallback onItemFavoriteCallback){
    }

    public void updateItem(int position, Movie movie){
        this.listMovie.set(position, movie);
        notifyItemChanged(position, movie);
    }

    public interface OnItemFavoriteCallback{
        void onItemFavorited(Movie data, int position);
    }

    public void setListMovie(ArrayList<Movie> listMovie){

        if(listMovie.size() > 0){
            this.listMovie.clear();
        }
        this.listMovie.addAll(listMovie);

        notifyDataSetChanged();
    }

    public interface OnItemClickCallback{
        void onItemClicked(Movie data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Movie movie = list.get(position);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185" + movie.getPoster())
                .apply(new RequestOptions().override(55,55))
                .into(holder.ivPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvOverview;
        ImageView ivPoster;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvOverview = itemView.findViewById(R.id.tv_item_description);
            ivPoster = itemView.findViewById(R.id.img_item_photo);

        }
    }
}
