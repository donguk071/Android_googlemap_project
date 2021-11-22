package com.example.porjectforfinal;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class frag3 extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3,container,false);
        return view;
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       /* LocationManager manager = (LocationManager) getSystemService(.LOCATION_SERVICE);
        GPSListener gpsListener = new GPSListener();*/
        long minTime = 10000;
        float minDistance = 0;
    }
}
