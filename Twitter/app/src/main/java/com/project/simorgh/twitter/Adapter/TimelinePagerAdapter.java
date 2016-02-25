package com.project.simorgh.twitter.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.project.simorgh.twitter.Fragment.TimelineFragment;

import java.util.ArrayList;


public class TimelinePagerAdapter extends FragmentPagerAdapter {

    ArrayList<TimelineFragment> _fragments;

    public TimelinePagerAdapter(FragmentManager manager) {

        super(manager);
        _fragments = new ArrayList<>();
        _fragments.add(TimelineFragment.newInstance(null));
        _fragments.add(TimelineFragment.newInstance(null));
    }

    @Override
    public Fragment getItem(int position) {
        return _fragments.get(position);
    }

    @Override
    public int getCount() {
        return _fragments.size();
    }

    public void addItem(String hashtag) {

        TimelineFragment fragment = TimelineFragment.newInstance(hashtag);
        _fragments.add(fragment);
    }

    public String getTitle(int position) {

        String hashtag = _fragments.get(position).getHashtag();
        if (hashtag == null) {
            return "Home";
        }

        return "#" + hashtag;
    }

}
