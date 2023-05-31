package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    private long insertIsland(Island i) {
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.Island.COLUMN_NAME_NAME, i.getName());
        values.put(DatabaseTables.Island.COLUMN_NAME_SIZE, i.getSquarekm());
        return database.insert(DatabaseTables.Island.TABLE_NAME, null, values);
    }
    private List<Island> getIslands() {
        Cursor cursor = database.query(DatabaseTables.Island.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        List<Island> islands = new ArrayList<>();
        while (cursor.moveToNext()) {
            Island island = new Island(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Island.COLUMN_NAME_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Island.COLUMN_NAME_LOCATION)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTables.Island.COLUMN_NAME_SIZE))
            );
            islands.add(island);
        }
        cursor.close();
        return islands;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init DB helper
        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();
    }
}
