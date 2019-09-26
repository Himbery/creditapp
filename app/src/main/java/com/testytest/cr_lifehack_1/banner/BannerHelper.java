package com.testytest.cr_lifehack_1.banner;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.testytest.cr_lifehack_1.R;
import com.testytest.cr_lifehack_1.WebActivity;
import com.testytest.cr_lifehack_1.base.BaseActivity;

import java.util.List;
import java.util.Random;

public class BannerHelper {
    private Context mContext;
    private IBanner mIBanner;
    private Dialog mBannerDialog;
    private SharedPreferences sPref;
    private InterstitialAd mInterstitialAd;
    private boolean isAdmobBanner;
    private boolean isOwnBannerInitialized;
    private boolean showAdsBanners;
    private boolean showOwnBanners;


    public BannerHelper(Context context) {
        mContext = context;
        sPref = mContext.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        isAdmobBanner = sPref.getBoolean("isAdmob", true);
        showAdsBanners = sPref.getBoolean("showAdsBanners", false);
        showOwnBanners = sPref.getBoolean("showOwnBanners", false);
        if (isAdmobBanner && showAdsBanners) admobBannerInit();
        else if (!isAdmobBanner && showOwnBanners) ownBannerInit();
        else if (!showAdsBanners && showOwnBanners) ownBannerInit();
        else if (showAdsBanners && !showOwnBanners) admobBannerInit();
    }

    private void admobBannerInit() {
            mInterstitialAd = new InterstitialAd(mContext);
            mInterstitialAd.setAdUnitId(mContext.getString(R.string.ads_interstitial_id));
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    mIBanner.onBannerClose();
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    if (showOwnBanners) ownBannerInit();
                }
            });
    }

    private void ownBannerInit() {
        BannerModel bannerModel = selectRandomBanner();
        if (bannerModel == null) {
            if (showAdsBanners) admobBannerInit();
            return;
        }
        mBannerDialog = new Dialog(mContext, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        mBannerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_banner, null, false);
        final View close = view.findViewById(R.id.close);
        final ImageView bannerImage = view.findViewById(R.id.bannerImage);
        Glide.with(mContext).load(bannerModel.getImageLink()).into(bannerImage);
        close.setOnClickListener(v -> {
            mIBanner.onBannerClose();
            mBannerDialog.dismiss();
        });
        bannerImage.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("url", bannerModel.getRedirectLink());
            mContext.startActivity(intent);
            mBannerDialog.dismiss();
        });
        mBannerDialog.setContentView(view);
        mBannerDialog.getWindow().setGravity(Gravity.BOTTOM);
        isOwnBannerInitialized = true;
    }

    private BannerModel selectRandomBanner() {
        Random random = new Random();
        List<BannerModel> bannerItemList = ((BaseActivity) mContext).readList("banners", BannerModel.class);
        if (bannerItemList == null || bannerItemList.size() == 0) return null;
        else return bannerItemList.get(random.nextInt(bannerItemList.size()));
    }

    public void showBanner() {
        long time = System.currentTimeMillis() - sPref.getLong("lastBannerTime", 0);
        long interval = sPref.getLong("banner_interval", 0);
        if (time > interval) {
            SharedPreferences.Editor ed = sPref.edit();
            if (mInterstitialAd != null && mInterstitialAd.isLoaded() && isAdmobBanner && showAdsBanners) {
                mInterstitialAd.show();
                ed.putBoolean("isAdmob", false);
                ed.putLong("lastBannerTime", System.currentTimeMillis());
            } else if (!isOwnBannerInitialized && mInterstitialAd != null && mInterstitialAd.isLoaded() && showAdsBanners) {
                mInterstitialAd.show();
                ed.putBoolean("isAdmob", true);
                ed.putLong("lastBannerTime", System.currentTimeMillis());
            } else if (isOwnBannerInitialized && !isAdmobBanner && showOwnBanners) {
                mBannerDialog.show();
                ed.putBoolean("isAdmob", true);
                ed.putLong("lastBannerTime", System.currentTimeMillis());
            } else if (isOwnBannerInitialized && showOwnBanners) {
                mBannerDialog.show();
                ed.putBoolean("isAdmob", false);
                ed.putLong("lastBannerTime", System.currentTimeMillis());
            } else mIBanner.onBannerClose();
            ed.apply();
        } else {
            mIBanner.onBannerClose();
        }
    }

    public void setIBanner(IBanner IBanner) {
        mIBanner = IBanner;
    }

    public interface IBanner {
        void onBannerClose();
    }
}
