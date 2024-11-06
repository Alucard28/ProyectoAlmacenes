package com.example.proyectoalmacenes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import kotlin.text.UStringsKt;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="tareas.db";

    private static final String Table_Name="task";

    private static final String Col_1= "ID";

    private static final String Col_2= "task";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, TASK TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }
    public boolean InsertTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, task);
        long result = db.insert(Table_Name, null,contentValues);
        return result != -1;
    }
    public ArrayList<String> getAllTask()
    {
        ArrayList<String> task = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Table_Name, null);
        if (res.moveToFirst()){
            do{
                task.add(res.getString(1));
            }while(res.moveToNext());
        }
        return task;
    }
    public void deleteTask(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_Name, "TASK = ?", new String[]{task});
    }

    public void updateTask(String oldTask, String newTask) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, newTask);
        db.update(Table_Name, contentValues, "TASK = ?", new String[]{oldTask});
    }
}