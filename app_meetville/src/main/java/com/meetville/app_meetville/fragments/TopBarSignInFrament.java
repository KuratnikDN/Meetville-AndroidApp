package com.meetville.app_meetville.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meetville.app_meetville.R;

/**
 * Created by user on 11.03.14.
 */
public class TopBarSignInFrament extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.topbar_signin_fragment, null);

        return v;
    }

}
