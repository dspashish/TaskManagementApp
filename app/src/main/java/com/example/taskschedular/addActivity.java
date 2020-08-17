package com.example.taskschedular;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class addActivity extends AppCompatActivity {

    DatePicker datePicker;
    EditText task, taskDescription;
    String notificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        datePicker = findViewById(R.id.date);
        task = findViewById(R.id.taskName);
        taskDescription = findViewById(R.id.taskdescription);
        notificationID = getIntent().getStringExtra("notificationID");
        task.setText(notificationID);
    }

    public void addMyTask(View view) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(addActivity.this);
        dataBaseHelper.addBook(task.getText().toString(), taskDescription.getText().toString(), String.valueOf(datePicker.getDayOfMonth()));
    }

}

