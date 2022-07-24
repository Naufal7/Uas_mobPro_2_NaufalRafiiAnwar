package com.example.pets;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    private FrameLayout container;
    private BottomNavigationView bottomNavigationView;

    private final DashboardFragment dashboardFragment = new DashboardFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();

    private final RescueFragment rescueFragment = new RescueFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        loadFragment(dashboardFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_profile : loadFragment(profileFragment);
                        return true;
                    case R.id.menu_beranda : loadFragment(dashboardFragment);
                        return true;

                    case R.id.menu_rescue : loadFragment(rescueFragment);
                        return true;

                }
                return false;
            }
        });
    }

    private void initView(){
        container = findViewById(R.id.container);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
