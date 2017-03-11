package a404_notfound.sourceappwater.model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joshua on 2/23/2017.
 *
 * Deals with everything Firebase related
 */

public class FirbaseUtility {

    //Firebase Database Objects
    private DatabaseReference mRef;

    // Firebase Authentication Objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "Info";

    /**
     * Sets up a connection to the Firebase Database
     */
    public FirbaseUtility() {
        // Set up fire base Authentication and Listener for that authentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        //Set Up Database For Session
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mRef = database.getReference();
    }

    /**
     * Registers an authentication listener with Firebase
     */
    public void addAuthListner() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    /**
     * Unregisters an authentication listener  with Firebase
     */
    public void removeAuthListener() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    /**
     * Getter for the database reference
     * Database reference represents a location in the database
     *
     * @return the current database reference
     */
    public DatabaseReference getmRef() {
        return mRef;
    }

    /**
     * Setter for the database reference
     *
     * @param mRef the new location in the database to access
     */
    public void setmRef(DatabaseReference mRef) {
        this.mRef = mRef;
    }

    /**
     * Getter for the firebase authentication
     *
     * @return The entrypoint of the firebase authentication sdk being used
     */
    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    /**
     * Setter for the firebase authentication
     *
     * @param mAuth The new entrypoint of the firebase authentication sdk to be used
     */
    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    /**
     * Getter for the authentication state listener
     *
     * @return The new authentication listener being used
     */
    public FirebaseAuth.AuthStateListener getmAuthListener() {
        return mAuthListener;
    }

    /**
     * Setter for the authentication state listener
     *
     * @param mAuthListener The new authentication listener to be used
     */
    public void setmAuthListener(FirebaseAuth.AuthStateListener mAuthListener) {
        this.mAuthListener = mAuthListener;
    }

    /**
     * Getter for the user's unique ID#
     * note: different from username
     *
     * @return the user's ID
     */
    public String getUser() {
        if (mAuth != null) {
            return mAuth.getCurrentUser().getUid();
        }
        return null;
    }

    /**
     * Getter for user email from database unless
     * the authentication wasn't instantiated
     *
     * @return The user's email or
     *         null if no authentication
     */
    public String getUserEmail(){
        if (mAuth != null) {
            return mAuth.getCurrentUser().getEmail();
        }
        return null;
    }

    /**
     * Adds a report to the Firebase database
     *
     * @param mRefIn Where in the database the report will be stored
     * @param report The report to be stored
     */
    public void addReport(DatabaseReference mRefIn, Report report) {
        DatabaseReference reportLocation = mRefIn.child("reports");
        String key = reportLocation.push().getKey();
        Map<String, Object> reportvalues = report.toMap();

        Map<String, Object> updates = new HashMap<>();
        updates.put("/reports/" + key, reportvalues);

        mRefIn.updateChildren(updates);

    }
}
