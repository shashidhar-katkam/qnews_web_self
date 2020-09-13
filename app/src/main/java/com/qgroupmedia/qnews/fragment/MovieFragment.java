package com.qgroupmedia.qnews.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.loader.content.Loader;

import android.util.Log;

import com.qgroupmedia.qnews.News;
import com.qgroupmedia.qnews.NewsLoader;
import com.qgroupmedia.qnews.NewsPreferences;
import com.qgroupmedia.qnews.R;

import java.util.List;

/**
 * The BusinessFragment is a {@link BaseArticlesFragment} subclass that
 * reuses methods of the parent class by passing the specific type of article to be fetched.
 */
public class MovieFragment extends BaseArticlesFragment {

    private static final String LOG_TAG = MovieFragment.class.getName();
    private static final String newsCategory = "movie";

    public MovieFragment() {
        super(newsCategory);
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        String businessUrl = NewsPreferences.getPreferredUrl(getContext(), getString(R.string.ic_title_movies));
        Log.e(LOG_TAG, businessUrl);

        // Create a new loader for the given URL
        return new NewsLoader(getActivity(), businessUrl, newsCategory);
    }
}
