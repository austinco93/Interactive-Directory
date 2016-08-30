package com.example.shakabreaux.corotanfinalproject;

import android.provider.BaseColumns;

/**
 * Created by ShakaBreaux on 6/1/16.
 */
public class TableData {
    public TableData(){

    }

    public static abstract class TableInfo implements BaseColumns{
        public static final String ROOM_NUMBER = "room_number";
        public static final String FIRST = "first";
        public static final String LAST = "last";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String EMAIL = "email";
        public static final String WIDTH = "width";
        public static final String HEIGHT = "height";
        public static final String DATABASE_NAME = "WWU_Directory";
        public static final String TABLE_NAME = "Computer_Science_Final";
    }
}
