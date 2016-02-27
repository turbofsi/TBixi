package com.yangtech.userdemo.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangtech.userdemo.Adapters.ImageListAdapter;
import com.yangtech.userdemo.R;
import com.yangtech.userdemo.model.ImageGridItem;
import com.yangtech.userdemo.utils.FileUtilities;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by apple on 16-02-25.
 */
public class ImageListFragment extends ListFragment {
    private ImageListAdapter mImageListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image_list, container, false);
        mImageListAdapter = new ImageListAdapter(this.getActivity(), extractFiles());
        setListAdapter(mImageListAdapter);
        return rootView;
    }

    private ArrayList extractFiles() {
        final ArrayList imageItems = new ArrayList();
        File[] filteredFiles = FileUtilities.listFiles(this.getActivity());
        for (File filteredFile : filteredFiles) {
            Bitmap bitmap = BitmapFactory.decodeFile(filteredFile.getAbsolutePath());
            ImageGridItem item = new ImageGridItem(bitmap, filteredFile.getName(), filteredFile.getAbsolutePath());
            imageItems.add(item);
        }
        return imageItems;
    }
}
