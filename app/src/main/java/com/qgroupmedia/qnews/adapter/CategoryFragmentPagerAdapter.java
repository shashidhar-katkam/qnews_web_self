/*
 * MIT License
 *
 * Copyright (c) 2018 Soojeong Shin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.qgroupmedia.qnews.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import androidx.fragment.app.FragmentPagerAdapter;

import com.qgroupmedia.qnews.News;
import com.qgroupmedia.qnews.R;
import com.qgroupmedia.qnews.fragment.ArticleFragment;
import com.qgroupmedia.qnews.fragment.BusinessFragment;
import com.qgroupmedia.qnews.fragment.CoronaFragment;
import com.qgroupmedia.qnews.fragment.CorruptionFragment;
import com.qgroupmedia.qnews.fragment.CrimeFragment;
import com.qgroupmedia.qnews.fragment.DevotionalFragment;
import com.qgroupmedia.qnews.fragment.EducationFragment;
import com.qgroupmedia.qnews.fragment.HealthFragment;
import com.qgroupmedia.qnews.fragment.HomeFragment;
import com.qgroupmedia.qnews.fragment.IndiaFragment;
import com.qgroupmedia.qnews.fragment.InfoFragment;
import com.qgroupmedia.qnews.fragment.InternationalFragment;
import com.qgroupmedia.qnews.fragment.JobsFragment;
import com.qgroupmedia.qnews.fragment.MovieFragment;
import com.qgroupmedia.qnews.fragment.MusicFragment;
import com.qgroupmedia.qnews.fragment.MustwatchFragment;
import com.qgroupmedia.qnews.fragment.NewsFragment;
import com.qgroupmedia.qnews.fragment.PoliticsFragment;
import com.qgroupmedia.qnews.fragment.PollsFragment;
import com.qgroupmedia.qnews.fragment.SportsFragment;
import com.qgroupmedia.qnews.fragment.TelanganaFragment;
import com.qgroupmedia.qnews.fragment.TimepassFragment;
import com.qgroupmedia.qnews.fragment.TrendingFragment;
import com.qgroupmedia.qnews.fragment.WeatherFragment;
import com.qgroupmedia.qnews.fragment.WomenFragment;
import com.qgroupmedia.qnews.utils.Constants;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link CategoryFragmentPagerAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     * across swipes.
     */
    public CategoryFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Constants.HOME:
                return new HomeFragment();
            case Constants.NEWS:
                return new NewsFragment();
            case Constants.TELANGANA:
                return new TelanganaFragment();
            case Constants.INDIA:
                return new IndiaFragment();
            case Constants.POLLS:
                return new PollsFragment();
            case Constants.CORRUPTION:
                return new CorruptionFragment();
            case Constants.MUSIC:
                return new MusicFragment();
            case Constants.USEFUL_INFO:
                return new InfoFragment();
            case Constants.ARTICLE:
                return new ArticleFragment();
            case Constants.MOVIES:
                return new MovieFragment();
            case Constants.SPORTS:
                return new SportsFragment();
            case Constants.BUSINESS:
                return new BusinessFragment();
            case Constants.TRENDING:
                return new TrendingFragment();
            case Constants.MUST_WATCH:
                return new MustwatchFragment();
            case Constants.TIME_PASS:
                return new TimepassFragment();
            case Constants.CRIME:
                return new CrimeFragment();
            case Constants.JOBS:
                return new JobsFragment();
            case Constants.CORONA:
                return new CoronaFragment();
            case Constants.HEALTH:
                return new HealthFragment();
            case Constants.WOMEN:
                return new WomenFragment();
            case Constants.DEVOTIONAL:
                return new DevotionalFragment();
            case Constants.POLITICS:
                return new PoliticsFragment();
            case Constants.EDUCATION:
                return new EducationFragment();
            case Constants.INTERNATIONAL:
                return new InternationalFragment();
            case Constants.WEATHER:
                return new WeatherFragment();
            default:
                return null;
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 25;
    }

    /**
     * Return page title of the tap
     */
    @Override
    public CharSequence getPageTitle(int position) {
        int titleResId;
        switch (position) {
            case Constants.HOME:
                titleResId = R.string.ic_title_home;
                break;
            case Constants.NEWS:
                titleResId = R.string.ic_title_news;
                break;
            case Constants.TELANGANA:
                titleResId = R.string.ic_title_telangana;
                break;
            case Constants.INDIA:
                titleResId = R.string.ic_title_india;
                break;
            case Constants.POLLS:
                titleResId = R.string.ic_title_polls;
                break;
            case Constants.CORRUPTION:
                titleResId = R.string.ic_title_corruption;
                break;
            case Constants.MUSIC:
                titleResId = R.string.ic_title_music;
                break;
            case Constants.USEFUL_INFO:
                titleResId = R.string.ic_title_useful_info;
                break;
            case Constants.ARTICLE:
                titleResId = R.string.ic_title_article;
                break;
            case Constants.MOVIES:
                titleResId = R.string.ic_title_movies;
                break;
            case Constants.SPORTS:
                titleResId = R.string.ic_title_sports;
                break;
            case Constants.BUSINESS:
                titleResId = R.string.ic_title_business;
                break;
            case Constants.TRENDING:
                titleResId = R.string.ic_title_trending;
                break;
            case Constants.MUST_WATCH:
                titleResId = R.string.ic_title_must_watch;
                break;
            case Constants.TIME_PASS:
                titleResId = R.string.ic_title_time_pass;
                break;
            case Constants.CRIME:
                titleResId = R.string.ic_title_crime;
                break;
            case Constants.JOBS:
                titleResId = R.string.ic_title_jobs;
                break;
            case Constants.CORONA:
                titleResId = R.string.ic_title_corona;
                break;
            case Constants.HEALTH:
                titleResId = R.string.ic_title_health;
                break;
            case Constants.WOMEN:
                titleResId = R.string.ic_title_women;
                break;
            case Constants.DEVOTIONAL:
                titleResId = R.string.ic_title_devotional;
                break;
            case Constants.POLITICS:
                titleResId = R.string.ic_title_politics;
                break;
            case Constants.EDUCATION:
                titleResId = R.string.ic_title_education;
                break;
            case Constants.INTERNATIONAL:
                titleResId = R.string.ic_title_international;
                break;
            default:
                titleResId = R.string.ic_title_weather;
                break;
        }
        return mContext.getString(titleResId);
    }
}