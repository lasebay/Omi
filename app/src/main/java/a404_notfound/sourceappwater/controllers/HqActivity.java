package a404_notfound.sourceappwater.controllers;

import android.os.Bundle;

import a404_notfound.sourceappwater.R;

public class HqActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getcView() {
        return R.layout.activity_hq;
    }
}
