package com.dream.main.tabme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.DreamUtils;
import com.dream.util.SharedPreferencesUtils;
import com.dream.util.ToastUtil;
import com.dream.util.UplodUtil;
import com.dream.views.imageview.DreamImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/29 00:13
 * 用户信息
 */
public class UserInfoAct extends BaseActivity implements BaseActView {


    @Bind(R.id.img_hand)
    DreamImageView layoutItemUser;//头像


    private String[] items = new String[]{"选择本地图片", "拍照"};

    /* 头像名称 */
    private static final String IMAGE_FILE_NAME = "faceImage.jpg";

    /* 请求码 */
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;

    UserInfoPM userInfoPM;

    @Override
    public void setOnClickView(View view) {


        switch (view.getId()) {

            case R.id.img_hand:
                showDialog();
                break;
            case R.id.layoutItem_username:
                showEditDialog(1);
                break;
            case R.id.layoutItem_signature:
                showEditDialog(2);
                break;
            case R.id.layoutItem_email:
                showEditDialog(3);
                break;
            case R.id.layoutItem_phone:
                showEditDialog(4);
                break;

        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public Object initPM() {
        userInfoPM = new UserInfoPM(this);
        return userInfoPM;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }

    /**
     * 显示选择对话框
     */
    private void showDialog() {

        new AlertDialog.Builder(this)
                .setTitle("设置头像")
                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intentFromGallery = new Intent();
                                intentFromGallery.setType("image/*"); // 设置文件类型
                                intentFromGallery
                                        .setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intentFromGallery,
                                        IMAGE_REQUEST_CODE);
                                break;
                            case 1:
                                Intent intentFromCapture = new Intent(
                                        MediaStore.ACTION_IMAGE_CAPTURE);
                                // 判断存储卡是否可以用，可用进行存储
//                                if (Tools.hasSdcard()) {
                                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                                File file = new File(path, IMAGE_FILE_NAME);
                                intentFromCapture.putExtra(
                                        MediaStore.EXTRA_OUTPUT,
                                        Uri.fromFile(file));
//                                }

                                startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
                                break;
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //结果码不等于取消时候
        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    if (DreamUtils.hasSDCard()) {
                        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        File tempFile = new File(path, IMAGE_FILE_NAME);
                        startPhotoZoom(Uri.fromFile(tempFile));
                    } else {
                        ToastUtil.show("未找到存储卡，无法存储照片！");
                    }
                    break;
                case RESULT_REQUEST_CODE: //图片缩放完成后
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 340);
        intent.putExtra("outputY", 340);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            savePic(photo);
        }
    }

    private void savePic(Bitmap photo) {
        long l2 = System.currentTimeMillis();
        String fileName = l2 + ".jpg";
        String tempImgPath = getCacheDir().getAbsolutePath() + "/sysfiles/temp/" + fileName;
        String dir = getDir(tempImgPath);
        File dirFile = new File(dir);
        dirFile.mkdirs();
        if (!dirFile.exists()) {
            ToastUtil.show("无法创建SD卡目录,图片无法保存");
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempImgPath));
            photo.compress(Bitmap.CompressFormat.JPEG, 75, bos);// (0 - 100)压缩文件
            SharedPreferencesUtils.setSharedPreference(this, tempImgPath);

            new Thread() {
                @Override
                public void run() {
                    try {
                        UplodUtil uplodUtil = new UplodUtil();
                        uplodUtil.upload(ProtocolUrl.USER_PHOTO, tempImgPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDir(String filePath) {
        int lastSlastPos = filePath.lastIndexOf('/');
        return filePath.substring(0, lastSlastPos);
    }


    private void showEditDialog(int type) {

        EditText editText = new EditText(this);

        switch (type){
            case 1:
                editText.setText(userInfoPM.getName());
                break;
            case 2:
                editText.setText(userInfoPM.getSignature());
                break;
            case 3:
                editText.setText(userInfoPM.getEmail());
                break;
            case 4:
                editText.setText(userInfoPM.getPhone());
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改信息")
                .setView(editText)
                .setCancelable(false)
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("修改", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(!DreamUtils.isEmpty(editText.getText().toString())){
                            switch (type){
                                case 1:
                                    DreamApplication.getApp().getUser().setUsername(editText.getText().toString());
                                    break;
                                case 2:
                                    DreamApplication.getApp().getUser().setQianming(editText.getText().toString());
                                    break;
                                case 3:
                                    DreamApplication.getApp().getUser().setEmail(editText.getText().toString());
                                    break;
                                case 4:
                                    DreamApplication.getApp().getUser().setMobile(editText.getText().toString());
                                    break;
                            }
                            userInfoPM.updataUserInfo(null);
                            dialog.cancel();
                        }
                    }
                });


        builder.show();

    }
}
