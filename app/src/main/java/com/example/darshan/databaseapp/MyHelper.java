package com.example.darshan.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darshan on 18-12-2015.
 */
public class MyHelper extends SQLiteOpenHelper{

    /* write below statement*/
    public static String DATABASE_NAME="MyDb";
    public static String TABLE_NAME="MyTable";
    public static int DATABASE_VERSION=1;

    public static String KEY_NUM="num";
    public static String KEY_NAME="name";

    Context ctx;

    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx=context;
    }

    /* called only once*/
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE "+TABLE_NAME+"("+KEY_NUM+" text primary key,"+KEY_NAME+" text)";

       try {
           db.execSQL(sql);
       }
       catch (Exception ex) {
            Toast.makeText(ctx,"Error in creation"+ex.toString(),Toast.LENGTH_SHORT).show();
       }
    }

    /* called if version number changes*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql="drop table if exists "+TABLE_NAME;

        db.execSQL(sql);
        onCreate(db);
    }

    public void addData(MyData md) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NUM,md.getNum());
            values.put(KEY_NAME,md.getName());

            long i= db.insert(TABLE_NAME, null, values);
            if(i>0)
            {
                Toast.makeText(ctx,"SuccessfulLy inserted",Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(ctx,"Duplication alert !!",Toast.LENGTH_SHORT).show();


        }
        catch (Exception ex) {
            Toast.makeText(ctx,"Error in creation"+ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public List<MyData> display() {

        List<MyData> lst = new ArrayList<MyData>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "select * from "+TABLE_NAME;

            Cursor cr = db.rawQuery(sql,null);

            while (cr.moveToNext())
            {
                MyData d = new MyData();
                d.setNum(cr.getString(0));
                d.setName(cr.getString(1));
                lst.add(d);
            }
            db.close();

        }
        catch (Exception ex) {
            MyData d=new MyData("DataNotFound","Error");
            Toast.makeText(ctx,"Error in creation"+ex.toString(),Toast.LENGTH_SHORT).show();
        }
        return lst;
    }

    public void doDelete(String jnum) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            long i=db.delete(TABLE_NAME,KEY_NAME+"=?",new String[]{jnum});
            if(i>0)
            {
                Toast.makeText(ctx,"Deletion success",Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(ctx,"Did not delete !!",Toast.LENGTH_SHORT).show();

        }
        catch (Exception ex) {
            MyData d=new MyData("DataNotFound","Error");
            Toast.makeText(ctx,"Error in creation"+ex.toString(),Toast.LENGTH_SHORT).show();
        }

    }

    public void doUpdate(String jnum, String jname) {
        try{

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, jname);

            long i = db.update(TABLE_NAME, values, KEY_NUM + "=?", new String[]{jnum});
            if(i>0)
            {
                Toast.makeText(ctx,"Updation success",Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(ctx,"NO NO NO !!",Toast.LENGTH_SHORT).show();

        }
        catch (Exception ex)
        {
            MyData d=new MyData("DataNotFound","Error");
            Toast.makeText(ctx,"Error in Updation"+ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
