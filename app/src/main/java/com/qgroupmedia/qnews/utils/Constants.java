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

package com.qgroupmedia.qnews.utils;

/**
 * Store Constants for the NewsFeed app.
 */

public class Constants {

    /**
     * Create a private constructor because no one should ever create a {@link Constants} object.
     */
    private Constants() {
    }

    /**
     * Extract the key associated with the JSONObject
     */
    static final String JSON_KEY_RESPONSE = "response";
    static final String JSON_KEY_RESULTS = "results";
    static final String JSON_KEY_WEB_TITLE = "webTitle";
    static final String JSON_KEY_SECTION_NAME = "sectionName";
    static final String JSON_KEY_WEB_PUBLICATION_DATE = "webPublicationDate";
    static final String JSON_KEY_WEB_URL = "webUrl";
    static final String JSON_KEY_TAGS = "tags";
    static final String JSON_KEY_FIELDS = "fields";
    static final String JSON_KEY_THUMBNAIL = "thumbnail";
    static final String JSON_KEY_TRAIL_TEXT = "trailText";

    /**
     * Read timeout for setting up the HTTP request
     */
    static final int READ_TIMEOUT = 10000; /* milliseconds */

    /**
     * Connect timeout for setting up the HTTP request
     */
    static final int CONNECT_TIMEOUT = 15000; /* milliseconds */

    /**
     * HTTP response code when the request is successful
     */
    static final int SUCCESS_RESPONSE_CODE = 200;

    /**
     * Request method type "GET" for reading information from the server
     */
    //static final String REQUEST_METHOD_GET = "GET";
    static final String REQUEST_METHOD_POST = "POST";
    static final String JSON_KEY_DATA = "data";
    static final String JSON_KEY_TITLE = "Title";
    static final String JSON_KEY_DESCRIPTION = "Description";
    static final String JSON_KEY_DATETIME = "DateTime";
    static final String JSON_KEY_USER = "User";
    static final String JSON_KEY_FIRST_NAME = "firstName";
    static final String JSON_KEY_LAST_NAME = "lastName";
    static final String JSON_KEY_IMAGE_PATH = "imagePath";
    static final String JSON_KEY_FILES = "Files";
    static final String JSON_KEY_FILE_NEW_NAME = "fileNewName";
    static final String JSON_KEY_MIME_TYPE = "mimeType";
    static final String JSON_KEY_ORIGINAL_NAME = "originalName";
    static final String JSON_KEY_FILE_PATH = "filePath";
    static final String JSON_KEY_FILE_TYPE = "fileType";
    static final String JSON_KEY_IS_TOP_NEWS = "IsTopTen";
    static final String JSON_KEY_IS_HEADLINES = "IsHeadlines";
    static final String JSON_KEY_EN_REF_ID = "ENRefId";
    static final String JSON_KEY_CATEGORY = "Category";
    static final String JSON_KEY_SHOW = "Show";
    static final String JSON_KEY_TYPE = "Type";
    static final String JSON_KEY_SOURCE = "Source";
    static final String JSON_KEY_ANALYSIS1 = "Analysis1";
    static final String JSON_KEY_ANALYSIS2 = "Analysis2";
    static final String JSON_KEY_ANALYSIS3 = "Analysis3";
    static final String JSON_KEY_VIEWS = "Views";
    static final String JSON_KEY_ID = "_id";


    /**
     * URL for news data from the guardian data set
     */
    //public static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search";
    public static final String NEWS_REQUEST_URL = "https://www.qgroupmedia.com/use/te/api/gnbf";
    public static final String SITE_URL = "https://www.qgroupmedia.com";
//    public static final String NEWS_REQUEST_URL = "http:/192.168.0.107:7777/use/te/api/gnbf";
//    public static final String SITE_URL = "http:/192.168.0.107:7777";



    /**
     * Parameters
     */
    public static final String QUERY_PARAM = "q";
    public static final String ORDER_BY_PARAM = "order-by";
    public static final String PAGE_SIZE_PARAM = "page-size";
    public static final String ORDER_DATE_PARAM = "order-date";
    public static final String FROM_DATE_PARAM = "from-date";
    public static final String SHOW_FIELDS_PARAM = "show-fields";
    public static final String FORMAT_PARAM = "format";
    public static final String SHOW_TAGS_PARAM = "show-tags";
    public static final String API_KEY_PARAM = "api-key";
    public static final String SECTION_PARAM = "section";

    /**
     * The show fields we want our API to return
     */
    public static final String SHOW_FIELDS = "thumbnail,trailText";

    /**
     * The format we want our API to return
     */
    public static final String FORMAT = "json";

    /**
     * The show tags we want our API to return
     */
    public static final String SHOW_TAGS = "contributor";

    /**
     * API Key
     */
    public static final String API_KEY = "test"; // Use your API Key when API rate limit exceeded

    /**
     * Default number to set the image on the top of the textView
     */
    public static final int DEFAULT_NUMBER = 0;

    /**
     * Constants value for each fragment
     */
    public static final int HOME = 0;
    public static final int NEWS = 1;
    public static final int TELANGANA = 2;
    public static final int INDIA = 3;
    public static final int POLLS = 4;
    public static final int CORRUPTION = 5;
    public static final int MUSIC = 6;
    public static final int USEFUL_INFO = 7;
    public static final int ARTICLE = 8;
    public static final int MOVIES = 9;
    public static final int SPORTS = 10;
    public static final int BUSINESS = 11;
    public static final int TRENDING = 12;
    public static final int MUST_WATCH = 13;
    public static final int TIME_PASS = 14;
    public static final int CRIME = 15;
    public static final int JOBS = 16;
    public static final int CORONA = 17;
    public static final int HEALTH = 18;
    public static final int WOMEN = 19;
    public static final int DEVOTIONAL = 20;
    public static final int POLITICS = 21;
    public static final int EDUCATION = 22;
    public static final int INTERNATIONAL = 23;
    public static final int WEATHER = 24;


}
