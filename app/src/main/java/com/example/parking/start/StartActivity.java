package com.example.parking.start;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.parking.R;
import com.example.parking.base.BaseActivity;
import com.example.parking.main.MainActivity;
import com.example.parking.utils.Utils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.List;

public class StartActivity extends BaseActivity {
    public static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    public void init() {
        setContentView(R.layout.activity_start);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    initPermission();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void initPermission() {
        // 先判断是否有权限。
        if (AndPermission.hasPermission(this, Utils.getPermission(0),
                Utils.getPermission(1))) {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            AndPermission.with(this)
                    .requestCode(PERMISSION_REQUEST_CODE)
                    .permission(Utils.getPermission(0), Utils.getPermission(1))
                    .send();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionYes(PERMISSION_REQUEST_CODE)
    private void getLocationYes(List<String> grantedPermissions) {
        // TODO 申请权限成功。
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @PermissionNo(PERMISSION_REQUEST_CODE)
    private void getLocationNo(List<String> deniedPermissions) {
        AndPermission.defaultSettingDialog(this, PERMISSION_REQUEST_CODE).show();
    }
}
