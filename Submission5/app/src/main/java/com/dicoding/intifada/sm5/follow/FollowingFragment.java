package com.dicoding.intifada.sm5.follow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dicoding.intifada.sm5.R;
import com.dicoding.intifada.sm5.adapter.FollowingItemsAdapter;
import com.dicoding.intifada.sm5.addingmethod.AllMyStrings;
import com.dicoding.intifada.sm5.addingmethod.CheckNetwork;
import com.dicoding.intifada.sm5.myactivity.DetailsMovieActivity;
import com.dicoding.intifada.sm5.mymodel.MainViewModel;
import com.dicoding.intifada.sm5.mymodel.FollowItemsParcelable;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import timber.log.Timber;

public class FollowingFragment extends Fragment {

  private RecyclerView recyclerViewMovie;
  private MainViewModel mainViewModel;
  private FollowingItemsAdapter followingItemsAdapter;
  private SwipeRefreshLayout refreshLayoutMovie;
  private RelativeLayout frameLayoutMovie;
  private String noInternet, tryAgain, reconnect, wrongNet, wrongError;
  private String textSearch;
  private TextView textViewEmpty;

  private static final String TAG = "Recents";

  private PageViewModel pageViewModel;

  public FollowingFragment() {
    // Required empty public constructor
  }

  /**
   * @return A new instance of fragment RecentsFragment.
   */
  public static FollowingFragment newInstance() {
    return new FollowingFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
    pageViewModel.setIndex(TAG);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    /*// Inflate the layout for this fragment
    View root = inflater.inflate(R.layout.fragment_main, container, false);
    final TextView textView = root.findViewById(R.id.section_label);
    pageViewModel.getText().observe(this, new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;*/
    return inflater.inflate(R.layout.fragment_nav_movies, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setHasOptionsMenu(true);// method for menu search
    textViewEmpty = view.findViewById(R.id.tv_db_movies_empty);
    refreshLayoutMovie = view.findViewById(R.id.swipe_scroll_movie);
    frameLayoutMovie = view.findViewById(R.id.framel_movie);
    recyclerViewMovie = view.findViewById(R.id.rv_tab_movies);

    if (getActivity() != null) {
      mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
      mainViewModel.getListFollowing().observe(getActivity(), getFollowing);
      followingItemsAdapter = new FollowingItemsAdapter(getActivity());
    }
    //refreshLayoutMovie.setOnRefreshListener(this);

    //callmethod
    getAllMyString();
    checkingNetwork();
    refreshLayoutMovie.setRefreshing(true);
  }

  private void getAllMyString() {
    AllMyStrings myStr = new AllMyStrings();
    noInternet = myStr.getNoInet(getContext());
    tryAgain = myStr.getTryAgain(getContext());
    reconnect = myStr.getRecon(getContext());
    wrongNet = myStr.getWrongNet(getContext());
    wrongError = myStr.getWrongErr(getContext());
  }

  private Observer<? super ArrayList<FollowItemsParcelable>> getFollowing = new Observer<ArrayList<FollowItemsParcelable>>() {
    @Override
    public void onChanged(ArrayList<FollowItemsParcelable> followingItems) {
      if (followingItemsAdapter != null) {
        followingItemsAdapter.setFollowersData(followingItems);
        if (followingItems.size() < 1) {
          textViewEmpty.setVisibility(View.VISIBLE);
        } else {
          textViewEmpty.setVisibility(View.INVISIBLE);
        }
        timeRecyclerLoadFalse();
      }
    }
  };

  private void checkingNetwork() {
    if (getContext() != null) {
      if (CheckNetwork.isInternetAvailable(getContext())) {
        int status = CheckNetwork.statusInternet;
        if (status == 1) {//connected
          showRecyclerList(textSearch);
        } else if (status == 0) {//disconnect
          timeRecyclerLoadFalse();
          Snackbar snackbar = Snackbar.make(frameLayoutMovie, noInternet, Snackbar.LENGTH_SHORT).setAction(tryAgain, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              checkingNetwork();
            }
          });
          snackbar.setActionTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
          snackbar.show();
        } else if (status == 2) {//reconnection
          Toast.makeText(getContext(), reconnect, Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(getContext(), wrongNet, Toast.LENGTH_SHORT).show();
        }
      } else {
        Toast.makeText(getContext(), wrongError, Toast.LENGTH_SHORT).show();
      }
    }
  }

  private void timeRecyclerLoadFalse() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        if (refreshLayoutMovie.isRefreshing()) {
          refreshLayoutMovie.setRefreshing(false);
        }
      }
    }, 3000);
  }

  private void showRecyclerList(String query) {
    if (mainViewModel != null) {
      DetailsMovieActivity activity = (DetailsMovieActivity) getActivity();
      assert activity != null;
      String myDataFromActivity = activity.getMyData();
      if (query != null) {
        if (!query.equals("")) {
          mainViewModel.setSeacrhMovies(query);
        } else {
          mainViewModel.setListFollowing(myDataFromActivity);
        }
      } else {
        mainViewModel.setListFollowing(myDataFromActivity);
      }
      followingItemsAdapter.notifyDataSetChanged();
      recyclerViewMovie.setHasFixedSize(true);
      recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext()));
      recyclerViewMovie.setAdapter(followingItemsAdapter);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Toast.makeText(getActivity(), "NAV", Toast.LENGTH_SHORT).show();
  }
}
