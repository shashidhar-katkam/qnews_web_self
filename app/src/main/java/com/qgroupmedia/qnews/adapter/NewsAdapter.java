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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.qgroupmedia.qnews.News;
import com.qgroupmedia.qnews.R;
import com.qgroupmedia.qnews.models.File;
import com.qgroupmedia.qnews.models.MainFileType;
import com.qgroupmedia.qnews.utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A {@link NewsAdapter} can provide a card item layout for each news in the data source
 * ( a list of {@link News} objects).
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context mContext;
    private List<News> mNewsList;
    private SharedPreferences sharedPrefs;

    /**
     * Constructs a new {@link NewsAdapter}
     *
     * @param context  of the app
     * @param newsList is the list of news, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> newsList) {
        mContext = context;
        mNewsList = newsList;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userTextView;
        private TextView textTimeStamp;
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView dateTextView;
        private ImageView userImageView;
        private ImageView shareImageView;
        private ImageView newsImageView;
        private TextView trailTextView;
        private VideoView newsVideoView;
        private WebView newsYoutubeView;
        private CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            userTextView = itemView.findViewById(R.id.textUser);
            textTimeStamp = itemView.findViewById(R.id.textTimeStamp);
            titleTextView = itemView.findViewById(R.id.textTitle);
            descriptionTextView = itemView.findViewById(R.id.textDescription);
//            dateTextView = itemView.findViewById(R.id.date_card);
            userImageView = itemView.findViewById(R.id.imageView);
            newsImageView = itemView.findViewById(R.id.newsImage1);
            newsVideoView = itemView.findViewById(R.id.videoView);
            newsYoutubeView = itemView.findViewById(R.id.videoWebView);
//            trailTextView = itemView.findViewById(R.id.trail_text_card);
//            cardView = itemView.findViewById(R.id.card_view);
            itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);


        // Change the color theme of Title TextView by using the user's stored preferences
        // setColorTheme(holder);

        // Change text size of TextView by using the user's stored preferences
        // setTextSize(holder);

        // Find the current news that was clicked on
        final News currentNews = mNewsList.get(position);
        Glide.with(mContext.getApplicationContext())
                .load(Constants.SITE_URL + currentNews.getUser().getImagePath())
                .into(holder.userImageView);
        holder.userTextView.setText(currentNews.getUser().getFirstName() + " " + currentNews.getUser().getLastName());
        holder.textTimeStamp.setText(getTimeDifference(formatDate(currentNews.getDateTime())));
        holder.titleTextView.setText(currentNews.getTitle());
        holder.descriptionTextView.setText(currentNews.getDescription());


        if (currentNews.getFiles() != null && currentNews.getFiles().size() == 1) {
//            if (MainFileType.values()[currentNews.getFiles().get(0).getFileType()] == MainFileType.localImage) {
            if (currentNews.getFiles().get(0).getFileType() == 0) {
                Glide.with(mContext.getApplicationContext())
                        .load(Constants.SITE_URL + currentNews.getFiles().get(0).getFilePath())
                        .into(holder.newsImageView);

                holder.newsImageView.setVisibility(View.VISIBLE);
                holder.newsImageView.setSystemUiVisibility(View.VISIBLE);
            //} else if (MainFileType.values()[currentNews.getFiles().get(0).getFileType()] == MainFileType.localVideo) {
            }
            else if (currentNews.getFiles().get(0).getFileType() == 1) {
//                Glide.with(mContext.getApplicationContext())
//                        .load(Constants.SITE_URL + currentNews.getFiles().get(0).getFilePath())
//                        .into(holder.newsVideoView);
                holder.newsVideoView.setVisibility(View.VISIBLE);
                holder.newsVideoView.setSystemUiVisibility(View.VISIBLE);

                Uri baseUri = Uri.parse(Constants.SITE_URL + currentNews.getFiles().get(0).getFilePath());
                Log.e("dfs", baseUri.toString());
                holder.newsVideoView.setVideoURI(baseUri);
                //holder.newsVideoView.setVideoPath(Constants.SITE_URL + currentNews.getFiles().get(0).getFilePath());
                holder.newsVideoView.start();

                // holder.newsImageView.setVisibility(View.VISIBLE);
            }else if (currentNews.getFiles().get(0).getFileType() == 3) {

                holder.newsYoutubeView.getSettings().setAppCacheMaxSize( 10 * 1024 * 1024 );
                holder.newsYoutubeView.loadData( "<iframe  src=\"https://www.youtube.com/embed/eWEF1Zrmdow\" frameborder=\\\"0\\\" allowfullscreen></iframe>", "text/html" , "utf-8" );
                holder.newsYoutubeView.getSettings().setJavaScriptEnabled(true);
                holder.newsYoutubeView.setWebChromeClient(new WebChromeClient() {});

            }
        }

        //holder.sectionTextView.setText(currentNews.getDescription());
        // If the author does not exist, hide the authorTextView
//        if (currentNews.getAuthor() == null) {
//            holder.authorTextView.setVisibility(View.GONE);
//        } else {
//            holder.authorTextView.setVisibility(View.VISIBLE);
//            holder.authorTextView.setText(currentNews.getAuthor());
//        }

        // Get time difference between the current date and web publication date and
        // set the time difference on the textView
        //holder.dateTextView.setText(getTimeDifference(formatDate(currentNews.getDate())));
        //holder.dateTextView.setText("Date");

        // Get string of the trailTextHTML and convert Html text to plain text
        // and set the plain text on the textView
        // String trailTextHTML = currentNews.getTrailTextHtml();
//        holder.trailTextView.setText("he");

        // Set an OnClickListener to open a website with more information about the selected article
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Convert the String URL into a URI object (to pass into the Intent constructor)
//                Uri newsUri = Uri.parse(currentNews.getUrl());
//
//                // Create a new intent to view the news URI
//                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
//
//                // Send the intent to launch a new activity
//                mContext.startActivity(websiteIntent);
//            }
//        });

//        if (currentNews.getThumbnail() == null) {
//            holder.thumbnailImageView.setVisibility(View.GONE);
//        } else {
//            holder.thumbnailImageView.setVisibility(View.VISIBLE);
//            // Load thumbnail with glide
//            Glide.with(mContext.getApplicationContext())
//                    .load(currentNews.getThumbnail())
//                    .into(holder.thumbnailImageView);
//        }
        // Set an OnClickListener to share the data with friends via email or  social networking
//        holder.shareImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shareData(currentNews);
//            }
//        });
    }

    /**
     * Set the user preferred color theme
     */
    private void setColorTheme(ViewHolder holder) {
        // Get the color theme string from SharedPreferences and check for the value associated with the key
//        String colorTheme = sharedPrefs.getString(
//                mContext.getString(R.string.settings_color_key),
//                mContext.getString(R.string.settings_color_default));
//
//        // Change the background color of titleTextView by using the user's stored preferences
//        if (colorTheme.equals(mContext.getString(R.string.settings_color_white_value))) {
//            holder.titleTextView.setBackgroundResource(R.color.white);
//            holder.titleTextView.setTextColor(Color.BLACK);
//        }else if (colorTheme.equals(mContext.getString(R.string.settings_color_sky_blue_value))) {
//            holder.titleTextView.setBackgroundResource(R.color.nav_bar_start);
//            holder.titleTextView.setTextColor(Color.WHITE);
//        } else if (colorTheme.equals(mContext.getString(R.string.settings_color_dark_blue_value))) {
//            holder.titleTextView.setBackgroundResource(R.color.color_app_bar_text);
//            holder.titleTextView.setTextColor(Color.WHITE);
//        } else if (colorTheme.equals(mContext.getString(R.string.settings_color_violet_value))) {
//            holder.titleTextView.setBackgroundResource(R.color.violet);
//            holder.titleTextView.setTextColor(Color.WHITE);
//        } else if (colorTheme.equals(mContext.getString(R.string.settings_color_light_green_value))) {
//            holder.titleTextView.setBackgroundResource(R.color.light_green);
//            holder.titleTextView.setTextColor(Color.WHITE);
//        } else if (colorTheme.equals(mContext.getString(R.string.settings_color_green_value))) {
//            holder.titleTextView.setBackgroundResource(R.color.color_section);
//            holder.titleTextView.setTextColor(Color.WHITE);
//        }
    }

    /**
     * Set the text size to the text size the user choose.
     */
    private void setTextSize(ViewHolder holder) {
        // Get the text size string from SharedPreferences and check for the value associated with the key
//        String textSize = sharedPrefs.getString(
//                mContext.getString(R.string.settings_text_size_key),
//                mContext.getString(R.string.settings_text_size_default));
//
//        // Change text size of TextView by using the user's stored preferences
//        if(textSize.equals(mContext.getString(R.string.settings_text_size_medium_value))) {
//            holder.titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp22));
//            holder.sectionTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp14));
//            holder.trailTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp16));
//            holder.authorTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp14));
//            holder.dateTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp14));
//        } else if(textSize.equals(mContext.getString(R.string.settings_text_size_small_value))) {
//            holder.titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp20));
//            holder.sectionTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp12));
//            holder.trailTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp14));
//            holder.authorTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp12));
//            holder.dateTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp12));
//        } else if(textSize.equals(mContext.getString(R.string.settings_text_size_large_value))) {
//            holder.titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp24));
//            holder.sectionTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp16));
//            holder.trailTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp18));
//            holder.authorTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp16));
//            holder.dateTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                    mContext.getResources().getDimension(R.dimen.sp16));
//        }
    }

    /**
     * Share the article with friends in social network
     *
     * @param news {@link News} object
     */
    private void shareData(News news) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                news.getTitle() + " : ");
