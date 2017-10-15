package com.example.keera.pharmacie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class DB extends SQLiteOpenHelper{
    //projet.db
    public static final String DbName="projet.db";
    String DB_PATH=null;

    public static final int version=1;
    public DB(Context context) {
        super(context,DbName, null, version);
        this.DB_PATH="/data/data"+context.getPackageName() + "/" + "databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Pharmacies (id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, adresse TEXT, quartier TEXT,tel TEXT , latitude REAL, longitude REAL)");

    }
    public void Update(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update Pharmacies set adresse = 'Rue 2, Aïn Chock' WHERE nom ='Pharmacie Sabri' ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table IF EXISTS Pharmacies");
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String name,String Quar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom",name);
        contentValues.put("quartier",Quar);
        long results =db.insert("Pharmacie",null,contentValues);
        if(results == -1) return false;
        else return true;
    }
    public ArrayList getAllelement(){
        ArrayList array = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Pharmacies",null);
        res.moveToFirst();
        while(res.isAfterLast()==false){

            String t1 = res.getString(1);
            String t2 = res.getString(2);
            String t3 = res.getString(3);
            String t4 = res.getString(4);
            Pharmacie A  = new Pharmacie(t3,t1,t2,t4);
            array.add(A);
            res.moveToNext();
        }
        return array;
    }
    public boolean checkDB(){
        boolean rowExists = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM Pharmacies", null);
        if (mCursor.moveToFirst())
        {  rowExists = true;
        } else
        {
            rowExists = false;
        }
    return rowExists;
    }

    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Drop TABLE Pharmacies");
        onCreate(db);
    }
    public  boolean insertDB(Pharmacie A){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long results=0;


            contentValues.put("nom",A.getNom());
            contentValues.put("adresse",A.getAdd());
            contentValues.put("quartier",A.getQuartier());
            contentValues.put("tel",A.getTel());
            contentValues.put("latitude","adaz");
            contentValues.put("longitude","zadazd");
            results =  db.insert("Pharmacies",null,contentValues);

        if(results == -1) return false;
        else return true;
    }

}
