package com.xiyou.advance.modulespublic.common.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressUtil {
    static ProgressDialog progressDialog;
    /*
     * 提示加载
     */
    public static void showProgressDialog(String title, String message, Context context) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(context, title,
                    message, true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }

    /*
     * 隐藏提示加载
     */
    public static void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
