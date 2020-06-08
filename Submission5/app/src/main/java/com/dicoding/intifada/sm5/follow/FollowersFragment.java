package com.dicoding.intifada.sm5.follow;

import android.content.Intent;
import android.os.Bundle;
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

import com.dicoding.intifada.sm5.R;
import com.dicoding.intifada.sm5.adapter.FollowersItemsAdapter;
import com.dicoding.intifada.sm5.addingmethod.AllMyStrings;
import com.dicoding.intifada.sm5.addingmethod.CheckNetwork;
import com.dicoding.intifada.sm5.myactivity.DetailsMovieActivity;
import com.dicoding.intifada.sm5.mymodel.FollowItemsParcelable;
import com.dicoding.intifada.sm5.mymodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import timber.log.Timber;

public class FollowersFragment extends Fragment {
  private RecyclerView recyclerViewMovie;
  private MainViewModel mainViewModel;
  private com.dicoding.intifada.sm5.adapter.FollowersItemsAdapter FollowersItemsAdapter;
  //private SwipeRefreshLayout refreshLayoutMovie;
  private RelativeLayout frameLayoutMovie;
  private String noInternet, tryAgain, reconnect, wrongNet, wrongError;
  private String textSearch;
  private TextView textViewEmpty;
  //private String userLogin;

  public static final String TAG = FollowersFragment.class.getSimpleName();

  public FollowersFragment() {
    // Required empty public constructor
  }

  /**
   * @return A new instance of fragment SpeedDialFragment.
   */
  static FollowersFragment newInstance() {
    return new FollowersFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
    pageViewModel.setIndex(TAG);
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                           Bundle savedInstanceState) {
    /*pageViewModel.getText().observe(this, new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        DetailsMovieActivity activity = (DetailsMovieActivity) getActivity();
        String follow = activity.getMyData();
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        textView.setText(follow);
        //textView.setText(s);
      }
    });
    // Inflate the layout for this fragment
    *//*View root = inflater.inflate(R.layout.fragment_main, container, false);
    final TextView textView = root.findViewById(R.id.section_label);*//*
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    ListView listview =(ListView) rootView.findViewById(R.id.list);
    final String[] items = new String[] {"Item 1", "Item 2", "Item 3"};
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
    listview.setAdapter(adapter);
    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity().getApplicationContext(), items[i], Toast.LENGTH_SHORT).show();
      }
    });
    return rootView;*/
    return inflater.inflate(R.layout.fragment_nav_movies, container, false);
  }

  /**/
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setHasOptionsMenu(true);// method for menu search
    textViewEmpty = view.findViewById(R.id.tv_db_movies_empty);
    //refreshLayoutMovie = view.findViewById(R.id.swipe_scroll_movie);
    frameLayoutMovie = view.findViewById(R.id.framel_movie);
    recyclerViewMovie = view.findViewById(R.id.rv_tab_movies);

    if (getActivity() != null) {
      mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
      mainViewModel.getListFollowers().observe(getActivity(), getMovie);
      FollowersItemsAdapter = new FollowersItemsAdapter(getActivity());
    }
    //refreshLayoutMovie.setOnRefreshListener(this);

    //callmethod
    getAllMyString();
    checkingNetwork();
    //callFollowers();
    //refreshLayoutMovie.setRefreshing(true);
  }

  private void getAllMyString() {
    AllMyStrings myStr = new AllMyStrings();
    noInternet = myStr.getNoInet(Objects.requireNonNull(getContext()));
    tryAgain = myStr.getTryAgain(getContext());
    reconnect = myStr.getRecon(getContext());
    wrongNet = myStr.getWrongNet(getContext());
    wrongError = myStr.getWrongErr(getContext());
  }

  private Observer<? super ArrayList<FollowItemsParcelable>> getMovie = new Observer<ArrayList<FollowItemsParcelable>>() {
    @Override
    public void onChanged(ArrayList<FollowItemsParcelable> movieItems) {
      if (FollowersItemsAdapter != null) {
        FollowersItemsAdapter.setFollowersData(movieItems);
        if (movieItems.size() < 1) {
          textViewEmpty.setVisibility(View.VISIBLE);
        } else {
          textViewEmpty.setVisibility(View.INVISIBLE);
        }
        //timeRecyclerLoadFalse();
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
          //timeRecyclerLoadFalse();
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

  /*private void timeRecyclerLoadFalse() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        if (refreshLayoutMovie.isRefreshing()) {
          refreshLayoutMovie.setRefreshing(false);
        }
      }
    }, 3000);
  }*/

  /*@Override
  public void onRefresh() {
    checkingNetwork();
  }*/

  /*@Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    SearchView searchView = (SearchView) menu.findItem(R.id.itemm_search).getActionView();
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

      @Override
      public boolean onQueryTextSubmit(String query) {
        textSearch = query;
        checkingNetwork();
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        textSearch = newText;
        checkingNetwork();
        return true;
      }
    });
  }*/


  private void showRecyclerList(String query) {
    if (mainViewModel != null) {
      DetailsMovieActivity activity = (DetailsMovieActivity) getActivity();
      assert activity != null;
      String myDataFromActivity = activity.getMyData();
      if (query != null) {
        if (!query.equals("")) {
          mainViewModel.setSeacrhMovies(query);
        } else {
          mainViewModel.setListFollowers(myDataFromActivity);
        }
      } else {
        mainViewModel.setListFollowers(myDataFromActivity);
      }
      FollowersItemsAdapter.notifyDataSetChanged();
      recyclerViewMovie.setHasFixedSize(true);
      recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext()));
      recyclerViewMovie.setAdapter(FollowersItemsAdapter);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Toast.makeText(getActivity(), "NAV", Toast.LENGTH_SHORT).show();
  }
}
