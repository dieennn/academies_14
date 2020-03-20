package com.dicoding.intifada.submission5.fragment;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.intifada.submission5.R;
import com.dicoding.intifada.submission5.adapter.FavoriteAdapter;
import com.dicoding.intifada.submission5.helper.FavoriteMovieHelper;
import com.dicoding.intifada.submission5.helper.MappingHelper;
import com.dicoding.intifada.submission5.model.FavoriteModel;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements LoadFavoriteMovieCallback {

    private FavoriteMovieHelper favMovieHelper;
    private RecyclerView rvFavMovie;
    private FavoriteAdapter adapter;
    private FavoriteModel favModel;
    private int toDelete;

    private static final String EXTRA_STATE = "EXTRA_STATE";

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavMovie = view.findViewById(R.id.rvFavMovie);
        rvFavMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavMovie.setHasFixedSize(true);

        adapter = new FavoriteAdapter(getActivity());
        adapter.setOnItemClickCallback(new FavoriteAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(FavoriteModel data, int position) {

                favModel = data;
                toDelete = position;
                showAlertDialog();

            }
        });

        rvFavMovie.setAdapter(adapter);
        favMovieHelper = FavoriteMovieHelper.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext());
        favMovieHelper.open();

        if(savedInstanceState == null){
            new LoadFavMoviesAsync(favMovieHelper, this).execute();
        }else {
            ArrayList<FavoriteModel> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if(list != null){
                adapter.setList(list);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getList());
    }

    private static class LoadFavMoviesAsync extends AsyncTask<Void, Void, ArrayList<FavoriteModel>> {

        private final WeakReference<FavoriteMovieHelper> weakHelper;
        private final WeakReference<LoadFavoriteMovieCallback> weakCallback;

        private LoadFavMoviesAsync(FavoriteMovieHelper favMovieHelper, LoadFavoriteMovieCallback callback){
            weakHelper = new WeakReference<>(favMovieHelper);
            weakCallback = new WeakReference<>(callback);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<FavoriteModel> doInBackground(Void... voids) {

            Cursor dataCursor = weakHelper.get().queryAll();
            return MappingHelper.mapCursorToArrayList(dataCursor);
        }
        @Override
        protected void onPostExecute(ArrayList<FavoriteModel> favoriteModels) {
            super.onPostExecute(favoriteModels);

            weakCallback.get().postExecute(favoriteModels);
        }
    }

    @Override
    public void postExecute(ArrayList<FavoriteModel> favoriteModels) {

        if(favoriteModels.size() > 0){
            adapter.setList(favoriteModels);
        }else {
            adapter.setList(new ArrayList<FavoriteModel>());
            showSnackbarMessage(getString(R.string.no_data));
        }
    }

    private void showAlertDialog() {

        String dialogTitle, dialogMessage;

        dialogMessage = getString(R.string.dialog_message_delete);
        dialogTitle = getString(R.string.dialog_title_delete);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        favMovieHelper.deleteById(String.valueOf(favModel.getId()));
                        adapter.removeItem(toDelete);
                        Toast.makeText(getActivity(), R.string.item_deleted, Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(rvFavMovie, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favMovieHelper.close();
    }
}

interface LoadFavoriteMovieCallback{
    void postExecute(ArrayList<FavoriteModel> favoriteModels);
}