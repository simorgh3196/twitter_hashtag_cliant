package com.project.simorgh.twitter.Fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;

import com.project.simorgh.twitter.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;


public class ComposeFragment extends DialogFragment implements View.OnClickListener, TextWatcher {

    private ImageButton backButton;
    private Button tweetButton;
    private EditText tweetContentText;
    private TextView lastCountText;


    public ComposeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_compose, container, false);
        backButton = (ImageButton) view.findViewById(R.id.backButton);
        tweetButton = (Button) view.findViewById(R.id.tweetButton);
        tweetContentText = (EditText) view.findViewById(R.id.tweetContentText);
        lastCountText = (TextView) view.findViewById(R.id.lastCountText);

        backButton.setOnClickListener(this);
        tweetButton.setOnClickListener(this);
        tweetContentText.addTextChangedListener(this);

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_compose);

        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

    /**
    入力された文字列が、修正される直前に呼び出されるメソッド

     CharSequence s : 現在EditTextに入力されている文字列
     int start      : sの文字列で新たに追加される文字列のスタート位置
     int count      : sの文字列の中で変更された文字列の総数
     int after      : 新規に追加された文字列の数
     */
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
        Toast.makeText(getActivity(), "push tweet button", Toast.LENGTH_LONG).show();

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();

        String message = tweetContentText.getText().toString();

        statusesService.update(message, null, false, null, null, null, false, null, null, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                Toast.makeText(getActivity(), "ツイートしました。", Toast.LENGTH_SHORT).show();
                closeFragment();
            }

            public void failure(TwitterException exception) {
                Toast.makeText(getActivity(), "ツイートに失敗しました。", Toast.LENGTH_SHORT).show();
                closeFragment();
            }
        });
    }

    public void pushBackButton(View view) {
        closeFragment();
    }

    private void closeFragment() {
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }
}
