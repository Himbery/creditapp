package com.testytest.cr_lifehack_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.testytest.cr_lifehack_1.banner.AdsConfigModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class StartActivity extends AppCompatActivity {
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isNetworkConnected()) {
            getDataFromFireStorage();
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    private void getDataFromFireStorage() {
        boolean langEmpty = false;
        final SharedPreferences sPref = getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putLong("banner_interval", Long.parseLong(getResources().getString(R.string.banner_interval)));
        ed.commit();

        FirebaseStorage storage = FirebaseStorage.getInstance();

        final long ONE_MEGABYTE = 1024 * 1024;

        StorageReference banners;
        StorageReference adsConfig;

        banners = storage.getReferenceFromUrl(getString(R.string.banners));
        adsConfig = storage.getReferenceFromUrl(getString(R.string.ads_config));

        // get banners
        banners.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                String json = new String(bytes);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("banners", json);
                ed.commit();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("banners", "");
                ed.commit();
            }
        });

        adsConfig.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                String json = new String(bytes);
                Gson g = new Gson();
                AdsConfigModel adsConfigModel = g.fromJson(json, AdsConfigModel.class);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putBoolean("showAdsBanners", adsConfigModel.isShowAdsBanners());
                ed.putBoolean("showOwnBanners", adsConfigModel.isShowOwnBanners());
                ed.putBoolean("showSmallAdsBanners", adsConfigModel.isShowSmallAdsBanners());
                ed.commit();
            }
        }).addOnFailureListener(exception -> {
            SharedPreferences.Editor ed1 = sPref.edit();
            ed1.putBoolean("showAdsBanners", false);
            ed1.putBoolean("showOwnBanners", false);
            ed1.putBoolean("showSmallAdsBanners", false);
            ed1.commit();
        });

        StorageReference configReference = storage.getReferenceFromUrl(getString(R.string.config_url));
        configReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                String ConfigJson = new String(bytes);
                checkConditions(ConfigJson);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    private void checkConditions(String configJson) {

        boolean mustShowAds = false;

        class ConfigItem {
            private String URL;
            private String[] show_in_countries;
            private boolean enabled;
        }

        ArrayList<ConfigItem> list = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(configJson);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = (JSONObject) arr.get(i);

                ConfigItem item = new ConfigItem();
                item.enabled = Boolean.parseBoolean(obj.get("enabled").toString());
                if (!item.enabled)
                    continue;
                item.URL = obj.get("URL").toString();
                JSONArray countries = obj.getJSONArray("show_in_countries");
                item.show_in_countries = new String[countries.length()];
                for (int j = 0; j < countries.length(); j++) {
                    item.show_in_countries[j] = countries.getString(j);
                }
                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String language = detectSIMCountry();
        if (!language.isEmpty()) {
            for (ConfigItem item: list){
                if (Arrays.asList(item.show_in_countries).contains(language.toUpperCase())) {

                    url = item.URL;
                }
            }
        }

        startMainActivity();
    }
    public void startMainActivity() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
        finish();
    }
    public String detectSIMCountry() {
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSimCountryIso().toUpperCase();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
