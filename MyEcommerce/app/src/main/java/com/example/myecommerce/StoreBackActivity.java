package com.example.myecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

public class StoreBackActivity extends AppCompatActivity {
    Button btnAddProduct;
    EditText edtNameProduct_Input;
    EditText edtPrice_Input;
    EditText edtNumberStock_Input;
    RelativeLayout emptyBack;
    List<Product> listProduct;
    RecyclerView recyclerViewProduct;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    CheckBox cbDelete;
    EditText edtIdDelete;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_back);

        Toolbar toolbar = findViewById(R.id.id_toolbar_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back);

        emptyBack = findViewById(R.id.id_empty_products_back);
        recyclerViewProduct =findViewById(R.id.id_recyclerview_back);

        loadList();

        addproducts();
        
        setDeleleButton();
    }

    private void addproducts() {
        btnAddProduct = findViewById(R.id.id_button_add_black);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                String error = null;
                StringBuilder stringBuilder = new StringBuilder();
                edtNameProduct_Input = findViewById(R.id.id_name_product_black);
                edtPrice_Input = findViewById(R.id.id_price_black);
                edtNumberStock_Input = findViewById(R.id.id_quantity_goods_stock_black);

                Editable edtablName = edtNameProduct_Input.getText();
                if(edtablName.toString().isEmpty()){
                    error = stringBuilder.append(" *Product is name").toString();
                }


                Editable edtabPrice =  edtPrice_Input.getText();
                if(edtabPrice.toString().isEmpty()){
                    error = stringBuilder.append(" *Price").toString();
                }


                Editable edtabNumberStock = edtNumberStock_Input.getText();
                if(edtabNumberStock.toString().isEmpty()){
                    error = stringBuilder.append(" *Quantity of goods stock").toString();
                }

                if (error != null){
                    showAlerDialogError(error);
                }else{
                    String name = edtNameProduct_Input.getText().toString();
                    float price = Float.parseFloat(edtPrice_Input.getText().toString());
                    int number_stock = Integer.parseInt(edtNumberStock_Input.getText().toString());
                    //
                    Product newProduct = new Product(name, price, number_stock);
                    //
                    MainActivity.databaseHelper.addHandler(newProduct);
                    //
                    loadList();
                    //
                    edtNameProduct_Input.setText("");
                    edtPrice_Input.setText("");
                    edtNumberStock_Input.setText("");
                    //
                    Toast.makeText(StoreBackActivity.this, "Added" + name + "products to the list", Toast.LENGTH_SHORT).show();
                    //
                    System.out.println("StoreBackActivity.Product : " + newProduct.getPrice());
                }
            }
        });
    }

    private void loadList() {
        listProduct = MainActivity.databaseHelper.loadAllHandler();
        if (listProduct.isEmpty()){
            emptyBack.setVisibility(View.VISIBLE);
        }
        else {
            emptyBack.setVisibility(View.GONE);
        }

        adapter = new ProductRecyclerBackAdapter(this, listProduct);
        layoutManager = new LinearLayoutManager(StoreBackActivity.this);
        recyclerViewProduct.setLayoutManager(layoutManager);
        recyclerViewProduct.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setDeleleButton(){
        cbDelete = findViewById(R.id.id_checkbox_delete);

        edtIdDelete = findViewById(R.id.id_edittext_delete);


        btnDelete = findViewById(R.id.id_button_delete);


        btnDelete.setVisibility(View.GONE);
        edtIdDelete.setVisibility(View.GONE);
        cbDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbDelete.isChecked()){
                    btnDelete.setVisibility(View.VISIBLE);
                    edtIdDelete.setVisibility(View.VISIBLE);
                }else{
                    btnDelete.setVisibility(View.GONE);
                    edtIdDelete.setVisibility(View.GONE);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtIdDelete.getText().toString().isEmpty()){
                    showAlerDialogError("The id of the element to delete is empty ??");
                }else{
                    int id = Integer.parseInt(edtIdDelete.getText().toString());
                    boolean boo = MainActivity.databaseHelper.deleteHandler(id);
                    if(boo){
                        Toast.makeText(getApplicationContext(),  "Product has been deleted successfully!!!", Toast.LENGTH_SHORT).show();
                        edtIdDelete.setText("");
                        loadList();
                    }else{
                        showAlerDialogError("Do not find a product teacher whose ID = " + String.valueOf(id));
                    }

                }
            }
        });

    }

    private void showAlerDialogError(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(StoreBackActivity.this, R.style.MyDialogTheme);
        builder.setTitle("There was an error !!");
        builder.setMessage(error + " must not be blank.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
