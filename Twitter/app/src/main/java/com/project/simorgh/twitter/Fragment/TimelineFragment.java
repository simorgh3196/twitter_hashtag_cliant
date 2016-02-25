package com.project.simorgh.twitter.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.project.simorgh.twitter.Activity.RootPagerActivity;
import com.project.simorgh.twitter.Adapter.TweetAdapter;
import com.project.simorgh.twitter.R;
import com.project.simorgh.twitter.Utils.TweetComparator;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TimelineFragment extends Fragment implements OnRefreshListener {

    private SwipeRefreshLayout _swipeRefreshLayout;
    private ListView _listView;
    private TweetAdapter _tweetAdapter = null;

    private String hashtag = null;


    public static TimelineFragment newInstance(String hashtag) {

        TimelineFragment fragment = new TimelineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("hashtag", hashtag);
        fragment.setArguments(bundle);

        return fragment;
    }

    public String getHashtag() {
        return hashtag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
//            hashtag = bundle.getString("hashtag");
        }

        View fragment = inflater.inflate(R.layout.fragment_timeline, container, false);

        _swipeRefreshLayout = (SwipeRefreshLayout) fragment.findViewById(R.id.swipeRefreshLayout);
        _swipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green, R.color.yellow);
        _swipeRefreshLayout.setOnRefreshListener(this);

        _listView = (ListView) fragment.findViewById(R.id.list);
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Item tapped", Toast.LENGTH_LONG).show();
            }
        });
        _listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), "Item long tapped", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        onRefresh();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        RootPagerActivity activity = (RootPagerActivity) context;
        if (activity != null) {
            if (hashtag == null) {
                activity.changeTitle("Home");
            }
            activity.changeTitle("#" + hashtag);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onRefresh() {

        Long latestId = null;

        if (_tweetAdapter != null) {
            if (_tweetAdapter.getCount() > 0) {
                latestId = _tweetAdapter.getLatestTweetId();
            }
        }

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();

        statusesService.homeTimeline(20, latestId, null, false, false, false, false,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {

                        if (_tweetAdapter == null) {
                            _tweetAdapter = new TweetAdapter(getActivity());
                            _tweetAdapter.setTweets(listResult.data);
                            _listView.setAdapter(_tweetAdapter);
                        }
                        else {
                            _tweetAdapter.addTweets(listResult.data);
                            _tweetAdapter.notifyDataSetChanged();

                            int position = _listView.getFirstVisiblePosition() + listResult.data.size();
                            int yOffset = _listView.getChildAt(0).getTop();
                            _listView.setSelectionFromTop(position, yOffset);
                        }

                        if (_swipeRefreshLayout.isRefreshing()) {
                            _swipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Toast.makeText(getActivity(), "ツイート取得失敗", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
