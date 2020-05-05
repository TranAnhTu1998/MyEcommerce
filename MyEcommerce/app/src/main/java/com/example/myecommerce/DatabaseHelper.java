package com.example.myecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productsDB.db";
    public static final String TABLE_NAME = "ProductsTable";
    public static final String COLUMN_ID = "ProductsID";
    public static final String COLUMN_NAME = "ProductsName";
    public static final String COLUMN_PRICE = "ProductPrice";
    public static final String COLUMN_NUMBER_PRODUCTS_WAREHOUSE = "NumberProductsWarehouse";


    public  DatabaseHelper(Context context, String name_database, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " +  TABLE_NAME + " ( "
                + COLUMN_ID + " integer primary key autoincrement , "
                + COLUMN_NAME + " TEXT , "
                + COLUMN_PRICE + " FLOAT , "
                + COLUMN_NUMBER_PRODUCTS_WAREHOUSE + " INTEGER "
                +" ) " ;

        sqLiteDatabase.execSQL(CREATE_TABLE);

        /*for (int i = 0; i < 10; i++){
            sqLiteDatabase.execSQL("INSERT INTO mytable VALUES(?, ?)", new String[]{Integer.toString(i), Integer.toString(i)});
        }*/
    }


    public List<Product> loadMoreZeroHandler(){
        List listProducts = new ArrayList<Product>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NUMBER_PRODUCTS_WAREHOUSE + " > 0 ";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            float price = cursor.getFloat(2);
            int amount_in_warehouse = cursor.getInt(3);

            Product newProduct = new Product(id, name, price, amount_in_warehouse);

            listProducts.add(newProduct);
        }
        return  listProducts;
    }

    public List<Product> loadAllHandler(){
        List listProducts = new ArrayList<Product>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            float price = cursor.getFloat(2);
            int amount_in_warehouse = cursor.getInt(3);

            Product newProduct = new Product(id, name, price, amount_in_warehouse);

            listProducts.add(newProduct);
        }
        return  listProducts;
    }


    public void addHandler(Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_NUMBER_PRODUCTS_WAREHOUSE, product.getAmountInWarehouse());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME,null, values);
        sqLiteDatabase.close();
    }

    public boolean updateHandler(int ID, int number_products_warehouse){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_NUMBER_PRODUCTS_WAREHOUSE, number_products_warehouse);
        int iupdate = sqLiteDatabase.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null);
        System.out.println("DBHelder.iupdate = " + iupdate);
        boolean boo =  iupdate > 0;
        return boo;
}

    public boolean deleteHandler(int ID){
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.moveToFirst()){
            sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(ID)});
            cursor.close();
            result = true;
        }
        return result;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
