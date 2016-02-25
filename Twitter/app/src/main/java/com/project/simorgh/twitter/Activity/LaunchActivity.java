package com.project.simorgh.twitter.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.project.simorgh.twitter.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.identity.*;


public class LaunchActivity extends AppCompatActivity {

    private TwitterLoginButton loginButton;
    private TwitterSession mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.project.simorgh.twitter.R.layout.activity_launch);

        mSession = Twitter.getInstance().core.getSessionManager().getActiveSession();
        if (mSession.getAuthToken() != null) {
            showTimelineActivity();
            return;
        }

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                mSession = result.data;
                showTimelineActivity();
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
                Toast.makeText(LaunchActivity.this, "接続状況を確認してください。", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    private void showTimelineActivity() {

        String msg = "@" + mSession.getUserName() + " logged in!";
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(LaunchActivity.this, RootPagerActivity.class);
        startActivity(intent);
    }

}
