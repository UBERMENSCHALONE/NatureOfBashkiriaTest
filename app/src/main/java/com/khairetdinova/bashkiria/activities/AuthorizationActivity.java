package com.khairetdinova.bashkiria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.khairetdinova.bashkiria.R;
import com.khairetdinova.bashkiria.fragments.LoginFragment;
import com.khairetdinova.bashkiria.interfaces.ChangeFragment;

public class AuthorizationActivity extends AppCompatActivity implements View.OnClickListener, ChangeFragment {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.stringAuthorization);
        setSupportActionBar(toolbar);
        setFragment(new LoginFragment());
    }

    @Override
    public boolean onSupportNavigateUp() {
        toolbar.setTitle(R.string.stringAuthorization);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setSupportActionBar(toolbar);
        setFragment(new LoginFragment());
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}