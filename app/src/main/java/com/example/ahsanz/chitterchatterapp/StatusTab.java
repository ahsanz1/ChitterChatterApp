package com.example.ahsanz.chitterchatterapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ahsanz on 6/4/18.
 */

public class StatusTab extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.status_tab_layout, container, false);
        return rootView;
    }

    public static android.support.v4.app.Fragment newInstance(){
        return new StatusTab();
    }
}
