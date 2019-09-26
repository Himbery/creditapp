package com.testytest.cr_lifehack_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.testytest.cr_lifehack_1.banner.BannerHelper;

public class Three_Activity extends FragmentActivity implements View.OnClickListener {
    private BannerHelper mBannerHelper;
    Button backToStartB;
    ViewPager pager;
    PagerAdapter pagerAdapter;
    int currentPage = 0;
    static int PAGE_COUNT = 5;
    AdView threeAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_activity);
        threeAdView = (AdView) findViewById(R.id.adView);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new Three_Activity.MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        backToStartB = (Button) findViewById(R.id.button_back);
        backToStartB.setOnClickListener(this);

        // откл прокрутку свайпом
        pager.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
        final SharedPreferences sPref = getSharedPreferences("DATA", Context.MODE_PRIVATE);
        if (sPref.getBoolean("showSmallAdsBanners", false)) {
            AdRequest adRequest = new AdRequest.Builder().build();
            threeAdView.loadAd(adRequest);
        }


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        mBannerHelper.setIBanner(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        mBannerHelper.showBanner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBannerHelper = new BannerHelper(this);
    }


    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return PageFragment_three.newInstance(position);
        }
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }
}
