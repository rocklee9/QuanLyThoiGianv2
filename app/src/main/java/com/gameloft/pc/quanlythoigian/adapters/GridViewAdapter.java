package com.gameloft.pc.quanlythoigian.adapters;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.content.FileProvider;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gameloft.pc.quanlythoigian.R;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by HOAN on 11/28/2017.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> arrImage;
    private int resource;

    public GridViewAdapter(Context c){
        this.mContext = c;
    }

    public GridViewAdapter(Context mContext, List<String> arrImage, int resource) {
        this.mContext = mContext;
        this.arrImage = arrImage;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return arrImage == null?0:arrImage.size();
    }

    @Override
    public Object getItem(int position) {
        return arrImage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(mContext).inflate(R.layout.dong_gridview, parent, false);
            imageView = (ImageView) convertView.findViewById(R.id.image_item);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            convertView.setTag(imageView);
        } else {
            imageView = (ImageView) convertView.getTag();
        }
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.parse(arrImage.get(position)));
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
            //decodeBase64AndSetImage(arrImage[position],imageView);
            //   viewHolder.imageView.setBackgroundResource(R.drawable.icons8_right);

        }

            return convertView;
    }

}
