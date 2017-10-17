package com.gameloft.pc.quanlythoigian.classPackage;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.gameloft.pc.quanlythoigian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOAN on 9/20/2017.
 */

public class CustomAdapter extends ArrayAdapter<monHoc> {
    private Context context;
    private int resource;
    private ArrayList<monHoc> arrMonHoc;
    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<monHoc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrMonHoc = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.dong_listview,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvMonHoc = (TextView) convertView.findViewById(R.id.tvMonHoc);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            viewHolder.tvPhong = (TextView) convertView.findViewById(R.id.tvPhong);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        monHoc monHoc = arrMonHoc.get(position);
        viewHolder.tvMonHoc.setText(" "+monHoc.getTenMonHoc());
        viewHolder.tvTime.setText(" "+monHoc.getThoiGian());
        viewHolder.tvPhong.setText(" "+monHoc.getPhong());

        return convertView;
    }

    public  class ViewHolder{
        TextView tvMonHoc, tvTime, tvPhong;
    }
}
