package a404_notfound.sourceappwater.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirbaseUtility;

public class DrawerActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] user_tabs = {"Home", "User Profile", "Reports", "Find Water","Settings", "Logout", "Submitted Reports"};
    private String[] worker_tabs = {"Home", "User Profile", "Create User Reports","Create Worker Report"
            , "Find Water","Settings", "Logout", "Submitted Reports"};
    private ActionBarDrawerToggle mDrawerToggle;
    private FirbaseUtility firbaseUtility;
    private String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getcView());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        role = FirbaseUtility.getRole();


        if (role != null && role.equals("Worker")){
            mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                    R.layout.drawer_list_layout, worker_tabs));
        } else {
            mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                    R.layout.drawer_list_layout, user_tabs));
        }


        mDrawerList.setOnItemClickListener(new DrawerActivity.DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    protected int getcView() {
        return R.layout.activity_drawer;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (role.equals("Worker")) {
                if (position == 0){
                    startActivity(new Intent(getApplicationContext(), HqActivity.class));
                } else if (position == 1) {
                    startActivity(new Intent(getApplicationContext(), BaseActivity.class));
                } else if (position == 6 ) {
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                } else if (position ==  2){
                    startActivity(new Intent(getApplicationContext(), ReportsActivity.class));
                } else if (position == 7) {
                    startActivity(new Intent(getApplicationContext(), ViewReportActivity.class));
                } else if (position == 4) {
                    startActivity(new Intent(getApplicationContext(), WaterAvailabilty.class));
                }
            } else {
                if (position == 0){
                    startActivity(new Intent(getApplicationContext(), HqActivity.class));
                } else if (position == 1) {
                    startActivity(new Intent(getApplicationContext(), BaseActivity.class));
                } else if (position == 5 ) {
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                } else if (position ==  2){
                    startActivity(new Intent(getApplicationContext(), ReportsActivity.class));
                } else if (position == 6) {
                    startActivity(new Intent(getApplicationContext(), ViewReportActivity.class));
                } else if (position == 3) {
                    startActivity(new Intent(getApplicationContext(), WaterAvailabilty.class));
                }
            }
        }
    }
}

