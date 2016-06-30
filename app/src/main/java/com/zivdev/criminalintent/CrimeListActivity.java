package com.zivdev.criminalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

/**
 * Created by ziv on 16.6.23.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }




}
