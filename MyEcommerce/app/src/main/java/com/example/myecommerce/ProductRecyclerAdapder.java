package com.example.myecommerce;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class ProductRecyclerAdapder extends RecyclerView.Adapter<ProductRecyclerAdapder.ProductViewHolder> {
    private List<Product> listProduct;
    private Context context;

    public ProductRecyclerAdapder(Context context, List<Product> listProduct){
        this.context = context;
        this.listProduct = listProduct;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page_store_front, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final int fPosition_ = position;
        final EditText  edtNumberOder = holder.edtNumberOder;
        final TextView ftvNumberInWareHose = holder.tvNumberInWareHose;
        holder.bntOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Editable edtabNumberOrder = edtNumberOder.getText();
                if(edtabNumberOrder.toString().isEmpty()){
                    showAlerDialogError("Number of orders  empty??.");
                }else {
                    int ID = listProduct.get(fPosition_).getId();
                    int number_order = Integer.parseInt(edtabNumberOrder.toString());
                    int number_products_warehouse = Integer.parseInt(ftvNumberInWareHose.getText().toString());
                    int number_products_warehouse_update = number_products_warehouse - number_order;
                    boolean boole = MainActivity.databaseHelper.updateHandler(ID, number_products_warehouse_update);
                    if(number_products_warehouse_update < 0){
                        //update;
                        showAlerDialogError("You cannot place" + listProduct.get(fPosition_).getName() + "products beyond the specified quantity. !!");
                    }else if(!boole){
                        showAlerDialogError("Could not update data !!!");
                    }else {
                        ftvNumberInWareHose.setText(Integer.toString(number_products_warehouse_update));
                        edtNumberOder.setText("");
                        Toast.makeText(context,"Order Success!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //holder.textViewTitle.setText("item " + String.format("%d", position));
        holder.tvNameProduct.setText(listProduct.get(position).getName());
        holder.tvPrice.setText(String.format("%.2f", listProduct.get(position).getPrice()));
        holder.tvNumberInWareHose.setText(String.format("%d", listProduct.get(position).getAmountInWarehouse()));
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        Button bntOrder;
        //TextView tvID;
        TextView tvNameProduct;
        TextView tvPrice;
        TextView tvNumberInWareHose;
        EditText edtNumberOder;

        //TextView textViewTitle;
        public ProductViewHolder(View view){
            super(view);

            //textViewTitle = itemView.findViewById(R.id.textView);

            bntOrder = view.findViewById(R.id.id_button_order);
            edtNumberOder = view.findViewById(R.id.id_number_order_front);


            tvNameProduct = view.findViewById(R.id.id_name_product_front);
            tvPrice = view.findViewById(R.id.id_price_product_front);
            tvNumberInWareHose = view.findViewById(R.id.id_number_products_warehouse_front);
        }


    }
    private void showAlerDialogError(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyDialogTheme);
        builder.setTitle("There was an error !!");
        builder.setMessage(error);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
