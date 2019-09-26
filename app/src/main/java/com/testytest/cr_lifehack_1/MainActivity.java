package com.testytest.cr_lifehack_1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.testytest.cr_lifehack_1.banner.BannerHelper;
import com.testytest.cr_lifehack_1.base.BaseActivity;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private BannerHelper mBannerHelper;
    AdView mainAdView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newUnbinder = ButterKnife.bind(this);
        mainAdView = (AdView) findViewById(R.id.adView);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        url = getIntent().getStringExtra("url");

        MobileAds.initialize(this, getString(R.string.ads_app_id));

        AppEventsLogger logger = AppEventsLogger.newLogger(this);
        logger.logEvent("sentFriendRequest");

        Button adviceButton = findViewById(R.id.one_button);
        adviceButton.setOnClickListener(this);
        Button twoButton = findViewById(R.id.two_button);
        twoButton.setOnClickListener(this);
        Button threeButton = findViewById(R.id.three_button);
        threeButton.setOnClickListener(this);
        Button fourButton = findViewById(R.id.four_button);
        fourButton.setOnClickListener(this);
        Button fiveButton = findViewById(R.id.five_button);
        fiveButton.setOnClickListener(this);
        Button sixButton = findViewById(R.id.six_button);
        sixButton.setOnClickListener(this);

        final SharedPreferences sPref = getSharedPreferences("DATA", Context.MODE_PRIVATE);
        if (sPref.getBoolean("showSmallAdsBanners", false)) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mainAdView.loadAd(adRequest);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mBannerHelper = new BannerHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one_button:
                mBannerHelper.setIBanner(() -> {
                    Intent intent1 = new Intent(MainActivity.this, One_Activity.class);
                startActivity(intent1);
                });
                mBannerHelper.showBanner();
                break;
            case R.id.two_button:
                mBannerHelper.setIBanner(() -> {
                    Intent intent2 = new Intent(MainActivity.this, Two_Activity.class);
                startActivity(intent2);
                });
                mBannerHelper.showBanner();
                break;
            case R.id.three_button:
                mBannerHelper.setIBanner(() -> {
                    Intent intent3 = new Intent(MainActivity.this, Three_Activity.class);
                startActivity(intent3);
                });
                mBannerHelper.showBanner();
                break;
            case R.id.four_button:
                if (url != null) {
                    Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                intent4.putExtra("url", url);
                    startActivity(intent4);
                } else{
                    Intent intent4 =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=net.creditonline.app"));
                    startActivity(intent4);
                }
                break;
            case R.id.five_button:
                mBannerHelper.setIBanner(() -> {
                    Intent intent5 = new Intent(MainActivity.this, Five_Activity.class);
                startActivity(intent5);
                });
                mBannerHelper.showBanner();
                break;
            case R.id.six_button:
                mBannerHelper.setIBanner(() -> {
                    Intent intent6 = new Intent(MainActivity.this, Six_Activity.class);
                startActivity(intent6);
                });
                mBannerHelper.showBanner();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Вы хотите выйти?")
                .setPositiveButton("да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("нет", null).show();
    }


}
