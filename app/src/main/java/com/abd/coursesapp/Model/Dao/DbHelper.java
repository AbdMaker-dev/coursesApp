package com.abd.coursesapp.Model.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.abd.coursesapp.Model.Modele;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String db_name = "courseApp.db";
    private static final int db_version = 1;
    private List<Modele> list;

    public DbHelper(Context context, List<Modele> list) {
        super(context, db_name, null, db_version);
        this.list = list;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       if (list != null){
           for (int i= 0; i< this.list.size(); i++){
               db.execSQL(this.createTable(this.list.get(i)));
           }
       }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){ }

    public String createTable(Modele object){
        String sql = "";
        Field[] fields = object.getClass().getDeclaredFields();
        String table = "";
        for(int i = 0; i< fields.length; i++){
            String field = fields[i].toString();
            String[] arr = field.split("\\s");
            // Table name
            if (i==0){
                table = this.shearchClassName(arr[arr.length-1].split("\\."));
                sql = "CREATE TABLE " + table + "(";
            }
            // GET ColumnName
            String columnName = this.shearch(arr[arr.length-1].split("\\."));
            sql = sql + "" + columnName ;

            // GET TYpes
            String type = "";
            if (arr.length == 2){
                type = this.type(shearch(arr[0].split("\\.")));
            }else {
                type = this.type(shearch(arr[1].split("\\.")));
            }
            sql = sql + " " + type;

            if(columnName.equals("id")){
                sql = sql + " PRIMARY KEY AUTOINCREMENT";
            }
            if (i+1 != fields.length){
                sql = sql + ", ";
            }
        }
        return sql + " )";
    }

    public String type(String types){
        String typ = "";
        switch (types){
            case "int":
                typ = "INTEGER";
                break;
            case "String":
                typ =  "VARCHAR(255)";
                break;
            case "Date":
                typ = "Date";
                break;
            case "Double":
                typ = "Double";
                break;
            case "boolean":
                typ = "boolean";
                break;
            default:
                typ = "TXET";
                break;
        }
        return  typ;
    }

    public String shearch(String[] types){
        if(types.length == 1){
            return  types[0];
        }else {
            return types[types.length-1];
        }
    }

    public String shearchClassName(String[] types){
        return types[types.length-2];
    }
}
