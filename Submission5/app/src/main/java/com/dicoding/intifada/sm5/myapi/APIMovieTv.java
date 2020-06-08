package com.dicoding.intifada.sm5.myapi;

import com.dicoding.intifada.sm5.mymodel.DetailItemsParcelable;
import com.dicoding.intifada.sm5.mymodel.FollowItemsParcelable;
import com.dicoding.intifada.sm5.mymodel.MoviesResponse;
import com.dicoding.intifada.sm5.mymodel.TvShowResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface APIMovieTv {
    @GET("users/{username}")
    Call<DetailItemsParcelable> getDetailParcelable(
            @Header("Authorization") String apiKey,
            @Path("username") String searchQuery);

    @GET("users/{username}/followers")
    Call<ArrayList<FollowItemsParcelable>> getFollowerParcelable(
            @Header("Authorization") String apiKey,
            @Path("username") String searchQuery);

    @GET("users/{username}/following")
    Call<ArrayList<FollowItemsParcelable>> getFollowingParcelable(
            @Header("Authorization") String apiKey,
            @Path("username") String searchQuery);

    /*@GET("covid-19/sm5/list_view")
    Call<Model> loadListBook();*/

    /*@GET("followers")
    Call<ArrayList<Contacts>> getProjectCategories();*/

    /*@GET("sm5")
    Call<List<Message>> getInbox();*/
    /*@GET("covid-19/sm5/{url}")
    Call<ArrayList<FollowItems>> getJSON(
            @Path("url") String searchQuery);*/
    /*@GET("covid-19/sm5/{url}")
    Call<ArrayList<Contacts>> getJSON(
            @Path("url") String searchQuery);*/
    /*@GET("users/{username}/followers")
    Call<ArrayList<FollowItems>> getFollower(
            @Path("username") String searchQuery);*/

    /*@GET("users/{username}/following")
    Call<ArrayList<FollowItems>> getFollowing(
            @Path("username") String searchQuery);*/

    /*@GET("followers")
    Call<List<Contacts>> getContacts();*/

    //@GET("users/dien16")
    /*@GET("search/users?q=dien")
    Call<Object> getUser(
            @Header("Authentication") String apiKey);*/


    @GET("search/users")
    Call<MoviesResponse> getUserSearch(
            @Header("Authorization") String apiKey,
            @Query("q") String searchQuery);

    /*@GET("users/{username}/followers")
    Call<MoviesResponse> getUserFollow(
            @Header("Authorization") String apiKey,
            //@Path(value = "username", encoded = true) String username);
            @Path("username") String searchQuery);*/

    @GET("search/users?q=dien")
    Call<MoviesResponse> getMovieList(
            @Query("api_key") String apiKey);

    /*@GET("search/users?q=dien")
    Call<FollowersResponse> getFollowersList(
            @Query("api_key") String apiKey);*/

    /*@GET("discover/movie")
    Call<MoviesResponse> getMovieList(
            @Query("api_key") String apiKey,
            @Query("language") String language);*/

    @GET("discover/tv")
    Call<TvShowResponse> getTvShowList(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    /*@GET("search/users?q=dien16")
    Call<MoviesResponse> getMovieSearch(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String searchQuery);*/

    /*@GET("search/movie")
    Call<MoviesResponse> getMovieSearch(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String searchQuery);*/

    @GET("search/tv")
    Call<TvShowResponse> getTvShowSearch(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String searchQuery);
//
//
//    @GET("discover/movie")
//    Call<MoviesResponse> getMovieRelease(
//            @Query("api_key") String apiKey,
//            @Query("primary_release_date.gte") String releaseDateGte,
//            @Query("primary_release_date.lte") String releaseDateLte);
}
