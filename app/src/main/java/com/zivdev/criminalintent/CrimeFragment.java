package com.zivdev.criminalintent;


import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;

import android.text.format.DateFormat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Date;
import java.util.UUID;
//import
/**
 * Created by ziv on 16.6.21.
 */

public class CrimeFragment extends Fragment {
    private static final String TAG = "CrimeFragment";
    public static final String EXTRA_CRIME_ID = "com.zivdev.criminalintent.crime_id";
    private static final  String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO = 1;

    private static final int REQUEST_CONTACT = 2;
    private static final String DIALOG_IMAGE = "image";

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    private ImageButton mPhotoButton;
    private Button mSuspectButton;

    private ImageView mPhotoView;

    private Callbacks mCallbacks;

    public interface Callbacks{
        void onCrimeUpdated(Crime crime);
    }

    private String getCrimeReport(){
        String solvedString = null;
        if (mCrime.isSolved()){
            solvedString = getString(R.string.crime_report_solved);
        }else {
            solvedString = getString(R.string.crime_report_unsolved);
        }

        String dateFormat = "EEE,MMM dd";
        String dateString = DateFormat.format(dateFormat,mCrime.getDate()).toString();

        String suspect = mCrime.getSuspect();
        if (suspect==null){
            suspect = getString(R.string.crime_report_no_suspect);
        }else {
            suspect = getString(R.string.crime_report_suspect,suspect);
        }

        String report = getString(R.string.crime_report,mCrime.getTitle(),dateString,solvedString,suspect);

        return report;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //方法一：
        //因为这个方法总需要由某个具体activity托管，造成无法复用CrimeFragemnt

//        UUID crimeid = (UUID)getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
//        mCrime = CrimeLab.get(getActivity()).getCrime(crimeid);

        //方法二：
        UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity())!=null){
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID,crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void returnResult(){
        getActivity().setResult(Activity.RESULT_OK,null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            if (NavUtils.getParentActivityName(getActivity())!=null){
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        //更新
//        updateDate();

//        mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
//                DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());

                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(fm,DIALOG_DATE);
            }
        });

        //2.设置chenkBox
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
                mCallbacks.onCrimeUpdated(mCrime);

            }
        });

        //3.设置标题
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
                mCallbacks.onCrimeUpdated(mCrime);
                getActivity().setTitle(mCrime.getTitle());

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mPhotoButton = (ImageButton)v.findViewById(R.id.crime_imageButton);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),CrimeCamerActivity.class);
                startActivityForResult(i,REQUEST_PHOTO);
            }
        });

        //显示ImageFragment界面
        mPhotoView = (ImageView)v.findViewById(R.id.crime_imageView);
        mPhotoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Photo p = mCrime.getPhoto();
                if (p==null)
                    return;

                FragmentManager fm = getActivity().getSupportFragmentManager();
                String path = getActivity().getFileStreamPath(p.getFilename()).getAbsolutePath();
                ImageFragment.newInstance(path).show(fm,DIALOG_IMAGE);
            }
        });

        //检查设备是否带相机
        PackageManager pm = getActivity().getPackageManager();
        boolean hasACamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)||
                pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)||
                Build.VERSION.SDK_INT<Build.VERSION_CODES.GINGERBREAD||
                Camera.getNumberOfCameras()>0;
        if (!hasACamera){
            mPhotoButton.setEnabled(false);
        }

        Button reportButton = (Button)v.findViewById(R.id.crime_repport_button);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,getCrimeReport());
                i.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.crime_report_subject));
                i = Intent.createChooser(i,getString(R.string.send_report));
                startActivity(i);
            }
        });

        mSuspectButton = (Button)v.findViewById(R.id.crime_suspectButton);
        mSuspectButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i,REQUEST_CONTACT);
            }
        });

        if (mCrime.getSuspect()!=null){
            mSuspectButton.setText(mCrime.getSuspect());
        }
        return v;
    }

    //加载图片
    private void showPhoto(){
        Photo p = mCrime.getPhoto();
        BitmapDrawable b = null;
        if (p!=null){
            String path = getActivity().getFileStreamPath(p.getFilename()).getAbsolutePath();
            b = PictureUtils.getScaledDrawable(getActivity(),path);
        }
        mPhotoView.setImageDrawable(b);
    }

    @Override
    public void onStart() {
        super.onStart();
        showPhoto();
    }

    @Override
    public void onStop() {
        super.onStop();
        PictureUtils.cleanImageView(mPhotoView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {

            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);

            mCallbacks.onCrimeUpdated(mCrime);

            updateDate();
//            Log.i("OAR", "-----------------" + mCrime.getDate().toString());
        }else if (requestCode ==REQUEST_PHOTO){
            String filename = data.getStringExtra(CrimeCameraFragment.EXTRA_PHOTO_FILENAME);
            if (filename!=null){
                Log.i(TAG,"filename: "+filename);
                Photo p = new Photo(filename);
                mCrime.setPhoto(p);
                mCallbacks.onCrimeUpdated(mCrime);
                showPhoto();

            }
        }else if (requestCode== REQUEST_CONTACT){
            Uri contactUri = data.getData();
            String[] queryFields = new String[]{
                    ContactsContract.Contacts.DISPLAY_NAME
            };

           Cursor c = getActivity().getContentResolver().query(contactUri,queryFields,null,null,null);
            if (c.getCount()==0){
                c.close();
                return;
            }

            c.moveToFirst();
            String suspect = c.getString(0);
            mCrime.setSuspect(suspect);
            mCallbacks.onCrimeUpdated(mCrime);
            mSuspectButton.setText(suspect);
            c.close();
        }
    }
    public void updateDate(){
        mDateButton.setText(mCrime.getDate().toString());
    }
}
