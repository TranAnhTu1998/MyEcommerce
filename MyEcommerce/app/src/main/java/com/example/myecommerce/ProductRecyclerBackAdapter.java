package com.example.myecommerce;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class ProductRecyclerBackAdapter extends RecyclerView.Adapter<ProductRecyclerBackAdapter.ProducBacktViewHolder> {
    private List<Product> listProduct;
    private Context context;

    public ProductRecyclerBackAdapter(Context context, List<Product> listProduct){
        this.context = context;
        this.listProduct = listProduct;
    }


    @NonNull
    @Override
    public ProducBacktViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_store_back, parent, false);
        return new ProducBacktViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProducBacktViewHolder holder, int position) {
        if(position % 2 == 0){
            holder.tvID.setBackgroundColor(Color.GREEN);
        }else{
            holder.tvID.setBackgroundColor(Color.WHITE);
        }
        holder.tvID.setText("ID : " + String.format("%d", listProduct.get(position).getId()) + " | ");
        holder.tvNameProduct.setText(listProduct.get(position).getName() + " | ");
        holder.tvPrice.setText(String.format("%.2f", listProduct.get(position).getPrice()) + " | ");
        holder.tvNumberInWareHose.setText(String.format("%d", listProduct.get(position).getAmountInWarehouse()));
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ProducBacktViewHolder extends RecyclerView.ViewHolder {
        TextView tvID;
        TextView tvNameProduct;
        TextView tvPrice;
        TextView tvNumberInWareHose;


        //TextView textViewTitle;
        public ProducBacktViewHolder(View view){
            super(view);
            tvID = view.findViewById(R.id.id_item_back);
            tvNameProduct = view.findViewById(R.id.id_name_item_back);
            tvPrice = view.findViewById(R.id.id_price_item_back);
            tvNumberInWareHose = view.findViewById(R.id.id_number_in_warehouse_item_black);
        }
        private void showAlerDialogError(String error) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyDialogTheme);
            builder.setTitle("There was an error !!");
            builder.setMessage(error + " empty??.");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
