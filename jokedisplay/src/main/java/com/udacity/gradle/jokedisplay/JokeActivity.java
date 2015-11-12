package com.udacity.gradle.jokedisplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    private static String JOKE_KEY = "Joke key";
    public static void startActivity(Fragment fragment, String joke) {
        Activity activity = fragment.getActivity();
        Intent intent = new Intent(activity, JokeActivity.class);
        intent.putExtra(JOKE_KEY, joke);
        fragment.startActivity(intent);
    }
    public static void startActivity(Activity activity, String joke) {
        Intent intent = new Intent(activity, JokeActivity.class);
        intent.putExtra(JOKE_KEY, joke);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView txt_joke = (TextView)findViewById(R.id.txt_joke);
        if (getIntent() != null && getIntent().hasExtra(JOKE_KEY) && !TextUtils.isEmpty(getIntent().getStringExtra(JOKE_KEY))) {
            txt_joke.setText(getIntent().getStringExtra(JOKE_KEY));
        }
    }
}
