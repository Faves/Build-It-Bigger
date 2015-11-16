package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.gradle.jokedisplay.JokeActivity;


public class MainActivity extends AppCompatActivity {
    private EndpointsAsyncTask mEndpointsAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        mEndpointsAsyncTask = null;
    }

    @Override
    protected void onDestroy() {
        if (mEndpointsAsyncTask != null) {
            mEndpointsAsyncTask.cancel(true);
        }

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        //Step 1
        //Joker myJoker = new Joker();
        //Toast.makeText(this, myJoker.getJoke(), Toast.LENGTH_SHORT).show();

        //Step 2
        //Joker myJoker = new Joker();
        //JokeActivity.startActivity(this, myJoker.getJoke());

        //Step 3
        doEndpointsTask();
    }

    private void doEndpointsTask() {
        if (mEndpointsAsyncTask == null) {
            mEndpointsAsyncTask = new EndpointsAsyncTask();
            mEndpointsAsyncTask.execute(new EndpointsAsyncTask.IEndpointsCallback() {
                @Override
                public void onEndTask_Endpoints(String result) {
                    mEndpointsAsyncTask = null;

                    JokeActivity.startActivity(MainActivity.this, result);
                }
            });
        }
    }
}
