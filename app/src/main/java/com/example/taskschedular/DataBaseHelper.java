package com.example.taskschedular;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static com.example.taskschedular.CONSTANTS.DATABASE_NAME;
import static com.example.taskschedular.CONSTANTS.DATABASE_VERSION;
import static com.example.taskschedular.CONSTANTS.DATEANDTIME;
import static com.example.taskschedular.CONSTANTS.DESCRIPTION;
import static com.example.taskschedular.CONSTANTS.ID;
import static com.example.taskschedular.CONSTANTS.TABLE_NAME;
import static com.example.taskschedular.CONSTANTS.TASK;

public class DataBaseHelper extends SQLiteOpenHelper {
    
    Context context;


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TASK + " TEXT, " +
                        DESCRIPTION + " TEXT, " +
                        DATEANDTIME + " INTEGER);";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    
    public void addBook(String taskName, String taskDescription, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TASK, taskName);
        cv.put(DESCRIPTION, taskDescription);
        cv.put(DATEANDTIME, time);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Task Added", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }
}
