package com.dicoding.intifada.submission2.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dicoding.intifada.submission2.R;
import com.dicoding.intifada.submission2.fragment.MovieFragment;
import com.dicoding.intifada.submission2.fragment.TvShowFragment;

public class TabAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public TabAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_text_1,
            R.string.tab_text_2
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MovieFragment();
                break;

            case 1:
                fragment = new TvShowFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle (int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
