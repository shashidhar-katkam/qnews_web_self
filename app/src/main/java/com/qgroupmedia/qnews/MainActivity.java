package com.qgroupmedia.qnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.qgroupmedia.qnews.adapter.CategoryFragmentPagerAdapter;
import com.qgroupmedia.qnews.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private WebView webView;
    private String mCM;
    private ValueCallback<Uri> mUM;
    private ValueCallback<Uri[]> mUMA;
    private final static int FCR = 1;
    private final static int FILECHOOSER_RESULTCODE = 1;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  Toolbar toolbar = findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        //  DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // drawer.addDrawerListener(toggle);
        // toggle.syncState();

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = findViewById(R.id.viewpager);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        // Set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        NavigationView navigationView = findViewById(R.id.nav_view);

        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        // Set the default fragment when starting the app
        onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));

        // Set category fragment pager adapter
        CategoryFragmentPagerAdapter pagerAdapter =
                new CategoryFragmentPagerAdapter(this, getSupportFragmentManager());
        // Set the pager adapter onto the view pager
        viewPager.setAdapter(pagerAdapter);

//        navigationView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//                if(view.)
//
//            }
//        });


        //bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home_1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.live_1:
                        startActivity(new Intent(getApplicationContext(), LiveActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home_1:
                        return true;
                }
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Switch Fragments in a ViewPager on clicking items in Navigation Drawer
        if (id == R.id.nav_home) {
            viewPager.setCurrentItem(Constants.HOME);
        } else if (id == R.id.nav_news) {
            viewPager.setCurrentItem(Constants.NEWS);
        } else if (id == R.id.nav_telangana) {
            viewPager.setCurrentItem(Constants.TELANGANA);
        } else if (id == R.id.nav_india) {
            viewPager.setCurrentItem(Constants.INDIA);
        } else if (id == R.id.nav_polls) {
            viewPager.setCurrentItem(Constants.POLLS);
        } else if (id == R.id.nav_corruption) {
            viewPager.setCurrentItem(Constants.CORRUPTION);
        } else if (id == R.id.nav_music) {
            viewPager.setCurrentItem(Constants.MUSIC);
        } else if (id == R.id.nav_usefull_info) {
            viewPager.setCurrentItem(Constants.USEFUL_INFO);
        } else if (id == R.id.nav_article) {
            viewPager.setCurrentItem(Constants.ARTICLE);
        }else if (id == R.id.nav_movies) {
            viewPager.setCurrentItem(Constants.MOVIES);
        } else if (id == R.id.nav_sports) {
            viewPager.setCurrentItem(Constants.SPORTS);
        } else if (id == R.id.nav_business) {
            viewPager.setCurrentItem(Constants.BUSINESS);
        } else if (id == R.id.nav_trending) {
            viewPager.setCurrentItem(Constants.TRENDING);
        } else if (id == R.id.nav_mustwatch) {
            viewPager.setCurrentItem(Constants.MUST_WATCH);
        } else if (id == R.id.nav_timepass) {
            viewPager.setCurrentItem(Constants.TIME_PASS);
        } else if (id == R.id.nav_crime) {
            viewPager.setCurrentItem(Constants.CRIME);
        } else if (id == R.id.nav_jobs) {
            viewPager.setCurrentItem(Constants.JOBS);
        }else if (id == R.id.nav_corona) {
            viewPager.setCurrentItem(Constants.CORONA);
        } else if (id == R.id.nav_health) {
            viewPager.setCurrentItem(Constants.HEALTH);
        } else if (id == R.id.nav_women) {
            viewPager.setCurrentItem(Constants.WOMEN);
        } else if (id == R.id.nav_devotional) {
            viewPager.setCurrentItem(Constants.DEVOTIONAL);
        } else if (id == R.id.nav_politics) {
            viewPager.setCurrentItem(Constants.POLITICS);
        } else if (id == R.id.nav_education) {
            viewPager.setCurrentItem(Constants.EDUCATION);
        } else if (id == R.id.nav_international) {
            viewPager.setCurrentItem(Constants.INTERNATIONAL);
        } else if (id == R.id.nav_weather) {
            viewPager.setCurrentItem(Constants.WEATHER);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    // Initialize the contents of the Activity's options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    // This method is called whenever an item in the options menu is selected.
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}