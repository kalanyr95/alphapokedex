package com.kalanyr.alphapokedex;

import android.app.Activity;
import android.os.Bundle;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;


public class MainActivity extends FragmentActivity {

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar = (Toolbar)findViewById(R.id.toolbar);
        //toolbar.setTitle("POKEMON LIST");
        //setActionBar(toolbar);
    }


}