package com.example.hasee.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    private final Context context;
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private final static String DB_TABLE="studentinfo";
    private final static String DB_NAME="student.db";
    private final static int DB_VERSION=1;
    private final static String KEY_ID="id";
    private final static String KEY_SNO="sno";
    private final static String KEY_SNAME="sname";
    private final static String KEY_CLASSES="classes";

    public DBAdapter(Context _cContext) {
        context = _cContext;
    }

    public void close()
    {
        if(db!=null)
        {
            db.close();
            db=null;
        }
    }

    public void open() throws SQLiteException
    {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        try
        {
            db = dbHelper.getWritableDatabase();
        }
        catch(Exception e)
        {
            db  = dbHelper.getReadableDatabase();
        }
    }

    public long insert(Student s)
    {

        ContentValues cv = new ContentValues();
        cv.put(KEY_SNO,s.getSno() );
        cv.put(KEY_SNAME, s.getSname());
        cv.put(KEY_CLASSES, s.getClasses());
        return db.insert(DB_TABLE, null, cv);
    }

    public Student[] queryAllData() {
        Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, KEY_SNO, KEY_SNAME, KEY_CLASSES},
                null, null, null, null, null);
        return convertToStudent(results);
    }
    public long deleteOneData(long id) {
        return db.delete(DB_TABLE,  KEY_ID + "=" + id, null);
    }
    public Student[] convertToStudent(Cursor c)
    {
        int resultsCount = c.getCount();
        if(resultsCount==0||!c.moveToFirst())
        {
            return null;
        }

        Student []stu = new Student[resultsCount];
        for (int i = 0; i < stu.length; i++) {

            stu[i] = new Student();
            String sno  = c.getString(c.getColumnIndex("sno"));
            String sname = c.getString(c.getColumnIndex("sname"));
            String classes = c.getString(c.getColumnIndex("classes"));
            stu[i].id=c.getInt(0);
            stu[i].setSno(sno);
            stu[i].setSname(sname);
            stu[i].setClasses(classes);
            c.moveToNext();
        }
        return stu;

    }

    private   static class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        private static final String SQL="CREATE TABLE "+DB_TABLE
                +"("
                + KEY_ID +"INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SNO +"TEXT DEFAULT null,"
                + KEY_SNAME +"TEXT DEFAULT null,"
                + KEY_CLASSES+" TEXT DEFAULT null"
                + ");";

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
            onCreate(db);

        }
    }
}
