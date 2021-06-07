package com.khairetdinova.bashkiria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.khairetdinova.bashkiria.R;
import com.khairetdinova.bashkiria.fragments.EventsFragment;
import com.khairetdinova.bashkiria.fragments.ObjectsFragment;
import com.khairetdinova.bashkiria.fragments.ProfileFragment;
import com.khairetdinova.bashkiria.interfaces.ChangeFragment;

public class MainActivity extends AppCompatActivity implements ChangeFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(ObjectsFragment.newInstance());
    }

    @Override
    protected void onStart() {
        super.onStart();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_objects:
                    setFragment(ObjectsFragment.newInstance());
                    break;
                case R.id.tab_events:
                    setFragment(EventsFragment.newInstance());
                    break;
                case R.id.tab_profile:
                    setFragment(ProfileFragment.newInstance());
                    break;
            }
            return true;
        });
    }

    @Override
    public void setFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
