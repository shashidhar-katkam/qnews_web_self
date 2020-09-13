package com.qgroupmedia.qnews.utils;

import android.os.AsyncTask;

import com.qgroupmedia.qnews.News;

import java.util.List;

public class Network  extends AsyncTask<String, Void, List<News>> {


    protected List<News> doInBackground(String... url) {
        try {
                return QueryUtils.fetchNewsData(url[0], url[1], url[2]);

        } catch (Exception e) {
            return null;
            //return null;
        } finally {

        }
    }


}
