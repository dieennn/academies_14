package com.dicoding.intifada.submission1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dicoding.intifada.submission1.Model.Film;
import com.dicoding.intifada.submission1.Adapter.FilmAdapter;
import com.dicoding.intifada.submission1.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FilmAdapter adapter;
    private String[] dataName;
    private String[] dataDescription;
    private String[] dataVote;
    private String[] dataLang;
    private String[] dataRuntime;
    private String[] dataRelease;
    private String[] dataBudget;
    private String[] dataRevenue;
    private TypedArray dataPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lv_list);
        adapter = new FilmAdapter(this);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ArrayList<Film> films = new ArrayList<>();
                Film film = new Film();
                film.setName(dataName[i]);
                film.setPhoto(dataPhoto.getResourceId(i, -1));
                film.setDescription(dataDescription[i]);
                film.setVote(dataVote[i]);
                film.setLang(dataLang[i]);
                film.setRuntime(dataRuntime[i]);
                film.setRelease(dataRelease[i]);
                film.setBudget(dataBudget[i]);
                film.setRevenue(dataRevenue[i]);

                films.add(film);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_FILMS, films);
                startActivity(intent);
            }
        });
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataVote = getResources().getStringArray(R.array.data_score);
        dataLang = getResources().getStringArray(R.array.data_languange);
        dataRuntime = getResources().getStringArray(R.array.data_runtime);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        dataRelease = getResources().getStringArray(R.array.data_released);
        dataBudget = getResources().getStringArray(R.array.data_budget);
        dataRevenue = getResources().getStringArray(R.array.data_revenue);
    }

    private void addItem() {
        ArrayList<Film> films = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            Film film = new Film();
            film.setPhoto(dataPhoto.getResourceId(i, -1));
            film.setName(dataName[i]);
            film.setDescription(dataDescription[i]);
            film.setVote(dataVote[i]);
            film.setLang(dataLang[i]);
            film.setRuntime(dataRuntime[i]);
            film.setRelease(dataRelease[i]);
            film.setBudget(dataBudget[i]);
            film.setRevenue(dataRevenue[i]);
            films.add(film);
        }

        adapter.setFilms(films);
    }
}