package com.example.twit_tok;

import static androidx.navigation.Navigation.findNavController;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.twit_tok.data.db.TwokDatabase;
import com.example.twit_tok.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jakewharton.processphoenix.ProcessPhoenix;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler handler = new Handler();
        TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid(); // used to create database
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());
                BottomNavigationView navView = findViewById(R.id.nav_view);
                // Passing each menu ID as a set of Ids because each
                // menu should be considered as top level destinations.
                AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.navigation_add_twok,
                        R.id.navigation_wall,
                        R.id.navigation_profile,
                        R.id.navigation_userwall)
                        .build();
                NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_activity_main);
                NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, appBarConfiguration);
                NavigationUI.setupWithNavController(binding.navView, navController);
                if (Objects.isNull(savedInstanceState)) {
                    navView.setSelectedItemId(R.id.navigation_wall);
                }
            }
        }, 1000);

    }

    public void restart() {
        ProcessPhoenix.triggerRebirth(getBaseContext());
    }
}