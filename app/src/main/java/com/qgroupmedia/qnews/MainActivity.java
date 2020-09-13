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

         webView = findViewById(R.id.webView1);

        webView.loadUrl("https://www.google.com/");
     //   webView.setWebChromeClient(WebChromeClient());
       // webView.clearCache(true);
        //webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
       webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);

       // webView = (WebView) findViewById(R.id.webView);
        WebSettings mWebSettings = webView.getSettings();
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setAllowContentAccess(true);
        webView.setWebViewClient(new WebViewClient());
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(false);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAllowContentAccess(true);
      //  webView.loadUrl("http://49.207.2.73/");
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide(); //hide the title bar

        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (mUMA != null) {
                    mUMA.onReceiveValue(null);
                }
                mUMA = filePathCallback;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(MainActivity.this.getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                        takePictureIntent.putExtra("PhotoPath", mCM);
                    } catch (IOException ex) {
                        Log.e("Webview", "Image file creation failed", ex);
                    }
                    if (photoFile != null) {
                        mCM = "file:" + photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    } else {
                        takePictureIntent = null;
                    }
                }

                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("*/*");
                Intent[] intentArray;
                if (takePictureIntent != null) {
                    intentArray = new Intent[]{takePictureIntent};
                } else {
                    intentArray = new Intent[0];
                }

                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooserIntent, FCR);
                return true;
            }
        });

    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        this.openFileChooser(uploadMsg, "*/*");
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        this.openFileChooser(uploadMsg, acceptType, null);
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        MainActivity.this.startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (Build.VERSION.SDK_INT >= 21) {
            Uri[] results = null;
            //Check if response is positive
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == FCR) {
                    if (null == mUMA) {
                        return;
                    }
                    if (intent == null) {
                        //Capture Photo if no image available
                        if (mCM != null) {
                            results = new Uri[]{Uri.parse(mCM)};
                        }
                    } else {
                        String dataString = intent.getDataString();
                        if (dataString != null) {
                            results = new Uri[]{Uri.parse(dataString)};
                        }
                    }
                }
            }
            mUMA.onReceiveValue(results);
            mUMA = null;
        } else {
            if (requestCode == FCR) {
                if (null == mUM) return;
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                mUM.onReceiveValue(result);
                mUM = null;
            }
        }
    }

    // Create an image file
    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "img_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);


    }

      //  Toolbar toolbar = findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
      //  DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       // drawer.addDrawerListener(toggle);
       // toggle.syncState();

        // Find the view pager that will allow the user to swipe between fragments
//        viewPager = findViewById(R.id.viewpager);
//        // Give the TabLayout the ViewPager
//        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        // Set gravity for tab bar
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//
//        assert navigationView != null;
//        navigationView.setNavigationItemSelectedListener(this);
//
//        // Set the default fragment when starting the app
//        onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));
//
//        // Set category fragment pager adapter
//        CategoryFragmentPagerAdapter pagerAdapter =
//                new CategoryFragmentPagerAdapter(this, getSupportFragmentManager());
//        // Set the pager adapter onto the view pager
//        viewPager.setAdapter(pagerAdapter);
//
////        navigationView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
////            @Override
////            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
////                if(view.)
////
////            }
////        });
//
//
//
//
//
//
//        //bottom navigation bar
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setSelectedItemId(R.id.home_1);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.live_1:
//                        startActivity(new Intent(getApplicationContext(), LiveActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.home_1:
//                        return true;
//                }
//                return false;
//            }
//        });




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