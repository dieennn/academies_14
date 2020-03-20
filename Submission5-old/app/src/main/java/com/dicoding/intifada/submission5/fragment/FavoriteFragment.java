package com.dicoding.intifada.submission5.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dicoding.intifada.submission5.R;
import com.dicoding.intifada.submission5.pager.PagerAdapterFavorite;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        PagerAdapterFavorite sectionsPagerAdapter = new PagerAdapterFavorite(getContext(), getChildFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.view_pager_favorite);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = view.findViewById(R.id.tab_favorite);
        tabs.setupWithViewPager(viewPager);

        return view;
    }

}
