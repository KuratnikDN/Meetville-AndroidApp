package com.meetville.app_meetville.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meetville.app_meetville.R;

/**
 * Created by user on 07.03.14.
 */
public class TopBarFragment extends Fragment {

    final String LOG_TAG = "myLogs";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment1 onCreateView");

        View v = inflater.inflate(R.layout.topbar_fragment, null);

        return v;
    }

}
