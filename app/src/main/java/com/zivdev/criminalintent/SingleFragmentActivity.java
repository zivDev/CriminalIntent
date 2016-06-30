package com.zivdev.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;

/**
 * Created by ziv on 16.6.22.
 */
public abstract class SingleFragmentActivity extends FragmentActivity{
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment ==null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer,fragment)
                    .commit();
        }
    }


}
