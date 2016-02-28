package com.project.simorgh.twitter.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.project.simorgh.twitter.Adapter.TimelinePagerAdapter;
import com.project.simorgh.twitter.R;



public class RootPagerActivity extends AppCompatActivity {

    private Toolbar _toolbar;
    private TimelinePagerAdapter _pageAdapter;

    private DrawerLayout _drawerLayout;
    private ActionBarDrawerToggle _drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_pager);
        _toolbar = (Toolbar) findViewById(R.id.toolbar);
        _toolbar.setTitle("Title");

        // ナビゲーションアイコンの設定、クリック処理
        _toolbar.setNavigationIcon(R.drawable.ic_menu_menu30);
        _toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ナビゲーションアイコンクリック時の処理
            }
        });
        setSupportActionBar(_toolbar);

//        _drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        _drawerToggle = new ActionBarDrawerToggle(this, _drawerLayout, _toolbar, 0, 0) {
//
//            /** Called when a drawer has settled in a completely closed state. */
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//            }
//
//            /** Called when a drawer has settled in a completely open state. */
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//            }
//        };
//        _drawerLayout.setDrawerListener(_drawerToggle);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        FragmentManager manager = getSupportFragmentManager();
        _pageAdapter = new TimelinePagerAdapter(manager);
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                changeTitle(_pageAdapter.getItemTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        viewPager.setAdapter(_pageAdapter);

    }

    public void changeTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void pushPostButton(View view) {
        Intent intent = new Intent(this, ComposeActivity.class);
        startActivity(intent);
    }

    public void pushCameraButton(View view) {
        Toast.makeText(this, "push camera button", Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, 0);
    }

    public void pushGalleryButton(View view) {
        Toast.makeText(this, "push gallery button", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();

    }

}
