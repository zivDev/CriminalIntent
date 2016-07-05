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
<<<<<<< HEAD
public abstract class SingleFragmentActivity extends FragmentActivity
        implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks {
    protected abstract Fragment createFragment();

    protected int getLayoutResId(){
        return R.layout.activity_masterdetail;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
        setContentView(getLayoutResId());
=======
public abstract class SingleFragmentActivity extends FragmentActivity{
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_fragment);
>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment ==null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer,fragment)
                    .commit();
        }
    }

<<<<<<< HEAD
    @Override
    public void onCrimeSelected(Crime crime) {

    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        FragmentManager fm = getSupportFragmentManager();
        CrimeListFragment listFragment = (CrimeListFragment)fm.findFragmentById(R.id.fragmentContainer);
        listFragment.updateUI();
    }
=======

>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
}
