package com.an.howtowear.support.utils;

import android.content.res.Resources;

import com.an.howtowear.HTWApp;
import com.an.howtowear.R;

public class UIUtil {

    public static int getResIdValue(String resName) {

        Resources res = HTWApp.getContext().getResources();
        int resId = res.getIdentifier(resName, "drawable", HTWApp.getContext().getPackageName());

        if (resId == 0) {
            return R.drawable.w_01d;
        } else
            return resId;
    }
}
