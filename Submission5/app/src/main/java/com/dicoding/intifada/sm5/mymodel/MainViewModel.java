package com.dicoding.intifada.sm5.mymodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.intifada.sm5.myapi.APIClientMovieTv;
import com.dicoding.intifada.sm5.myapi.APIMovieTv;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.dicoding.intifada.sm5.addingmethod.Constant.API_KEY;
import static com.dicoding.intifada.sm5.addingmethod.Constant.API_KEY_GITHUB;


public class MainViewModel extends ViewModel {
    //private static final String TAG = MainViewModel.class.getSimpleName();
    private static final String LANGUAGE = "en-US";

    private MutableLiveData<ArrayList<MovieItems>> listMovies = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvShowItems>> listTvShow = new MutableLiveData<>();

    private MutableLiveData<ArrayList<FollowItemsParcelable>> listFollowers = new MutableLiveData<>();
    private MutableLiveData<ArrayList<FollowItemsParcelable>> listFollowing = new MutableLiveData<>();

    /*public void setListGithub() {
        APIMovieTv apiService = APIClientMovieTv.getClientGithub().create(APIMovieTv.class);
        //Call call = apiService.getMovieList(API_KEY, LANGUAGE);
        Call call = apiService.getUser(API_KEY_GITHUB);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("TAG", "response 33: "+new Gson().toJson(response.body()) );
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.toString() );
                // Log error here since request failed
            }
        });
    }*/

    public void setListFollowers(String login) {
        APIMovieTv apiMovieTv = APIClientMovieTv.getClientGithub().create(APIMovieTv.class);
        Call<ArrayList<FollowItemsParcelable>> call = apiMovieTv.getFollowerParcelable(API_KEY_GITHUB, login);
        final ArrayList<FollowItemsParcelable> followersItems = new ArrayList<>();
        call.enqueue(new Callback<ArrayList<FollowItemsParcelable>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<FollowItemsParcelable>> call, @NonNull Response<ArrayList<FollowItemsParcelable>> response) {
                List<FollowItemsParcelable> followersItemList = null;
                Timber.d("setListFollowers%s", new Gson().toJson(response.body()));
                if (response.body() != null) {
                    followersItemList = response.body();
                }
                try {
                    if (followersItemList != null) {
                        followersItems.addAll(followersItemList);
                    }
                    listFollowers.postValue(followersItems);
                } catch (Exception e) {
                    Timber.d(e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<FollowItemsParcelable>> call, @NonNull Throwable t) {
                Timber.d("ERROR : %s", t.getMessage());
            }
        });
    }

    public void setListFollowing(String login) {
        APIMovieTv apiMovieTv = APIClientMovieTv.getClientGithub().create(APIMovieTv.class);
        Call<ArrayList<FollowItemsParcelable>> call = apiMovieTv.getFollowingParcelable(API_KEY_GITHUB, login);
        final ArrayList<FollowItemsParcelable> followingItems = new ArrayList<>();
        call.enqueue(new Callback<ArrayList<FollowItemsParcelable>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<FollowItemsParcelable>> call, @NonNull Response<ArrayList<FollowItemsParcelable>> response) {
                List<FollowItemsParcelable> followingItemList = null;
                Timber.d("setListFollowing%s", new Gson().toJson(response.body()));
                if (response.body() != null) {
                    followingItemList = response.body();
                }
                try {
                    if (followingItemList != null) {
                        followingItems.addAll(followingItemList);
                    }
                    listFollowing.postValue(followingItems);
                } catch (Exception e) {
                    Timber.d(e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<FollowItemsParcelable>> call, @NonNull Throwable t) {
                Timber.d("ERROR : %s", t.getMessage());
            }
        });
    }

