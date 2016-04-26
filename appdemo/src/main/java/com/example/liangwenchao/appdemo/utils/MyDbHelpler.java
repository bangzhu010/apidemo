package com.example.liangwenchao.appdemo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LiangWenchao on 2016/4/15.
 */
public class MyDbHelpler extends SQLiteOpenHelper{
    private Context mContext;

    public static final String CREATE_BOOK = "create table Book(" +
            "id integer primary key autoincrement" +
            "author text," +
            "price real," +
            "pages integer" +
            "name text)";

    public static final String CREATE_CATEGORY = "create table Category(" +
            "id integer primary key autoincrement" +
            "category_name text" +
            "+category_code ingeger)";


    public MyDbHelpler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        ContentValues values = new ContentValues();
        db.insert("tableName",null,values);
        db.update("table",values,"whereClause = ? ",new String[]{"xiaoming"});
        Cursor c = db.query("tableName",null,null,null,null,null,null);
        if(c.moveToFirst()){
            do {
                String name = c.getString(c.getColumnIndex("name"));
            } while (c.moveToNext());
        }
        c.close();
        onCreate(db);
    }
}
