package com.example.myecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static  DatabaseHelper databaseHelper;
    private Button bntStoreFront;
    private Button bntStoreBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bntStoreFront = this.findViewById(R.id.id_button_store_front);
        bntStoreFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStoreFront = new Intent(MainActivity.this, StoreFrontActivity.class);
                startActivity(intentStoreFront);
            }
        });

        bntStoreBack = this.findViewById(R.id.id_button_store_back);
        bntStoreBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStoreBlack = new Intent(MainActivity.this, StoreBackActivity.class);
                startActivity(intentStoreBlack);
            }
        });

        databaseHelper = new DatabaseHelper(this, null, null, 1);
        //SQLiteDatabase  sqLiteDatabase = databaseHelper.getReadableDatabase();
        //Cursor cursor = sqLiteDatabase.query("mytable", null, null, null, null, null, null);
        //System.out.println("main.corsor == null ???" + (cursor == null));


    }
}
