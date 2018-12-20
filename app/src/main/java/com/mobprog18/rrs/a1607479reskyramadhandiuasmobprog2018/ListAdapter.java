package com.mobprog18.rrs.a1607479reskyramadhandiuasmobprog2018;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CategoryViewHolder>{

    private Context context;
    ArrayList<Model> getListSensor() {
        return listSensor;
    }

    private ArrayList<Model> listSensor;
    ListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new CategoryViewHolder(itemRow);
    }
    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.tvStatus.setText(getListSensor().get(position).getStatus());
        holder.tvTimestamp.setText(getListSensor().get(position).getTimestamp());
    }
    @Override
    public int getItemCount() {
        return getListSensor().size();
    }

    public void setListSensor(ArrayList<Model> listSensor) {
        this.listSensor = listSensor;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView tvStatus;
        TextView tvTimestamp;
        CategoryViewHolder(View itemView) {
            super(itemView);
            tvStatus = (TextView)itemView.findViewById(R.id.tv_status);
            tvTimestamp = (TextView)itemView.findViewById(R.id.tv_timestamp);
        }
    }
}

