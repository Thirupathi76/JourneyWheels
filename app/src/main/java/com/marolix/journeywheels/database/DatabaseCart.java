package com.marolix.journeywheels.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.marolix.journeywheels.bean.Cart;

import java.util.ArrayList;

public class DatabaseCart extends SQLiteOpenHelper {

    //    public String tableName = "video_names";
    public String tableName = "cart_items";
    public String tableCountry = "country_names";
    public String fieldId = "id";

    /*public String videoName = "name";
    public String videoCountry = "name";
    public String videoState = "name";
    public String videoUrl = "url";*/

    public String itemName = "itemName";
    public String itemRest = "itemRest";
    public String itemPrice = "itemPrice";
    public String itemCount = "itemCount";


    public DatabaseCart(Context context) {
        super(context, "Food_app", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "";

        sql += "CREATE TABLE " + tableName;
        sql += " ( ";
        /*sql += fieldId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";*/
        sql += itemName + " TEXT, ";
        sql += itemPrice + " TEXT, ";
        sql += itemCount + " TEXT ";
        sql += " ) ";

        db.execSQL(sql);


        /*String sqlCountry = "";
        sqlCountry += "CREATE TABLE " + tableCountry;
        sqlCountry += " ( ";
        sqlCountry += fieldId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sqlCountry += videoCountry + " TEXT ";
        sqlCountry += " ) ";
        db.execSQL(sqlCountry);*/
//        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + tableName;
        db.execSQL(sql);

        onCreate(db);
    }

    public boolean storeData(String country, String state, String name, String url) {

        boolean createSuccessful = false;

        if (!checkIfExists(name)) {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(itemName, country);
            values.put(itemPrice, state);
            values.put(itemCount, name);
            values.put(itemPrice, url);
            createSuccessful = db.insert(tableName, null, values) > 0;

//            db.close();
            if (createSuccessful) {
                Log.e("Database", name + " " + url + " created.");
            }
        }

        return createSuccessful;
    }


    public boolean checkIfExists(String objectName) {

        boolean recordExists = false;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + itemName + " FROM " + tableName + " WHERE " + itemName + " = '" + objectName + "'", null);

        if (cursor != null) {

            if (cursor.getCount() > 0) {
                recordExists = true;
            }
        }

        cursor.close();
//        db.close();

        return recordExists;
    }

    public ArrayList<String> getVideos(String searchTerm) {

        ArrayList<String> names = new ArrayList<>();
        // select query
        if (searchTerm.equals(""))
            return names;
        String sql = "";
        sql += "SELECT * FROM " + tableName;
        sql += " WHERE " + itemName + " LIKE '%" + searchTerm + "%'";
        sql += " ORDER BY " + fieldId + " ASC";
        sql += " LIMIT 0,5";

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String objectName = cursor.getString(cursor.getColumnIndex(itemName));
                Log.e("Database class", "objectName: " + objectName);

                names.add(cursor.getString(cursor.getColumnIndex(itemName)));
            } while (cursor.moveToNext());
        }

        cursor.close();
//        db.close();

        return names;

    }


    public String getVideoUrl(String videoName) {
        String sql = "";
        sql += "SELECT * FROM " + tableName;
        sql += " WHERE " + this.itemName + " LIKE '" + videoName + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        String names = "";
        if (cursor.moveToFirst()) {
            do {
                names = cursor.getString(cursor.getColumnIndex(itemPrice));
                Log.e("Database class", "objectName: " + names);

//                names = (cursor.getString(cursor.getColumnIndex(videoName)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return names;
    }

    public boolean storeCountry(String country) {

        boolean createSuccessful = false;

        /*if (!checkIfExists(country)) {*/

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(itemName, country);

        createSuccessful = db.insert(tableCountry, null, values) > 0;

        db.close();
        if (createSuccessful) {
            Log.e("Database", itemPrice + " created");
        }
//        }

        return createSuccessful;
    }


    public boolean addItemsToCart(String name, String price, String count) {

        boolean createSuccessful = false;
        SQLiteDatabase db = this.getWritableDatabase();
//        if (!checkIfExists(name)) {

        if (Integer.parseInt(count) != 0) {
            ContentValues values = new ContentValues();
            values.put(itemName, name);
            values.put(itemPrice, price);
            values.put(itemCount, count);
            createSuccessful = db.insert(tableName, null, values) > 0;
        } else {
            removeItemsFromCart(name);
        }

        if (createSuccessful) {
            Log.e("Database", name + " " + name + " created.");
        }
        /*} else {

            updateItems(name, price, count);
//            db.execSQL("UPDATE "+tableName+" SET "+itemCount+"='"+count+"' WHERE "+itemName+"="+name);
        }*/
//        db.close();
        return createSuccessful;
    }

    public void updateCartItems(String name, String price, String count) {
        if(Integer.parseInt(count) != 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(itemName, name);
            values.put(itemPrice, price);
            values.put(itemCount, count);
            db.update(tableName, values, /*itemName = name*/itemName + "='" + name + "'", null);
        } else {
            removeItemsFromCart(name);
        }
    }

    public void removeItemsFromCart(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + tableName + " where " + itemName + " ='" + name + "'");
    }

    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DROP TABLE IF EXISTS " + tableName;
        db.execSQL(sql);
    }

    public ArrayList<Cart> getAllCartData() {
        ArrayList<Cart> data = new ArrayList<>();
        String sql = "";
        sql += "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String objectName = cursor.getString(cursor.getColumnIndex(itemName));
                Log.e("Database class", "objectName: " + objectName);

                Cart cartData = new Cart();
                cartData.setItemName(cursor.getString(cursor.getColumnIndex(itemName)));
                cartData.setItemPrice(cursor.getString(cursor.getColumnIndex(itemPrice)));
                cartData.setItemCount(cursor.getString(cursor.getColumnIndex(itemCount)));

                data.add(cartData);
            } while (cursor.moveToNext());
        }

        cursor.close();
//        db.close();

        return data;
    }

    public int getAllCartAmount() {

        String sql = "";
        sql += "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        int amount = 0;
        if (cursor.moveToFirst()) {
            do {
                String objectName = cursor.getString(cursor.getColumnIndex(itemPrice));
                Log.e("Database class", "objectName: " + objectName);
                int price = Integer.parseInt(cursor.getString(cursor.getColumnIndex(itemPrice)));
                int quantity = Integer.parseInt(cursor.getString(cursor.getColumnIndex(itemCount)));
                amount = amount + (price*quantity);
            } while (cursor.moveToNext());
        }
        cursor.close();
//        db.close();

        return amount;
    }
}
