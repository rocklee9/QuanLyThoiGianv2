package com.gameloft.pc.quanlythoigian.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.models.MonHoc;

import java.util.List;

/**
 * Created by HOAN on 9/20/2017.
 */

public class CustomAdapter extends ArrayAdapter<MonHoc> {
    private Context context;
    private int resource;
    private List<MonHoc> arrMonHoc;
    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MonHoc> objects) {
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
            viewHolder.lnItem = (LinearLayout) convertView.findViewById(R.id.lnItem);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MonHoc monHoc = arrMonHoc.get(position);
        viewHolder.tvMonHoc.setText(" "+monHoc.getTenMonHoc());
        viewHolder.tvTime.setText(" "+monHoc.getThoiGian1()+" - "+monHoc.getThoiGian2());
        viewHolder.tvPhong.setText(" "+monHoc.getPhong());

        if(monHoc.isWarning()) viewHolder.lnItem.setBackgroundResource(R.drawable.vientron2);
            else viewHolder.lnItem.setBackgroundResource(R.drawable.vientron);
        return convertView;
    }

    public  class ViewHolder{
        TextView tvMonHoc, tvTime, tvPhong;
        LinearLayout lnItem;
    }
}
