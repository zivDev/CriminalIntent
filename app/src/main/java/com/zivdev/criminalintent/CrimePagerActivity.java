package com.zivdev.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ziv on 16.6.24.
 */
<<<<<<< HEAD
public class CrimePagerActivity extends FragmentActivity implements CrimeFragment.Callbacks{
=======
public class CrimePagerActivity extends FragmentActivity {
>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("-----onCreate-----","CrimePagerActivity");
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mCrimes = CrimeLab.get(this).getCrimes();

        //设置代理
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Log.d("-------getItem------","CrimePagerActivity");
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                Log.d("-------getCount------","CrimePagerActivity");
                return mCrimes.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Crime crime = mCrimes.get(position);
                if (crime.getTitle()!=null){
                    setTitle(crime.getTitle());
                }
            }
        });

        //把列表项和对应pager绑定
        UUID crimedId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimedId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
<<<<<<< HEAD
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
=======

>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab

    }
}
