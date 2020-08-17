package com.example.taskschedular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView numberView;

    ArrayList<String> id, taskName, taskDescription, DateAndTime;
    DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DataBaseHelper(this);
        id = new ArrayList<>();
        taskName= new ArrayList<>();
        taskDescription = new ArrayList<>();
        DateAndTime = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        storeDataInArray();
//
//        Log.d("ASHISH", id.get(id.size()-1));
//        Log.d("ASHISH", taskName.toString());
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, MainActivity.this, id, taskName, taskDescription, DateAndTime);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void storeDataInArray() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Nothing Found in Database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                taskName.add(cursor.getString(1));
                taskDescription.add(cursor.getString(2));
                DateAndTime.add(cursor.getString(3));

            }
            Toast.makeText(this, "All Data Set", Toast.LENGTH_SHORT).show();
        }
    }


    public void addActivity(View view) {
        Intent addIntent = new Intent(this, addActivity.class);
        addIntent.putExtra("notificationID", String.valueOf(Integer.parseInt(id.get(id.size()-1)) + 1))
;        startActivity(addIntent);
    }
}