package com.example.myecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


import java.util.List;

public class StoreFrontActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private ViewPager2 myViewPager;
    private List<Product> listProduct;
    private RelativeLayout emptyFront;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_front);

        Toolbar toolbar = findViewById(R.id.id_toolbar_front);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back);

        myViewPager = findViewById(R.id.id_pager);
        emptyFront = findViewById(R.id.id_empty_front);
        listProduct = MainActivity.databaseHelper.loadMoreZeroHandler();
        if (listProduct.isEmpty()){
            emptyFront.setVisibility(View.VISIBLE);
        }
        else {
            emptyFront.setVisibility(View.GONE);
        }

        System.out.println("main.listProduct == null??: " + listProduct.isEmpty());
        adapter = new ProductRecyclerAdapder(this, listProduct);
        //adapter = new MyPagerAdapter();
        myViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //CursorRecyclerAdapter adapter1 = new MyPagerCursorAdapter(cursor, databaseHelper);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
