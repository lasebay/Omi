package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirebaseUtility;

/**
 * Controller for changing user information
 */
public class EditInfoActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mAddrs;
    private EditText mCoord;


    private FirebaseUtility fbinstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mName = (EditText) findViewById(R.id.nameEdit);
        mAddrs = (EditText) findViewById(R.id.addressEdit);
        mCoord = (EditText) findViewById(R.id.coordinateEdit);
        fbinstance = new FirebaseUtility();

        Button changes = (Button) findViewById(R.id.submitChangesButton);
        changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo();
                Intent switchScreen = new Intent(getApplicationContext(), BaseActivity.class);
                startActivity(switchScreen);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        fbinstance.addAuthListner();
    }

    //Stop the firbase Listener
    @Override
    public void onStop() {
        super.onStop();
        fbinstance.removeAuthListener();
    }

    private void updateInfo() {
        String name = mName.getText().toString();
        String address = mAddrs.getText().toString();
        String coordinates  = mCoord.getText().toString();
        boolean cancel = false;
        View focusView = null;

        mName.setError(null);

        if (name.isEmpty()) {
            cancel = true;
            focusView = mName;
            mName.setError("This field cannot be empty");
        }

        if (address.isEmpty()) {
            cancel = true;
            focusView = mAddrs;
            mAddrs.setError("This field cannot be empty");
        }

        if (coordinates.isEmpty()) {
            cancel = true;
            focusView = mCoord;
            mCoord.setError("This field cannot be empty");
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            DatabaseReference useInfo = fbinstance.getmRef().child("/users").child(
                    fbinstance.getUser());

            useInfo.child("/name").setValue(name);
            useInfo.child("addrs").setValue(address);
            useInfo.child("/coor").setValue(coordinates);
        }
    }
}
