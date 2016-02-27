package com.yangtech.userdemo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangtech.userdemo.R;
import com.yangtech.userdemo.model.ImageGridItem;

import java.util.List;

/**
 * Created by apple on 16-02-25.
 */
public class ImageListAdapter extends BaseAdapter {

    private List<ImageGridItem> mImageGridItemsmageItems;
    private Context mContext;
    public ImageListAdapter(Context context, List<ImageGridItem> imageItems) {
        this.mContext = context;
        this.mImageGridItemsmageItems = imageItems;
    }

    @Override
    public int getCount() {
        return mImageGridItemsmageItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mImageGridItemsmageItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            //brand new list item
            convertView = LayoutInflater.from(mContext).inflate(R.layout.image_list_item, null);
            holder = new ViewHolder();
            holder.iconImage = (ImageView) convertView.findViewById(R.id.imageView);
            holder.imageLabel = (TextView) convertView.findViewById(R.id.imageTitle);

            convertView.setTag(holder);
        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageGridItem item = mImageGridItemsmageItems.get(i);

        holder.iconImage.setImageBitmap(item.getImage());
        holder.imageLabel.setText(item.getFilename());

        return convertView;
    }

    private static class ViewHolder {
        ImageView iconImage;
        TextView imageLabel;
    }
}
