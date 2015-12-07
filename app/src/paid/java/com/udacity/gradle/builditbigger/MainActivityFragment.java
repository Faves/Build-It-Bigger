package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements
        IMainActivityLoadable {

    private ProgressBar pb_loading;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        pb_loading = (ProgressBar)root.findViewById(R.id.pb_loading);

        return root;
    }


    @Override
    public void startLoading() {
        if (pb_loading != null)
            pb_loading.setVisibility(View.VISIBLE);
    }
    @Override
    public void stopLoading() {
        if (pb_loading != null)
            pb_loading.setVisibility(View.GONE);
    }
}
