package com.dicoding.intifada.submission5.adapter;

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
import com.dicoding.intifada.submission5.Constant;
import com.dicoding.intifada.submission5.activity.DetailActivity;
import com.dicoding.intifada.submission5.R;
import com.dicoding.intifada.submission5.model.Movie;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<Movie> filmArrayList = new ArrayList<>();
    private Context context;

    public ArrayList<Movie> getFilm(){
        return filmArrayList;
    }

    public void setFilm(ArrayList<Movie> films){
        this.filmArrayList = films;
    }

    public SearchAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<Movie> data){
        filmArrayList.clear();
        filmArrayList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new SearchAdapter.ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.bind(filmArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return filmArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        TextView  tittle, popularity, view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.ivBackdrop);
            tittle =  itemView.findViewById(R.id.tvTitle);
            popularity = itemView.findViewById(R.id.tvRateTag);
            view = itemView.findViewById(R.id.tvOverview);

            itemView.setOnClickListener(this);
        }

        public void bind(Movie film) {

            String populer = Double.toString(film.getPopularity());
            String  imageData = Constant.API_URL_IMAGE + film.getPhoto();

            tittle.setText(film.getTitle());
            popularity.setText(populer);
            view.setText(film.getVote());

            Glide.with(itemView.getContext())
                    .load(imageData)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(img);
        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            Movie film = filmArrayList.get(i);

            film.setTitle(film.getTitle());

            Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.DATA_FILM, getFilm().get(i));
            itemView.getContext().startActivity(intent);
        }
    }
}
