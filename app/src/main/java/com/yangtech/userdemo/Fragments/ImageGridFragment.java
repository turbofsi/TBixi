package com.yangtech.userdemo.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.yangtech.userdemo.Adapters.GridViewAdapter;
import com.yangtech.userdemo.R;
import com.yangtech.userdemo.model.ImageGridItem;
import com.yangtech.userdemo.utils.FileUtilities;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by apple on 16-02-25.
 */
public class ImageGridFragment extends Fragment {
    private GridView mGridView;
    private GridViewAdapter mGridAdapter;
    public static int RESULT_LOAD_IMAGE = 1000;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image_grid, container, false);
        mGridView = (GridView) rootView.findViewById(R.id.gridView);
        mGridAdapter = new GridViewAdapter(this.getActivity(), R.layout.view_grid, extractFiles());
        mGridView.setAdapter(mGridAdapter);
        mGridView.setOnItemClickListener(mOnItemClickListener);
        mGridView.setOnItemLongClickListener(mOnItemLongClickListener);
        this.setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
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

    private void resetGridAdapter() {
        mGridAdapter = new GridViewAdapter(this.getActivity(), R.layout.view_grid, extractFiles());
        mGridView.setAdapter(mGridAdapter);
    }

    protected AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    };

    protected AdapterView.OnItemLongClickListener mOnItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            return true;
        }
    };
}
