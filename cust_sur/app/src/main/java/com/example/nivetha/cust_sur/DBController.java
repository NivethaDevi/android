package com.example.nivetha.cust_sur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DBController  extends SQLiteOpenHelper {
     Context con;
    public DBController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
    }
    //Creates Table
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE users ( userId INTEGER PRIMARY KEY, field6 TEXT,field7 TEXT,field8 TEXT,field9 TEXT,field10 TEXT,field11 TEXT,field12 TEXT,field13 TEXT,field14 TEXT,field15 TEXT,field16 TEXT,field17 TEXT,field18 TEXT,field19 TEXT,field20 TEXT,field21 TEXT,field22 TEXT,field23 TEXT,field24 TEXT,field25 TEXT,field26 TEXT,field27 TEXT,field28 TEXT,field29 TEXT,field30 TEXT,field31 TEXT,field32 TEXT,field33 TEXT,field34 TEXT,field35 TEXT,field36 TEXT,field37 TEXT,field38 TEXT,field39 TEXT,field40 TEXT,field41 TEXT,field42 TEXT,field43 TEXT,field44 TEXT,field45 TEXT,field46 TEXT,field47 TEXT,field48 TEXT,field49 TEXT,field50 TEXT, udpateStatus TEXT)";
        database.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS users";
        database.execSQL(query);
        onCreate(database);
    }
    /**
     * Inserts User into SQLite DB
     * @param queryValues
     */
    public void insertUser(LinkedHashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        Toast.makeText(con, "insert_user",
//                Toast.LENGTH_LONG).show();
        System.out.println("hi");
        for(int i=6;i<=50;i++) {
            System.out.println("field"+i);
            values.put("field" + i, queryValues.get("field" + i));
        }
        values.put("udpateStatus", "no");
        database.insert("users", null, values);
        database.close();
    }

    /**
     * Get list of Users from SQLite DB as Array List
     * @return
     */
    public ArrayList<LinkedHashMap<String, String>> getAllUsers() {
        ArrayList<LinkedHashMap<String, String>> wordList;
        wordList = new ArrayList<LinkedHashMap<String, String>>();
        String selectQuery = "SELECT  * FROM users";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
                map.put("userId", cursor.getString(0));
                for(int i=6,j=1;i<=50;i++,j++)
                    map.put("field"+i,cursor.getString(j));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    /**
     * Compose JSON out of SQLite records
     * @return
     */
    public String composeJSONfromSQLite(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM users where udpateStatus = '"+"no"+"'";
        System.out.println("Select:"+selectQuery);
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
                map.put("userId", cursor.getString(0));
                for(int i=6,j=1;i<=50;i++,j++)
                    map.put("field"+i,cursor.getString(j));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        Gson gson = new GsonBuilder().create();
        System.out.println("letussee;"+ gson.toJson(wordList));
        //Use GSON to serialize Array List to JSON
        return gson.toJson(wordList);
    }
//    public String composeJSONfromSQLite1(){
//        ArrayList<HashMap<String, String>> wordList;
//        wordList = new ArrayList<HashMap<String, String>>();
//                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
//                for(int i=0;i<5;i++)
//                    map.put("field"+(i+1),cursor.getString(j));
//        map.put();
//                wordList.add(map);
//            } while (cursor.moveToNext());
//        }
//        Gson gson = new GsonBuilder().create();
//        System.out.println("letussee;"+ gson.toJson(wordList));
//        //Use GSON to serialize Array List to JSON
//        return gson.toJson(wordList);
//    }
    /**
     * Get Sync status of SQLite
     * @return
     */
    public String getSyncStatus(){
        String msg = null;
        if(this.dbSyncCount() == 0){
            msg = "SQLite and Remote MySQL DBs are in Sync!";
        }else{
            msg = "DB Sync neededn";
        }
        return msg;
    }

    /**
     * Get SQLite records that are yet to be Synced
     * @return
     */
    public int dbSyncCount(){
        int count = 0;
        String selectQuery = "SELECT  * FROM users where udpateStatus = '"+"no"+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    /**
     * Update Sync status against each User ID
     * @param id
     * @param status
     */
    public void updateSyncStatus(String id, String status){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "Update users set udpateStatus = '"+ status +"' where userId="+"'"+ id +"'";
        System.out.println("state:"+status);
        Log.d("query",updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}