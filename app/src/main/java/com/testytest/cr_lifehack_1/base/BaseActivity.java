package com.testytest.cr_lifehack_1.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spanned;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = "myLog";


    protected Unbinder newUnbinder;

    @SuppressLint("ResourceType")
    public void showToast(@IdRes int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String error) {
        Log.w(TAG, "showToast: " + error);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void showSToast(Spanned error) {
        Log.w(TAG, "showToast: " + error);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (newUnbinder != null)
            newUnbinder.unbind();
    }
    public <T> List<T> readList(String key, Class<T> type) {
        String jsonStr = getSharedPreferences("DATA", Context.MODE_PRIVATE).getString(key, "");
        Gson gson = new Gson();
        List<T> json = gson.fromJson(jsonStr, new ListOfSomething<T>(type));
        if (json != null)
            return json;
        else
            return new ArrayList<>();
    }

    public class ListOfSomething<X> implements ParameterizedType {

        private Class<?> wrapped;

        ListOfSomething(Class<X> wrapped) {
            this.wrapped = wrapped;
        }

        @NonNull
        public Type[] getActualTypeArguments() {
            return new Type[]{wrapped};
        }

        @NonNull
        public Type getRawType() {
            return List.class;
        }

        public Type getOwnerType() {
            return null;
        }

    }

}
