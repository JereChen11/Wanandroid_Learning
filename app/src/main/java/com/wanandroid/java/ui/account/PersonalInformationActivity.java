package com.wanandroid.java.ui.account;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.permissionx.guolindev.PermissionX;
import com.wanandroid.java.R;
import com.wanandroid.java.databinding.ActivityPersonalInfomationBinding;
import com.wanandroid.java.util.Settings;

import java.io.File;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityPersonalInfomationBinding mBinding;
    private AlertDialog selectedPictureDialog;
    private static final int TAKE_PHOTO_REQUEST_CODE = 1111;
    private static final int FROM_ALBUM_REQUEST_CODE = 2222;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_infomation);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_personal_infomation);

        initClickEvent();
        initSelectedPictureDialog();
    }

    private void initClickEvent() {
        mBinding.personalInfoNameItem.setOnClickListener(this);
        mBinding.personalInfoAvatarItem.setOnClickListener(this);
        mBinding.personalInfoMyAddressItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personalInfoNameItem:
                Intent setNameIntent = new Intent(this, SetNameActivity.class);
                startActivity(setNameIntent);
                break;
            case R.id.personalInfoAvatarItem:
                requestPermissions();
                break;
            case R.id.personalInfoMyAddressItem:
                //todo
                break;
            case R.id.cameraTv:
                startCameraIntent();
                selectedPictureDialog.dismiss();
                break;
            case R.id.albumTv:
                startAlbumIntent();
                selectedPictureDialog.dismiss();
                break;
            case R.id.cancelTv:
                selectedPictureDialog.dismiss();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.personalInfoNameItem.setContentText(Settings.getInstance().getUserName());
        if (!TextUtils.isEmpty(Settings.getInstance().getAvatarUrl())) {
            setAvatar(Settings.getInstance().getAvatarUrl());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO_REQUEST_CODE:
                    if (!TextUtils.isEmpty(imageUri.toString())) {
                        setAvatar(imageUri.toString());
                        Settings.getInstance().setAvatarUrl(imageUri.toString());
                    }
                    break;
                case FROM_ALBUM_REQUEST_CODE:
                    if (data != null && data.getData() != null) {
                        Uri uri = data.getData();
                        if (uri != null && !TextUtils.isEmpty(uri.toString())) {
                            setAvatar(uri.toString());
                            Settings.getInstance().setAvatarUrl(uri.toString());
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void setAvatar(String avatarUriString) {
        mBinding.personalInfoAvatarItem.setCircleAvatar(avatarUriString);
    }

    private void requestPermissions() {
        PermissionX.init(this)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onExplainRequestReason((scope, deniedList) -> scope.showRequestReasonDialog(deniedList,
                        "该功能依赖于该权限，请允许",
                        getString(R.string.confirm_cn),
                        getString(R.string.cancel_cn)))
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        selectedPictureDialog.show();
                        changeDialogSize();
                    } else {
                        Toast.makeText(this, "这些权限被拒绝: " + deniedList, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initSelectedPictureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialogTheme);
        View view = getLayoutInflater().inflate(R.layout.select_picture_dialog_custom_view_ll, null);
        builder.setView(view);
        selectedPictureDialog = builder.create();

        TextView cameraTv = view.findViewById(R.id.cameraTv);
        TextView albumTv = view.findViewById(R.id.albumTv);
        TextView cancelTv = view.findViewById(R.id.cancelTv);
        cameraTv.setOnClickListener(this);
        albumTv.setOnClickListener(this);
        cancelTv.setOnClickListener(this);
    }

    private void changeDialogSize() {
        Window window = selectedPictureDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }

    private void startAlbumIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, FROM_ALBUM_REQUEST_CODE);
    }

    private void startCameraIntent() {
        File outputImageFile = new File(getExternalCacheDir(), "output_image.jpg");
        if (outputImageFile.exists()) {
            outputImageFile.delete();
        }
        try {
            outputImageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(this, "com.wanandroid.java.fileprovider", outputImageFile);
        } else {
            imageUri = Uri.fromFile(outputImageFile);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);
    }


}
