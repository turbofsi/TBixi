package com.yangtech.userdemo;

import com.yangtech.userdemo.utils.FileUtilities;

/**
 * Created by apple on 16-02-25.
 */
public class UserDemoApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FileUtilities.saveAssetImage(this, "aquarium.jpg");
        FileUtilities.saveAssetImage(this, "artgalleryontario.jpg");
        FileUtilities.saveAssetImage(this, "cntower.jpg");
    }
}
