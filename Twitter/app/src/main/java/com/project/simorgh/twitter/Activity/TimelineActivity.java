package com.project.simorgh.twitter.Activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.project.simorgh.twitter.Fragment.ComposeFragment;
import com.project.simorgh.twitter.R;
import com.project.simorgh.twitter.Utils.TweetComparator;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetViewFetchAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimelineActivity extends AppCompatActivity implements OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView listView;

    private Tweet tweet;

    final UserTimeline userTimeline = new UserTimeline.Builder()
            .screenName(Twitter.getInstance().core.getSessionManager().getActiveSession().getUserName())
            .build();
    final TweetTimelineListAdapter adapter =
            new TweetTimelineListAdapter(this, userTimeline);

    final TweetViewFetchAdapter oldAdapter =
            new TweetViewFetchAdapter<CompactTweetView>(
                    TimelineActivity.this);

    private ArrayList<Tweet> tweets = new ArrayList<>();

    private TwitterApiClient twitterApiClient;
    private StatusesService statusesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // SwipeRefreshLayoutを作成
        createSwipeRefreshLayout();

        // リストビュー作成
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(oldAdapter);

        twitterApiClient = TwitterCore.getInstance().getApiClient();
        statusesService = twitterApiClient.getStatusesService();

        statusesService.homeTimeline(20, null, null, false, false, false, false,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {

                        tweets.addAll(listResult.data);
                        oldAdapter.setTweets(tweets);
                    }

                    @Override
                    public void failure(TwitterException e) {
                    }
                }
        );
}

    /**
     * 引っ張って更新するSwipeRefreshLayoutを作成
     */
    public void createSwipeRefreshLayout(){

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        // 色指定
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green, R.color.yellow);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {

        long latestId = oldAdapter.getItem(0).id;

        statusesService.homeTimeline(20, latestId, null, false, false, false, false,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {

                        tweets.addAll(listResult.data);
                        Collections.sort(tweets, new TweetComparator());
                        oldAdapter.setTweets(tweets);

                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void failure(TwitterException e) {
                    }
                }
        );
    }

    public void pushPostButton(View view) {

        Intent intent = new Intent(this, ComposeActivity.class);
        startActivity(intent);

//        ComposeFragment fragment = new ComposeFragment();
//        fragment.show(getFragmentManager(), "test");
    }

    public void pushCameraButton(View view) {
        Toast.makeText(this, "push camera button", Toast.LENGTH_LONG).show();
    }

    public void pushGalleryButton(View view) {
        Toast.makeText(this, "push gallery button", Toast.LENGTH_LONG).show();
    }
}