//        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
//                news.getTitle() + " : " + news.getUrl());
        mContext.startActivity(Intent.createChooser(sharingIntent,
                mContext.getString(R.string.share_article)));
    }

    /**
     * Clear all data (a list of {@link News} objects)
     */
    public void clearAll() {
        mNewsList.clear();
        notifyDataSetChanged();
    }

    /**
     * Add  a list of {@link News}
     *
     * @param newsList is the list of news, which is the data source of the adapter
     */
    public void addAll(List<News> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    public void addMore(List<News> newsList) {
        for (int i = 0; i < newsList.size(); i++) {
            mNewsList.add(newsList.get(i));
        }
        notifyDataSetChanged();
    }


    /**
     * Convert date and time in UTC (webPublicationDate) into a more readable representation
     * in Local time
     *
     * @param dateStringUTC is the web publication date of the article (i.e. 2014-02-04T08:00:00Z)
     * @return the formatted date string in Local time(i.e "Jan 1, 2000  2:15 AM")
     * from a date and time in UTC
     */
    private String formatDate(String dateStringUTC) {
        // Parse the dateString into a Date object
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.sss'Z'");
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(dateStringUTC);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Initialize a SimpleDateFormat instance and configure it to provide a more readable
        // representation according to the given format, but still in UTC
        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy  h:mm a", Locale.ENGLISH);
        String formattedDateUTC = df.format(dateObject);
        // Convert UTC into Local time
        df.setTimeZone(TimeZone.getTimeZone("UTC+5:30"));
        Date date = null;
        try {
            date = df.parse(formattedDateUTC);
            df.setTimeZone(TimeZone.getDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(date);
    }

    /**
     * Get the formatted web publication date string in milliseconds
     *
     * @param formattedDate the formatted web publication date string
     * @return the formatted web publication date in milliseconds
     */
    private static long getDateInMillis(String formattedDate) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MMM d, yyyy  h:mm a");
        long dateInMillis;
        Date dateObject;
        try {
            dateObject = simpleDateFormat.parse(formattedDate);
            dateInMillis = dateObject.getTime();
            return dateInMillis;
        } catch (ParseException e) {
            Log.e("Problem parsing date", e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get the time difference between the current date and web publication date
     *
     * @param formattedDate the formatted web publication date string
     * @return time difference (i.e "9 hours ago")
     */
    private CharSequence getTimeDifference(String formattedDate) {
        long currentTime = System.currentTimeMillis();
        long publicationTime = getDateInMillis(formattedDate);
        return DateUtils.getRelativeTimeSpanString(publicationTime, currentTime,
                DateUtils.SECOND_IN_MILLIS);
    }

}
