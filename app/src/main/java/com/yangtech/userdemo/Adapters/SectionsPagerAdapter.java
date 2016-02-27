package com.yangtech.userdemo.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yangtech.userdemo.Fragments.ImageGridFragment;
import com.yangtech.userdemo.Fragments.ImageListFragment;
import com.yangtech.userdemo.R;

/**
 * Created by apple on 16-02-25.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    Context mContext;
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if(position == 0) {
            fragment = new ImageGridFragment();
        } else {
            fragment = new ImageListFragment();
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.images_grid_title);
        } else {
            return mContext.getString(R.string.images_list_title);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
