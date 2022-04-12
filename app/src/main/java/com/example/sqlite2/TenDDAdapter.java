package com.example.sqlite2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TenDDAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<TenDD> tenDDList;

    public TenDDAdapter(MainActivity context, int layout, List<TenDD> tenDDList) {
        this.context = context;
        this.layout = layout;
        this.tenDDList = tenDDList;
    }

    @Override
    public int getCount() {
        return tenDDList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvTen;
        ImageView imgSua, imgXoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.tvTen = (TextView)  view.findViewById(R.id.tvTen);
            holder.imgSua = (ImageView) view.findViewById(R.id.imgSua);
            holder.imgXoa = (ImageView) view.findViewById(R.id.imgXoa);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        final TenDD tenDD = tenDDList.get(i);
        holder.tvTen.setText(tenDD.getTenDD());

        holder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Sua", Toast.LENGTH_SHORT).show();
                context.DialogUpdate(tenDD.getTenDD(),tenDD.getId());

            }
        });

        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context, "Xoa", Toast.LENGTH_SHORT).show();
                context.DialogXoa(tenDD.getTenDD(),tenDD.getId());
            }
        });
        return  view;
    }
}
