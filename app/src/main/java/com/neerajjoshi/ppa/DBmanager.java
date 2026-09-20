package com.neerajjoshi.ppa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmanager extends SQLiteOpenHelper {

    private static final String dbname = "dbPPA";

    public DBmanager(@Nullable Context context)
    {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String qry = "create table tbl_webpass ( id integer primary key autoincrement,webname text, username text,pass text)";
        sqLiteDatabase.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String qry = "DROP TABLE IF EXISTS tbl_webpass";
        sqLiteDatabase.execSQL(qry);
        onCreate(sqLiteDatabase);

    }


    public  String addRecord(String web,String uname,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("webname",web);
        cv.put("username",uname);
        cv.put("pass",pass);

        float res = db.insert("tbl_webpass",null,cv);
        if(res == -1 ){
            return "Failed";
        }else {
            return "Sucssfully inserted";
        }
    }

    public Cursor readData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from tbl_webpass order by id desc";
        Cursor cursor = db.rawQuery(qry,null);
        return cursor;
    }

    public int deleteData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //String qry = "DELETE FROM tbl_webpass WHERE id = "+id;

        return db.delete("tbl_webpass","id = ?",new String[]{String.valueOf(id)});

    }

}
