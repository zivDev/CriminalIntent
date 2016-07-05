package com.zivdev.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ziv on 16.6.21.
 */
public class Crime {
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";
    private static final String JSON_PHOTO = "photo";
<<<<<<< HEAD
    private static final String JSON_SUSPECT = "suspect";

=======
>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private Photo mPhoto;
<<<<<<< HEAD
    private String mSuspect;
    private boolean mSolved;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
=======
    private boolean mSolved;

    public Crime(){

        mId = UUID.randomUUID();
        mDate = new Date();

>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
    }

    public Crime(JSONObject json)throws JSONException{
        mId = UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_TITLE)){
            mTitle = json.getString(JSON_TITLE);
        }
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));

        if (json.has(JSON_PHOTO))
            mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));
<<<<<<< HEAD
        if (json.has(JSON_SUSPECT))
            mSuspect = json.getString(JSON_SUSPECT);
=======
>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mId.toString());
        json.put(JSON_SOLVED, mId.toString());
        json.put(JSON_DATE, mId.toString());
        if (mPhoto!=null)
            json.put(JSON_PHOTO,mPhoto.toJSON());
<<<<<<< HEAD
        json.put(JSON_SUSPECT,mSuspect);
=======
>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
        return json;

    }

    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
<<<<<<< HEAD
=======

>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
<<<<<<< HEAD
=======

>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
        return mSolved;
    }

    public void setSolved(boolean solved) {
<<<<<<< HEAD
=======

>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
        this.mSolved = solved;
    }
    public String toString(){
        return mTitle;
    }
<<<<<<< HEAD
=======


>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
    public Photo getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo photo) {
        mPhoto = photo;
    }
<<<<<<< HEAD

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect){
        mSuspect = suspect;
    }
=======
>>>>>>> f28275eca12580a0e1164e1003d57871ddc544ab
}
