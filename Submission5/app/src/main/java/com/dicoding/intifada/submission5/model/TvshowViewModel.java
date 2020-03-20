package com.dicoding.intifada.submission5.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.intifada.submission5.BuildConfig;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class TvshowViewModel extends ViewModel {

    //https://www.dicoding.com/blog/registrasi-testing-themoviedb-api/
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;
    private MutableLiveData<ArrayList<Movie>> listTvshow = new MutableLiveData<>();

    public void setListMovie(){

        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    Log.d("HASIL : ", result);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    Log.d("LIST : ", ""+list.length());

                    for (int i = 0 ; i < list.length() ; i ++){
                        JSONObject data = list.getJSONObject(i);

                        Movie movie = new Movie();
                        movie.setId(i);
                        movie.setTitle(data.getString("name"));
                        movie.setOverview(data.getString("overview"));
                        movie.setPoster(data.getString("poster_path"));
                        movie.setBackdrop(data.getString("backdrop_path"));
                        movie.setRate(data.getInt("vote_average"));
                        movie.setReleaseDate(data.getString("first_air_date"));

                        listItems.add(movie);
                    }
                    listTvshow.postValue(listItems);
                }
                catch (Exception e){
                    Log.d("Exceptoin huhu ", Objects.requireNonNull(e.getMessage()));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }
        });

    }

    public LiveData<ArrayList<Movie>> getListMovies(){
        return listTvshow;
    }

}
