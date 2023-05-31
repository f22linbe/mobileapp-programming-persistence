package com.example.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.insertButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText islandName = findViewById(R.id.editName);
                EditText islandLocation = findViewById(R.id.editLocation);
                EditText islandSize = findViewById(R.id.editSize);

                String name = islandName.getText().toString();
                String location = islandLocation.getText().toString();
                String size = islandSize.getText().toString();

                try (DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                     SQLiteDatabase database = databaseHelper.getWritableDatabase()) {

                    ContentValues values = new ContentValues();
                    values.put(DatabaseTables.Island.COLUMN_NAME_NAME, name);
                    values.put(DatabaseTables.Island.COLUMN_NAME_LOCATION, location);
                    values.put(DatabaseTables.Island.COLUMN_NAME_SIZE, size);

                    long newTableRow = database.insert(DatabaseTables.Island.TABLE_NAME, null, values);
                }
            }
        });

        Button readTableButton = findViewById(R.id.readButton);
        readTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                SQLiteDatabase database = databaseHelper.getReadableDatabase();

                Cursor cursor = database.query(DatabaseTables.Island.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );

                StringBuilder tableContent = new StringBuilder();

                while (cursor.moveToNext()) {
                    String columnName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Island.COLUMN_NAME_NAME));
                    String columnLocation = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Island.COLUMN_NAME_LOCATION));
                    String columnSize = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Island.COLUMN_NAME_SIZE));
                    tableContent.append(columnName).append(", ").append(columnLocation).append(" ").append(columnSize).append(" km²").append("\n");
                }
                cursor.close();
                database.close();

                TextView islandTextView = findViewById(R.id.viewTable);
                islandTextView.setText(tableContent.toString());
            }
        });
    }
}
