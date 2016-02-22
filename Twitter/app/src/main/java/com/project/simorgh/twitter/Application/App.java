package com.project.simorgh.twitter.Application;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;


public class App extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "WGvM1fj40dRCaLeWVxLktJitz";
    private static final String TWITTER_SECRET = "246jNuNDhWruphXeGyZYEHgvB43aRt5UrEevPvuzfo13fuTyYB";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.v("Override Application", "--- onCreate() in ---");
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        /** This Method Called when this Application finished. */
        Log.v("Override Application", "--- onTerminate() in ---");
    }
}
