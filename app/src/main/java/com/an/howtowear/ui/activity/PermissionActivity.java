package com.an.howtowear.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.an.howtowear.R;
import com.an.howtowear.ui.HTWToast;
import com.an.howtowear.support.utils.HTWLog;
import com.an.howtowear.support.utils.PermissionUtil;

import java.util.ArrayList;

public class PermissionActivity extends Activity implements View.OnClickListener {

    private ArrayList<String> requestPermissionList;
    private Button btn_cancel;
    private Button btn_ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        requestPermissionList = new ArrayList<>();
        requestPermissionList = getIntent().getStringArrayListExtra("requestPermissionList");

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_ok = (Button) findViewById(R.id.btn_ok);

        btn_cancel.setOnClickListener(this);
        btn_ok.setOnClickListener(this);

        HTWLog.i(requestPermissionList.isEmpty() ? "0" : requestPermissionList.toString());

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                HTWToast.getInstance().showToast(getString(R.string.toast_app_finish));
                finish();
                break;

            case R.id.btn_ok:
                PermissionUtil.requestPermission(requestPermissionList, this);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PermissionUtil.CODE_REQEUST_PERMISSION:

                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            HTWToast.getInstance().showToast(getString(R.string.toast_app_finish));
                            finish();
                            return;
                        }
                    }

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    HTWToast.getInstance().showToast(getString(R.string.toast_app_finish));
                    finish();
                }
                break;
        }
    }
}
