package com.udacity.gradle.builditbigger;

/**
 * Created by Fabien on 13/11/2015.
 */

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.jockebackend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<EndpointsAsyncTask.IEndpointsCallback, Void, String> {
    private static MyApi myApiService = null;
    private IEndpointsCallback iEndpointsCallback = null;

    @Override
    protected String doInBackground(IEndpointsCallback... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        iEndpointsCallback = params[0];

        try {
            return myApiService.tellMeAJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onCancelled() {
        if (iEndpointsCallback != null) {
            iEndpointsCallback.onEndTask_Endpoints(null);
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (iEndpointsCallback != null) {
            iEndpointsCallback.onEndTask_Endpoints(result);
        }
    }


    public static interface IEndpointsCallback {
        void onEndTask_Endpoints(String result);
    }
}