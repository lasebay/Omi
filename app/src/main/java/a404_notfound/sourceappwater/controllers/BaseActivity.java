package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirebaseUtility;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Controller for the user's profile screen
 */
public class BaseActivity extends AppCompatActivity {
      private static final String TAG = "Info";


    private TextView username;
    private TextView address;
    private TextView coordinates;
    private TextView accountType;
    private FirebaseUtility fbinstance;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        username = (TextView) findViewById(R.id.username1);
        address = (TextView) findViewById(R.id.address);
        coordinates = (TextView) findViewById(R.id.coordinates);
        accountType = (TextView) findViewById(R.id.accountType);


        Button mEditInfo = (Button) findViewById(R.id.editinfobutton);
        mEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), EditInfoActivity.class);
                startActivity(switchScreen);
            }
        });



        fbinstance = new FirebaseUtility();
        fbinstance.getmRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);

                String n = dataSnapshot.child("users").child(fbinstance.getUser()).child(
                        "name").getValue().toString();
                String m = dataSnapshot.child("users").child(fbinstance.getUser()).child(
                        "addrs").getValue().toString();
                String o = dataSnapshot.child("users").child(fbinstance.getUser()).child(
                        "coor").getValue().toString();
                String p = dataSnapshot.child("users").child(fbinstance.getUser()).child(
                        "accttype").getValue().toString();

                username.setText(n);
                address.setText(m);
                coordinates.setText(fbinstance.getUserEmail());
                accountType.setText(p);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        fbinstance.addAuthListner();

    }

    //Stop the Firebase Listener
    @Override
    public void onStop() {
        super.onStop();
        fbinstance.removeAuthListener();
    }

}
