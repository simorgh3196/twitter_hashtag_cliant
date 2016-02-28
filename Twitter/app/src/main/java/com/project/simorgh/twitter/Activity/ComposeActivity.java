package com.project.simorgh.twitter.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.project.simorgh.twitter.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ComposeActivity extends Activity implements OnClickListener, TextWatcher {

    private ImageButton _backButton;
    private Button      _tweetButton;
    private EditText    _tweetContentText;
    private TextView    _lastCountText;
    private List<ImageButton> _imageButtons;
    private LinearLayout _imageLayout;

    private List<Bitmap> _images;
    private String _text = null;

    protected void onNewIntent(Intent intent, Bitmap bitmap, String text) {
        super.onNewIntent(intent);

        _images = new ArrayList<>();

        if (bitmap != null) {
            _images.add(bitmap);
        }

        if (text != null) {
            _text = text;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        _backButton = (ImageButton) findViewById(R.id.backButton);
        _tweetButton = (Button) findViewById(R.id.tweetButton);
        _tweetContentText = (EditText) findViewById(R.id.tweetContentText);
        _lastCountText = (TextView) findViewById(R.id.lastCountText);

        _imageButtons = new ArrayList<>();
        _imageButtons.add((ImageButton) findViewById(R.id.imageButton1));
        _imageButtons.add((ImageButton) findViewById(R.id.imageButton2));
        _imageButtons.add((ImageButton) findViewById(R.id.imageButton3));
        _imageButtons.add((ImageButton) findViewById(R.id.imageButton4));
        _imageLayout = (LinearLayout) findViewById(R.id.imageLayout);

        for (ImageButton button : _imageButtons) {
            button.setVisibility(View.GONE);
        }
        _imageLayout.setVisibility(View.GONE);

        _backButton.setOnClickListener(this);
        _tweetButton.setOnClickListener(this);
        _tweetContentText.addTextChangedListener(this);
    }

    public void setImage(URI uri, int position) {

        Glide.with(this).load(uri).asBitmap().into(new BitmapImageViewTarget(_imageButtons.get(position)) {
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                _images.add(resource);
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == _backButton) {
            pushBackButton(v);
        }
        else if (v == _tweetButton) {
            pushTweetButton(v);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String content = _tweetContentText.getText().toString();
        int content_count = 140 - content.length();

        _lastCountText.setText("" + content_count);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void pushTweetButton(View view) {
        Toast.makeText(this, "push tweet button", Toast.LENGTH_LONG).show();

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();

        String message = _tweetContentText.getText().toString();

        statusesService.update(message, null, false, null, null, null, false, null, null, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                Toast.makeText(ComposeActivity.this, "ツイートしました。", Toast.LENGTH_SHORT).show();
                finish();
            }

            public void failure(TwitterException exception) {
                Toast.makeText(ComposeActivity.this, "ツイートに失敗しました。", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void pushBackButton(View view) {
        finish();
    }
}
