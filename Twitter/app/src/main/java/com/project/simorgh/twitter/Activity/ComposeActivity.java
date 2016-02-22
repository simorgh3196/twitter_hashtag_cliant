package com.project.simorgh.twitter.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.simorgh.twitter.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

public class ComposeActivity extends Activity implements OnClickListener, TextWatcher {

    private ImageButton backButton;
    private Button tweetButton;
    private EditText tweetContentText;
    private TextView lastCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        backButton = (ImageButton) findViewById(R.id.backButton);
        tweetButton = (Button) findViewById(R.id.tweetButton);
        tweetContentText = (EditText) findViewById(R.id.tweetContentText);
        lastCountText = (TextView) findViewById(R.id.lastCountText);

        backButton.setOnClickListener(this);
        tweetButton.setOnClickListener(this);
        tweetContentText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == backButton) {
            pushBackButton(v);
        }
        else if (v == tweetButton) {
            pushTweetButton(v);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String content = tweetContentText.getText().toString();
        int content_count = 140 - content.length();

        lastCountText.setText("" + content_count);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void pushTweetButton(View view) {
        Toast.makeText(this, "push tweet button", Toast.LENGTH_LONG).show();

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();

        String message = tweetContentText.getText().toString();

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
