package com.zivdev.criminalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
=======
>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
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

<<<<<<< HEAD
    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detailFragmentContainer)==null){
            Intent i = new Intent(this,CrimePagerActivity.class);
            i.putExtra(CrimeFragment.EXTRA_CRIME_ID,crime.getId());
            startActivity(i);
        }else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailFragmentContainer);
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());

            if (oldDetail!=null){
                ft.remove(oldDetail);
            }

            ft.add(R.id.detailFragmentContainer,newDetail);
            ft.commit();
        }
    }
=======



>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
}
