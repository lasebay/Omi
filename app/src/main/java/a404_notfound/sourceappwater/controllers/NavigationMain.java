package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
//TODO Check if needed
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirebaseUtility;


/**
 * Main navigation View for the App, contians the different sections
 */
public class NavigationMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final WaterAvailabilityMap wap = new WaterAvailabilityMap();
    private final CreateReportsActivity crp = new CreateReportsActivity();
    private final FirebaseUtility fbinstance = new FirebaseUtility();
    private final CreateWorkerReport crwp = new CreateWorkerReport();
    private final ManagerViewReport mvp = new ManagerViewReport();
    private final UserWorkerViewReport uwvp = new UserWorkerViewReport();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //making default screen the water availability screen
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_navigation_main, wap);
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        final View header = navigationView.getHeaderView(0);
        TextView userRole = (TextView) header.findViewById(R.id.userRole);
        userRole.setText(FirebaseUtility.getRole());
        TextView email = (TextView) header.findViewById(R.id.textView);
        email.setText(fbinstance.getUserEmail());

        ImageButton imageButton = (ImageButton) header.findViewById(R.id.settingsButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), EditInfoActivity.class);
                startActivity(switchScreen);
            }
        });


        fbinstance.getmRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String n = dataSnapshot.child("users").child(fbinstance.getUser()).child(
                        "name").getValue().toString();
                TextView name = (TextView) header.findViewById(R.id.name);
                name.setText(n);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelected(id);
        return true;
    }


    /**
     * Switches the screen based on which tab is selected
     * @param id The id referring to the layout tab
     */
    private void displaySelected(int id) {
        Fragment fragment = null;

//        if (id == R.id.nav_home) {
//            //TODO Deceide what will the home screen be
//        } else
        if (id == R.id.nav_find_water) {
            fragment = wap;
        } else if (id == R.id.nav_createReport) {
            if (("Worker").equals(FirebaseUtility.getRole())
                    || ("Manager").equals(FirebaseUtility.getRole())) {
                fragment = crwp;
            } else {
                fragment = crp;
            }
        } else if (id == R.id.nav_view_my_reports) {
            if (("Manager").equals(FirebaseUtility.getRole())) {
                fragment = mvp;
            } else {
                fragment = uwvp;
            }
        } else if (id == R.id.nav_logout) {
            Intent switchScreen = new Intent(getApplicationContext(), WelcomeActivity.class);
            startActivity(switchScreen);
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_navigation_main, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
