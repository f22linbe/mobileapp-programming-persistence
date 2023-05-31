package com.example.persistence;

public class DatabaseTables {

    static class Island {

        static final String TABLE_NAME = "island";
        static final String COLUMN_NAME_LOCATION = "location";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_SIZE = "size";

    }

    static final String SQL_CREATE_TABLE_ISLAND =
            // "CREATE TABLE mountain (id INTEGER PRIMARY KEY, name TEXT, height INT)"
            "CREATE TABLE " + Island.TABLE_NAME + " (" +
                    Island.COLUMN_NAME_NAME + " TEXT PRIMARY KEY," +
                    Island.COLUMN_NAME_LOCATION + " TEXT," +
                    Island.COLUMN_NAME_SIZE + " INT)";

    static final String SQL_DELETE_TABLE_ISLAND =
            // "DROP TABLE IF EXISTS mountain"
            "DROP TABLE IF EXISTS " + Island.TABLE_NAME;

}