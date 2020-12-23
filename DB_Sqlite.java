package com.example.todo0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_Sqlite extends SQLiteOpenHelper {

    public static final String BDname ="data.db" ;

    public DB_Sqlite(@Nullable Context context) {
        super(context, BDname,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table mytable (id INTEGER PRIMARY KEY AUTOINCREMENT,note TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS mytable");
onCreate(db);
    }
    public boolean insertData(String note){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("Note",note);
        long result =db.insert("mytable",null,contentValues);
        if(result== -1)
            return false;
        else
            return true;

    }
    public ArrayList getAllrecord(){
            ArrayList arrayList =new ArrayList();
            SQLiteDatabase db=this.getReadableDatabase();
        Cursor res =db.rawQuery("select * from mytable",null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            String t1=res.getString(0);
            String t2 =res.getString(1);
            arrayList.add(t1 +" - " +t2);
            res.moveToNext();


        }
        return arrayList;
    }
    public  Integer Delete (String id){
        SQLiteDatabase  db = this.getWritableDatabase();
        return  db.delete("mytable","id=?",new String[]{id});
    }
}