package a404_notfound.sourceappwater.controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

import a404_notfound.sourceappwater.R;

public class LoadingActivity extends AppCompatActivity {
    private ProgressDialog progress;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        new LoadViewTask().execute();
    }

    private class LoadViewTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            //create progress bar and show it
            setContentView(R.layout.activity_loading);
            pb = (ProgressBar) findViewById(R.id.progressBar);

//            progress = new ProgressDialog(LoadingActivity.this);
//            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progress.setTitle("Loading...");
//            progress.setProgress(0);
//            progress.show();
        }

        @Override
        protected Void doInBackground(Void...params) {
            //the stuff that the user won't see
            try {
                synchronized(this) {
                    int counter = 0;
                    while(counter <= 4) {
                        this.wait(850);
                        counter++;
                        publishProgress(counter * 25);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer...Values) {
            //set the current progress
//            progress.setProgress(Values[0]);
            pb.setProgress(Values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            //get rid of progress dialog
//            progress.dismiss();

            //show the screen's content
            setContentView(R.layout.activity_welcome);
            Button login = (Button) findViewById(R.id.login_button);
        }

    }

    /**
     * Button handler for the login button
     * @param view the button
     */
    public void onLoginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Button handler for the register button
     * @param view the button
     */
    public void onRegisterPressed(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
