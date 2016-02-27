package com.yangtech.userdemo.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by apple on 15-05-07.
 */
public class AlertMessageDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Network Connection Error")
                .setMessage("Please chech your netword")
                .setPositiveButton("Confirm", null);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