    public void setListMovies() {

        APIMovieTv apiMovieTv = APIClientMovieTv.getClientGithub().create(APIMovieTv.class);
        Call<MoviesResponse> call = apiMovieTv.getMovieList(API_KEY_GITHUB);
        //Call<MoviesResponse> call = apiMovieTv.getMovieList(API_KEY);
        final ArrayList<MovieItems> movieItems = new ArrayList<>();
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                Timber.d("setListMovies : %s", new Gson().toJson(response.body()));
                List<MovieItems> movieItemList = null;
                if (response.body() != null) {
                    movieItemList = response.body().getResults();
                }
                try {
                    if (movieItemList != null) {
                        movieItems.addAll(movieItemList);
                    }
                    listMovies.postValue(movieItems);
                } catch (Exception e) {
                    Timber.d(e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                Timber.d("ERROR : %s", t.getMessage());
            }
        });
    }

    public void setListTvShow() {
        APIMovieTv apiMovieTv = APIClientMovieTv.getClient().create(APIMovieTv.class);
        Call<TvShowResponse> call = apiMovieTv.getTvShowList(API_KEY, LANGUAGE);
        final ArrayList<TvShowItems> tvShowItems = new ArrayList<>();
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                List<TvShowItems> movieItemList = null;
                if (response.body() != null) {
                    movieItemList = response.body().getResults();
                }
                try {
                    if (movieItemList != null) {
                        tvShowItems.addAll(movieItemList);
                    }
                    listTvShow.postValue(tvShowItems);
                } catch (Exception e) {
                    Timber.d(e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                Timber.d("ERROR : %s", t.getMessage());
            }
        });
    }

    public void setSeacrhMovies(String strQuery) {
        APIMovieTv apiMovieTv = APIClientMovieTv.getClientGithub().create(APIMovieTv.class);
        Call<MoviesResponse> call = apiMovieTv.getUserSearch(API_KEY_GITHUB, strQuery);
        final ArrayList<MovieItems> movieItems = new ArrayList<>();
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                //Timber.tag("Data").d(new Gson().toJson(response.body().getResults()));
                List<MovieItems> movieItemList = null;
                if (response.body() != null) {
                    movieItemList = response.body().getResults();
                    Timber.tag("setSearch").d(new Gson().toJson(movieItemList));
                }
                try {
                    if (movieItemList != null) {
                        movieItems.addAll(movieItemList);
                    }
                    listMovies.postValue(movieItems);
                } catch (Exception e) {
                    Timber.d(e);
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull Throwable t) {
                Timber.d("onFailure: %s", t.toString());
                // Log error here since request failed
            }
        });
        /*final String sa = (strQuery);
        APIMovieTv apiMovieTv = APIClientMovieTv.getClient().create(APIMovieTv.class);
        Call<MoviesResponse> call = apiMovieTv.getMovieSearch(API_KEY, LANGUAGE, strQuery);
        final ArrayList<MovieItems> movieItems = new ArrayList<>();
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                assert response.body() != null;
                Timber.d("setSeacrhMovies : %s", new Gson().toJson(response.body().getResults()));
                Log.d("Query", sa);
                Log.d("Data", new Gson().toJson(response.body().getResults()));
                List<MovieItems> movieItemList = null;
                if (response.body() != null) {
                    movieItemList = response.body().getResults();
                }
                try {
                    if (movieItemList != null) {
                        movieItems.addAll(movieItemList);
                    }
                    listMovies.postValue(movieItems);
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "ERROR : " + t.getMessage());
            }
        });*/
    }

    public void setSearchTvShow(String strQuery) {
        APIMovieTv apiMovieTv = APIClientMovieTv.getClient().create(APIMovieTv.class);
        Call<TvShowResponse> call = apiMovieTv.getTvShowSearch(API_KEY, LANGUAGE, strQuery);
        final ArrayList<TvShowItems> tvShowItems = new ArrayList<>();
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                List<TvShowItems> movieItemList = null;
                if (response.body() != null) {
                    movieItemList = response.body().getResults();
                }
                try {
                    if (movieItemList != null) {
                        tvShowItems.addAll(movieItemList);
                    }
                    listTvShow.postValue(tvShowItems);
                } catch (Exception e) {
                    Timber.d(e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                Timber.d("ERROR : %s", t.getMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<MovieItems>> getListMovies() {
        return listMovies;
    }

    public MutableLiveData<ArrayList<TvShowItems>> getListTvShow() {
        return listTvShow;
    }

    public MutableLiveData<ArrayList<FollowItemsParcelable>> getListFollowers() {
        return listFollowers;
    }

    public MutableLiveData<ArrayList<FollowItemsParcelable>> getListFollowing() {
        return listFollowing;
    }
}
