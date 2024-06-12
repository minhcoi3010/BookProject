package com.example.bookprojectpractice.database;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class ClassConverter {

    @TypeConverter
    public static List<String> fromString(String value) {
        List<String> list = new ArrayList<>();
        if (value != null && !value.isEmpty()) {
            String[] items = value.split(",");
            for (String item : items) {
                list.add(item);
            }
        }
        return list;
    }

    //chuyển từ list sang String bằng cách cộng chuỗi
    @TypeConverter
    public static String toString(List<String> list) {
        //BuilderString
        StringBuilder value = new StringBuilder();
        if (list != null) {
            for (String items : list) {
                value.append(items);
                value.append(",");
            }
        }
        return value.toString();
    }
}
