package com.example.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addData = (Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.phonedatabase.provider/contacts");
                ContentValues values = new ContentValues();
                values.put("name","小明");
                values.put("phone","94785136");
                values.put("sex","男");
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button queryData = (Button)findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.phonedatabase.provider/contacts");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor != null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String sex = cursor.getColumnName(cursor.getColumnIndex("sex"));
                        int phone = cursor.getInt(cursor.getColumnIndex("phone"));
                        Log.d("MainActivity","contacts name is " + name);
                        Log.d("MainActivity","contacts sex is " + sex);
                        Log.d("MainActivity","contacts phone is " + phone);
                    }
                    cursor.close();
                }
            }
        });

        Button updateData = (Button)findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.phonedatabase.provider/contacts/"+ newId);
                ContentValues values = new ContentValues();
                values.put("name","小梅");
                values.put("phone","17845296");
                values.put("sex","女");
                getContentResolver().update(uri,values,null,null);
            }
        });

        Button deleteData = (Button)findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.phonedatabase.provider/contacts/" + newId);
                getContentResolver().delete(uri,null,null);
            }
        });

    }
}
