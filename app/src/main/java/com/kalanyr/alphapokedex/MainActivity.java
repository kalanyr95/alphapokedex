package com.kalanyr.alphapokedex;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.kalanyr.alphapokedex.Common.Common;
import com.kalanyr.alphapokedex.Model.Pokemon;


public class MainActivity extends FragmentActivity {

    Toolbar toolbar;

    BroadcastReceiver showDetail = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals(Common.KEY_ENABLE_HOME)){
                //getActionBar().setDisplayHomeAsUpEnabled(true);
                //getActionBar().setDisplayShowHomeEnabled(true);

                //set name
                //Pokemon pokemon = Common.commonPokemonList.get(position);
                //toolbar.setTitle(pokemon.getName());

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goTo(new PokemonList());


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("POKEMON LIST");
        //setActionBar(toolbar);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetail, new IntentFilter(Common.KEY_ENABLE_HOME));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                toolbar.setTitle("POKEMON LIST");
                getSupportFragmentManager().popBackStack("détail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
            default:
                break;
        }
        return true;
    }

    public void goTo(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void goToWithBackStack(Fragment fragment, String backstack){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(backstack);
        fragmentTransaction.commit();
    }
}