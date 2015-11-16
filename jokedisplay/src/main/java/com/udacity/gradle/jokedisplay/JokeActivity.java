package com.udacity.gradle.jokedisplay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    private static String JOKE_KEY = "Joke key";
    public static void startActivity(Context context, String joke) {
        Intent intent = createIntentToStartActivity(context, joke);
        context.startActivity(intent);
    }
    public static Intent createIntentToStartActivity(Context context, String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JOKE_KEY, joke);
        return intent;
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
