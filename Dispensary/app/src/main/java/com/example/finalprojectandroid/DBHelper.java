package com.example.finalprojectandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.finalprojectandroid.Models.OrderModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper  {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION  = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // data table to store order details
        sqLiteDatabase.execSQL(
                "create table orders " +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                        "quantity int," +
                        "description text," +
                        "medicineName text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        sqLiteDatabase.execSQL("DROP table if exists reg_table");
        onCreate(sqLiteDatabase);
    }

    // insert order details into database
    public boolean insertOrder(String name, String phone, int price, int image, String desc, String medicineName,int quantity){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        id = 0
        name = 1
        phone 2
        price 3
        image 4
        quantity 5
        description 6
        medicineName 7
         */
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",medicineName); // swap b/w desc and medicine name
        values.put("medicineName",desc);
        values.put("quantity",quantity);
        long id = database.insert("orders",null,values);
        if (id <= 0){
            return false;
        } else {
            return true;
        }
    }

    // method to get order list from database to show on orders activity
    public ArrayList<OrderModel> getOrders(){
        ArrayList<OrderModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select id,medicineName,image,price from orders",null);
        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                OrderModel model = new OrderModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)+"",cursor.getInt(3)+"");
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id = "+id,null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public boolean updateOrder(String name, String phone, int price, int image, String desc, String medicineName,int quantity,int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
         /*
        id = 0
        name = 1
        phone 2
        price 3
        image 4
        quantity 5
        description 6
        medicineName 7
         */
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("medicineName",desc);
        values.put("description",medicineName); // swap b/w desc and medicine name
        long row = database.update("orders",values,"id="+id,null);
        if (row <= 0){
            return false;
        } else {
            return true;
        }
    }

    public int deleteOrder(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }

}
