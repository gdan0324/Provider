package com.example.phonedatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"PhoneContacts.db",null,1);
        Button createDatabase = (Button)findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name","小王");
                values.put("sex","男");
                values.put("phone",45896320);
                db.insert("Contacts",null,values);
                values.clear();
                values.put("name","小红");
                values.put("sex","女");
                values.put("phone",87416925);
                db.insert("Contacts",null,values);
            }
        });

        Button queryButton = (Button)findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Contacts",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String sex = cursor.getString(cursor.getColumnIndex("sex"));
                        int phone = cursor.getInt(cursor.getColumnIndex("phone"));
                        Log.d("MainActivity","contacts name is " + name);
                        Log.d("MainActivity","contacts sex is " + sex);
                        Log.d("MainActivity","contacts phone is " + phone);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }


}
