package com.dicoding.intifada.submission1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicoding.intifada.submission1.Model.Film;
import com.dicoding.intifada.submission1.R;

import java.util.ArrayList;

public class FilmAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Film> films = new ArrayList<>();

    public void setFilms(ArrayList<Film> films) { this.films = films; }

    public FilmAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return films.size();
    }

    @Override
    public Object getItem(int i) {
        return films.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_film, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);

        Film film = (Film) getItem(i);
        viewHolder.bind(film);
        return itemView;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private TextView txtVote;
        private TextView txtLang;
        private TextView txtRuntime;
        private ImageView imgPhoto;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            txtVote = view.findViewById(R.id.vote);
            txtLang = view.findViewById(R.id.lang);
            txtRuntime = view.findViewById(R.id.runtime);
            imgPhoto = view.findViewById(R.id.img_photo);
        }

        void bind(Film film) {
            txtName.setText(film.getName());
            txtDescription.setText(film.getDescription());
            txtVote.setText(film.getVote());
            txtLang.setText(film.getLang());
            txtRuntime.setText(film.getRuntime());
            imgPhoto.setImageResource(film.getPhoto());
        }
    }
}