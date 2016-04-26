package com.example.liangwenchao.appdemo.ui.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;

import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 2016/4/18.
 */
public class CameraFragment extends BaseFragment implements View.OnClickListener {

    private static final int TAKE_PHOTO = 101;
    private static final int CROP_PHOTO = 102;
    private static final int REQUEST_CAMERA = 201;
    private Button openCameraBtn;
    private Button choosePicBtn;
    private ImageView mPicIV;

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_camera, null);
    }

    @Override
    public void initView(View view) {
        openCameraBtn = (Button) view.findViewById(R.id.btn_open_camera);
        mPicIV = (ImageView) view.findViewById(R.id.iv_pic);
        choosePicBtn = (Button) view.findViewById(R.id.btn_choose_pic);
        openCameraBtn.setOnClickListener(this);
        choosePicBtn.setOnClickListener(this);
    }



    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_camera:
                checkPermission();
                break;
            case  R.id.btn_choose_pic:
                choosePic();
                break;
        }
    }



    private Uri imageUri;

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {//判断摄像头权限是否被准许
            Log.i("lwc","请求授权");
            requestCameraPermission();//请求权限
        } else {

            Log.i("lwc","打开摄像头");
            openCamera();
        }

    }

    private void openCamera() {
        File outputImage = new File(Environment.getExternalStorageDirectory(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//android.media.action.IMAGE_CAPTURE
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//设置之后data为null
        startActivityForResult(intent, TAKE_PHOTO);
    }

    private void choosePic() {
        File outputImage = new File(Environment.getExternalStorageDirectory(),"output_image.jpg");

        try {

            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);

        intent.setType("image/*");
        intent.putExtra("crop", true);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        if(intent.resolveActivity(getActivity().getPackageManager())!=null){
            startActivityForResult(intent,CROP_PHOTO);
            Log.i("lwc","选择照片 uri Path = " + imageUri.getPath());

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                Log.i("lwc", "成功摄像");
                if (resultCode == Activity.RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivityForResult(intent, CROP_PHOTO);
                    }
                }
                break;
            case CROP_PHOTO:

                if (resultCode == Activity.RESULT_OK) {

                    Log.i("lwc", "剪裁图片完成");
                    try {
                        Bitmap bit = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
//                        Bitmap bit = BitmapFactory.decodeFile(imageUri.getPath());
                        mPicIV.setImageBitmap(bit);

                        Log.i("lwc","imageUri.getPath() = " + imageUri.getPath() + " bit = " + bit);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private Uri uritempFile;


    /**
     * 裁剪图片
     */
    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);

        /**
         * 此方法返回的图片只能是小图片（sumsang测试为高宽160px的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
        //intent.putExtra("return-data", true);

        //uritempFile为Uri类变量，实例化uritempFile
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, CROP_PHOTO);
    }


    private void requestCameraPermission() {

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CAMERA);
    }
}
