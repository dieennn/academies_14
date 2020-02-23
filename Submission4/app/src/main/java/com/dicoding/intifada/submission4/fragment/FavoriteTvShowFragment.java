package com.dicoding.intifada.submission4.fragment;


import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dicoding.intifada.submission4.R;
import com.dicoding.intifada.submission4.adapter.FavoriteAdapter;
import com.dicoding.intifada.submission4.helper.FavoriteTvShowHelper;
import com.dicoding.intifada.submission4.helper.MappingHelper;
import com.dicoding.intifada.submission4.model.FavoriteModel;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvShowFragment extends Fragment implements LoadFavTVShowCallback {

    private com.dicoding.intifada.submission4.helper.FavoriteTvShowHelper FavoriteTvShowHelper;
    private RecyclerView rvFavTVShow;
    private FavoriteAdapter adapter;
    private FavoriteModel favModel;
    private int toDelete;

    private final int ALERT_DIALOG_DELETE = 10;
    private static final String EXTRA_STATE = "EXTRA_STATE";

    public FavoriteTvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavTVShow = view.findViewById(R.id.rvFavTVShow);
        rvFavTVShow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavTVShow.setHasFixedSize(true);

        adapter = new FavoriteAdapter(getActivity());
        adapter.setOnItemClickCallback(new FavoriteAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(FavoriteModel data, int position) {

                favModel = data;
                toDelete = position;
                showAlertDialog(ALERT_DIALOG_DELETE);

            }
        });

        rvFavTVShow.setAdapter(adapter);
        FavoriteTvShowHelper = FavoriteTvShowHelper.getInstance(getActivity().getApplicationContext());
        FavoriteTvShowHelper.open();

        if(savedInstanceState == null){
            Log.d("LOAD AWAL", "LOAD FAV TV DARI AWAL");
            new LoadFavTVShowAsync(FavoriteTvShowHelper, this).execute();
        }else {
            Log.d("LOAD DATA", "LOAD FAV TV DARI DATA");
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

    private static class LoadFavTVShowAsync extends AsyncTask<Void, Void, ArrayList<FavoriteModel>> {

        private final WeakReference<FavoriteTvShowHelper> weakHelper;
        private final WeakReference<LoadFavTVShowCallback> weakCallback;

        private LoadFavTVShowAsync(FavoriteTvShowHelper FavoriteTvShowHelper, LoadFavTVShowCallback callback){
            weakHelper = new WeakReference<>(FavoriteTvShowHelper);
            weakCallback = new WeakReference<>(callback);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<FavoriteModel> doInBackground(Void... voids) {

            Cursor dataCursor = weakHelper.get().queryAll();
            return MappingHelper.mapCursorToArrayListTV(dataCursor);
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

    private void showAlertDialog(int type) {

        String dialogTitle, dialogMessage;

        dialogMessage = getString(R.string.dialog_message_delete);
        dialogTitle = getString(R.string.dialog_title_delete);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        FavoriteTvShowHelper.deleteById(String.valueOf(favModel.getId()));
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
        Snackbar.make(rvFavTVShow, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FavoriteTvShowHelper.close();
    }
}

interface LoadFavTVShowCallback{
    void postExecute(ArrayList<FavoriteModel> favoriteModels);
}

