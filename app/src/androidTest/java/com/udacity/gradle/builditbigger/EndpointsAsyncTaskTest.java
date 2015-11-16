package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import com.udacity.gradle.jokes.Joker;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Fabien on 16/11/2015.
 */
public class EndpointsAsyncTaskTest extends AndroidTestCase {
    private static final String TAG = "EndpointsAsyncTaskTest";

    /**
     * ASSERT TEST - Verify that the async task is indeed loading jokes.
     *
     * Thanks to this thread http://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
     */
    public void testVerifyEndpointResponse() {
        final CountDownLatch signal = new CountDownLatch(1);//help to wait for AsyncTask result
        final Long startTime = new Date().getTime();

        new EndpointsAsyncTask().execute(new EndpointsAsyncTask.IEndpointsCallback() {
            @Override
            public void onEndTask_Endpoints(String result) {
                //init expected result
                Joker joker = new Joker();
                String expectedResult = joker.getJoke();

                //test
                assertEquals(result, expectedResult);
                Log.i(TAG, result);

                // notify the count down latch
                signal.countDown();
            }
        });

        try {
            // wait for callback
            if (!signal.await(30, TimeUnit.SECONDS)) {
                Long nbSecs = (new Date().getTime() - startTime) / 1000;
                throw new AssertionError("Service not responding in "+nbSecs+"sec");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
