package com.project.simorgh.twitter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.project.simorgh.twitter.Adapter.TimelinePagerAdapter;
import com.project.simorgh.twitter.R;

public class RootPagerActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_pager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Title");
        setSupportActionBar(toolbar);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        FragmentManager manager = getSupportFragmentManager();
        TimelinePagerAdapter adapter = new TimelinePagerAdapter(manager);
        viewPager.setAdapter(adapter);
    }

    public void changeTitle(String title) {
        toolbar.setTitle(title);
    }

    public void pushPostButton(View view) {
        Intent intent = new Intent(this, ComposeActivity.class);
        startActivity(intent);
    }

    public void pushCameraButton(View view) {
        Toast.makeText(this, "push camera button", Toast.LENGTH_LONG).show();
    }

    public void pushGalleryButton(View view) {
        Toast.makeText(this, "push gallery button", Toast.LENGTH_LONG).show();
    }

}
