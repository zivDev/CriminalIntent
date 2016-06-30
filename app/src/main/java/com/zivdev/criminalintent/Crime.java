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

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime(){

        mId = UUID.randomUUID();
        mDate = new Date();

    }

    public Crime(JSONObject json)throws JSONException{
        mId = UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_TITLE)){
            mTitle = json.getString(JSON_TITLE);
        }
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mId.toString());
        json.put(JSON_SOLVED, mId.toString());
        json.put(JSON_DATE, mId.toString());
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

        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {

        return mSolved;
    }

    public void setSolved(boolean solved) {

        this.mSolved = solved;
    }
    public String toString(){
        return mTitle;
    }

}
