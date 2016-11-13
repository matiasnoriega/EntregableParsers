package com.example.matias.entregableparsers.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.matias.entregableparsers.R;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentMain, recyclerViewFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        setContentView(R.layout.activity_main);
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentMain, recyclerViewFragment);
        fragmentTransaction.commit();
    }

}

