package com.example.liangwenchao.appdemo.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.liangwenchao.appdemo.utils.MyDbHelpler;

/**
 * Created by LiangWenchao on 2016/4/18.
 */
public class MyContentProvider extends ContentProvider {

    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;

    private MyDbHelpler dBHelper;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI("com.example.liangwenchao.appdemo","book",BOOK_DIR);
        uriMatcher.addURI("com.example.liangwenchao.appdemo","book/#",BOOK_ITEM);
        uriMatcher.addURI("com.example.liangwenchao.appdemo","category",CATEGORY_DIR);
        uriMatcher.addURI("com.example.liangwenchao.appdemo","category/#",CATEGORY_ITEM);
    }
    @Override
    public boolean onCreate() {
        dBHelper = new MyDbHelpler(getContext(),"content",null,2);
        return true;
    }
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dBHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book",null,values);
                uriReturn = Uri.parse("content://" + "com.example.liangwenchao.appdemo" + "/book/" + newBookId);
        }


        return uriReturn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dBHelper.getWritableDatabase();
        int deleteId = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deleteId = db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String id = uri.getPathSegments().get(1);
                deleteId = db.delete("Book","id = ?",new String[]{id});
        }
        return deleteId;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dBHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                db.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String id = uri.getPathSegments().get(1);
                db.update("Book",values,"id = ?",new String[]{id});
                break;
        }
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String id = uri.getPathSegments().get(1);
                cursor = db.query("Book",projection,"id = ?",new String[]{id},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.liangwenchao.appdemo.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.liangwenchao.appdemo.book/#";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.liangwenchao.appdemo.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.liangwenchao.appdemo.category/#";

        }

        return null;
    }
}
