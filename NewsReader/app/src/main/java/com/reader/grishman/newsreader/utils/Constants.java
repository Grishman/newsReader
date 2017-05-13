package com.reader.grishman.newsreader.utils;


import com.reader.grishman.newsreader.BuildConfig;

/**
 * App-wide constants
 */
public class Constants  {
    public static final String BASE_URL = BuildConfig.API_HOST;

    public static final String ARTICLES = "articles";

    //Query params
    public static final String QUERY_SORT_BY = "sortBy";
    public static final String QUERY_SOURCE = "source";
    public static final String QUERY_API_KEY = "apiKey"; //can be provided as http header

}
