package com.asmat.rolando.popularmovies.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.asmat.rolando.popularmovies.R;
import com.asmat.rolando.popularmovies.fragments.FavoriteMoviesGridFragment;
import com.asmat.rolando.popularmovies.fragments.MovieGridFragment;
import com.asmat.rolando.popularmovies.fragments.WatchLaterMoviesGridFragment;
import com.asmat.rolando.popularmovies.models.RequestType;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_OF_PAGES = 4;
    private Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return NUM_OF_PAGES;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MovieGridFragment popularMoviesFragment = new MovieGridFragment();
                popularMoviesFragment.setTypeOfMovies(RequestType.MOST_POPULAR);
                return popularMoviesFragment;
            case 1:
                MovieGridFragment topRatedMoviesFragment = new MovieGridFragment();
                topRatedMoviesFragment.setTypeOfMovies(RequestType.TOP_RATED);
                return topRatedMoviesFragment;
            case 2:
                FavoriteMoviesGridFragment favoritesFragment = new FavoriteMoviesGridFragment();
                return favoritesFragment;
            case 3:
                WatchLaterMoviesGridFragment watchLaterFragment = new WatchLaterMoviesGridFragment();
                return watchLaterFragment;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position) {
            case 0:
                title = context.getString(R.string.most_popular);
                break;
            case 1:
                title = context.getString(R.string.top_rated);
                break;
            case 2:
                title =  context.getString(R.string.favorites);
                break;
            case 3:
                title =  context.getString(R.string.watch_later);
                break;
            default:
                title = "";
        }
        return title;
    }

}
