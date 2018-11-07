package com.eclectics.canaandairysalesapp;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout nav_menu, nav_web_portal, nav_help, nav_logout;
    private DrawerLayout drawer;
    //private FrameLayout app_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav_menu = findViewById(R.id.nav_menu);
        nav_web_portal = findViewById(R.id.nav_web_portal);
        nav_help = findViewById(R.id.nav_help);
        nav_logout = findViewById(R.id.nav_logout);
        drawer = findViewById(R.id.drawer_layout);
        //app_content = findViewById(R.id.app_content);

        initialize();

        nav_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //replace exiting fragment with correct one
                MainMenuFragment fragmentMenu = new MainMenuFragment();
                android.support.v4.app.FragmentTransaction fragmentTransactionMenu =
                        getSupportFragmentManager().beginTransaction();

                fragmentTransactionMenu.replace(R.id.app_content, fragmentMenu);
                fragmentTransactionMenu.addToBackStack(null);
                fragmentTransactionMenu.commit();
                //close the drawer on your way out
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        nav_web_portal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        nav_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        nav_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                drawer.closeDrawer(GravityCompat.START);
            }
        });


    }

    private void initialize() {
        DashboardFragment fragmentDash = new DashboardFragment();
        android.support.v4.app.FragmentTransaction fragmentTransactionDash =
                getSupportFragmentManager().beginTransaction();

        fragmentTransactionDash.replace(R.id.app_content, fragmentDash);
        //fragmentTransactionDash.addToBackStack(null);
        fragmentTransactionDash.commit();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void openDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.openDrawer(Gravity.START);
    }
}
