package com.project.simorgh.twitter.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.*;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.simorgh.twitter.Activity.ComposeActivity;
import com.project.simorgh.twitter.R;
import com.project.simorgh.twitter.Utils.TwitterUtils;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.internal.TweetMediaUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import io.realm.processor.Utils;


public class TweetAdapter extends BaseAdapter {

    Context _context;
    LayoutInflater _layoutInflater = null;
    List<Tweet> _tweets;

    public TweetAdapter(Context context) {
        super();

        _context = context;
        _tweets = new ArrayList();
        _layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setTweets(List<Tweet> tweets) {
        this._tweets = tweets;
    }

    public void addTweets(List<Tweet> tweets) {

        List<Tweet> newTweets = new ArrayList<>();
        newTweets.addAll(tweets);
        newTweets.addAll(_tweets);
        _tweets = newTweets;
    }

    public long getLatestTweetId() {
        return _tweets.get(0).id;
    }

    @Override
    public int getCount() {
        return _tweets.size();
    }

    @Override
    public Tweet getItem(int position) {
        return _tweets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return _tweets.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (_layoutInflater == null) {
            _layoutInflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        convertView = _layoutInflater.inflate(R.layout.row_tweet,parent,false);
        Tweet tweet = getItem(position);
        Tweet retweetedTweet = tweet.retweetedStatus;
        TextView retweetedFromText = (TextView)convertView.findViewById(R.id.retweeted_from_text);

        if (retweetedTweet != null) {
            retweetedFromText.setVisibility(View.VISIBLE);
            retweetedFromText.setText(tweet.user.name + "さんからのリツイート");
            setView(retweetedTweet, convertView);
        }
        else {
            retweetedFromText.setVisibility(View.GONE);
            setView(tweet, convertView);
        }

        return convertView;
    }

    public void setView(Tweet tweet, View convertView) {

//        ((ImageButton)convertView.findViewById(R.id.icon_image_button)).setImageBitmap(tweetList.get(position));
        ((TextView)convertView.findViewById(R.id.name_text)).setText(tweet.user.name);
        ((TextView)convertView.findViewById(R.id.screen_name_text)).setText("@" + tweet.user.screenName);
        ((TextView)convertView.findViewById(R.id.time_text)).setText(TwitterUtils.parseTwitterDiffDate(tweet.createdAt));
        ((TextView)convertView.findViewById(R.id.content_text)).setText(tweet.text);

        ImageButton icon = ((ImageButton)convertView.findViewById(R.id.icon_image_button));
        Glide.with(_context).load(tweet.user.profileImageUrl).fitCenter().thumbnail(0.8f).into(icon);

        ImageButton image1 = (ImageButton)convertView.findViewById(R.id.imageButton1);
        ImageButton image2 = (ImageButton)convertView.findViewById(R.id.imageButton2);
        ImageButton image3 = (ImageButton)convertView.findViewById(R.id.imageButton3);
        ImageButton image4 = (ImageButton)convertView.findViewById(R.id.imageButton4);
        LinearLayout layout1 = (LinearLayout)convertView.findViewById(R.id.LinearLayout1);
        LinearLayout layout2 = (LinearLayout)convertView.findViewById(R.id.LinearLayout2);
        LinearLayout layout3 = (LinearLayout)convertView.findViewById(R.id.LinearLayout3);

        if (TweetMediaUtils.hasPhoto(tweet)) {

            List<MediaEntity> entities = tweet.extendedEtities.media;

            OnClickListener listener = new OnClickListener() {
                @Override
                public void onClick(View v) {

                    ImageButton imageButton = (ImageButton) v;
                    if (imageButton == null) {
                        return;
                    }

                    Bitmap bitmap = ((BitmapDrawable)imageButton.getDrawable()).getBitmap();

                    //Bitmap image
                    ImageView iv = new ImageView(_context);
                    iv.setImageBitmap(bitmap);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    iv.setAdjustViewBounds(true);

                    Dialog dialog = new Dialog(_context);
                    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(iv);
                    dialog.show();
                }
            };

            image1.setOnClickListener(listener);
            image2.setOnClickListener(listener);
            image3.setOnClickListener(listener);
            image4.setOnClickListener(listener);

            image1.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);

            int count = entities.size();
            switch (count) {
                case 1:
                    image2.setVisibility(View.GONE);
                    image3.setVisibility(View.GONE);
                    image4.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);
                    Glide.with(_context).load(entities.get(0).mediaUrl).asBitmap().centerCrop().into(image1);
                    break;

                case 2:
                    image2.setVisibility(View.GONE);
                    image3.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.GONE);
                    layout3.setVisibility(View.VISIBLE);
                    Glide.with(_context).load(entities.get(0).mediaUrl).asBitmap().centerCrop().into(image1);
                    Glide.with(_context).load(entities.get(1).mediaUrl).asBitmap().centerCrop().into(image3);
                    break;

                case 3:
                    image2.setVisibility(View.GONE);
                    image3.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.VISIBLE);
                    layout3.setVisibility(View.VISIBLE);
                    Glide.with(_context).load(entities.get(0).mediaUrl).asBitmap().centerCrop().into(image1);
                    Glide.with(_context).load(entities.get(1).mediaUrl).asBitmap().centerCrop().into(image3);
                    Glide.with(_context).load(entities.get(2).mediaUrl).asBitmap().centerCrop().into(image4);
                    break;

                case 4:
                    image2.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.VISIBLE);
                    layout3.setVisibility(View.VISIBLE);
                    Glide.with(_context).load(entities.get(0).mediaUrl).asBitmap().centerCrop().into(image1);
                    Glide.with(_context).load(entities.get(2).mediaUrl).asBitmap().centerCrop().into(image2);
                    Glide.with(_context).load(entities.get(1).mediaUrl).asBitmap().centerCrop().into(image3);
                    Glide.with(_context).load(entities.get(3).mediaUrl).asBitmap().centerCrop().into(image4);
                    break;

                default: break;
            }
        }
        else {
            image1.setVisibility(View.GONE);
            image2.setVisibility(View.GONE);
            image3.setVisibility(View.GONE);
            image4.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
        }
    }


}
