package activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.suishouji.R;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class EditActivity extends Activity {


    private ImageView IvBack;
    private EditText EdEdit;

    private ImageButton IBtnSpot,IBtnNumber,IBtnLeft,IBtnRight,IBtnPicture,IBtnWord;

    private LinearLayout HideWord,HidePicture;
    private RelativeLayout addPicture,addCamera;
    private ImageView IvAdd;
    private static final int CAMERA_IMAGE_CODE = 1;// 拍照
    private static final int LOCAL_IMAGE_CODE = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    private boolean isVisbile = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();
    }

    //获取界面控件
    private void getView() {
        IvBack = (ImageView) findViewById(R.id.Iv_activity_edit_back);
        IvAdd = (ImageView)findViewById(R.id.Iv_activity_add);
        EdEdit = (EditText) findViewById(R.id.Ed_activity_edit_content);

        IBtnSpot = (ImageButton) findViewById(R.id.Ibtn_activity_edit_spot);
        IBtnNumber = (ImageButton) findViewById(R.id.Ibtn_activity_edit_number);
        IBtnLeft = (ImageButton) findViewById(R.id.Ibtn_activity_edit_left);
        IBtnRight = (ImageButton) findViewById(R.id.Ibtn_activity_edit_right);
        IBtnPicture = (ImageButton) findViewById(R.id.Ibtn_activity_edit_picture);
        IBtnWord = (ImageButton) findViewById(R.id.Ibtn_activity_edit_word);

        HideWord = (LinearLayout) findViewById(R.id.Llyout_activity_edit_hideword);
        HidePicture = (LinearLayout) findViewById(R.id.Llyout_activity_edit_hidepicture);
        addPicture = (RelativeLayout) findViewById(R.id.activity_edit_picture);
        addCamera = (RelativeLayout) findViewById(R.id.activity_edit_camera);
    }


    //注册监听事件
    private void setListener() {
        EditActivity.MyListener listener = new EditActivity.MyListener();
        IvBack.setOnClickListener(listener);

        IBtnSpot.setOnClickListener(listener);
        IBtnNumber.setOnClickListener(listener);
        IBtnLeft.setOnClickListener(listener);
        IBtnRight.setOnClickListener(listener);
        IBtnPicture.setOnClickListener(listener);
        IBtnWord.setOnClickListener(listener);
        addPicture.setOnClickListener(listener);
        addCamera.setOnClickListener(listener);
        IvAdd.setOnClickListener(listener);
    }
//    public static boolean saveImage(Bitmap photo, String spath) {
//        try {
//            BufferedOutputStream bos = new BufferedOutputStream(
//                    new FileOutputStream(spath, false));
//            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//            bos.flush();
//            bos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
    //剪切图片
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
         intent.putExtra("outputFormat", "JPEG");// 图片格式
         intent.putExtra("noFaceDetection", true);// 取消人脸识别
         intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
}
    //获取相册和相机
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        ContentResolver resolver = getContentResolver();
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 表示 调用照相机拍照
            case CAMERA_IMAGE_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    IvAdd.setImageBitmap(bitmap);
                }
                break;
            // 选择图片库的图片
            case LOCAL_IMAGE_CODE:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // 得到图片的全路径
                        Uri uri = data.getData();
                        crop(uri);
                    }
                }
                break;
            case PHOTO_REQUEST_CUT:
                // 从剪切图片返回的数据
                if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                IvAdd.setImageBitmap(bitmap);
                }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /**
     * 点击监听事件
     */
    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Iv_activity_edit_back:         //返回
                    Intent intent = new Intent();
                    intent.setClass(getApplication(),EditHomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.Ibtn_activity_edit_spot:       //无数字列项
                    break;
                case R.id.Ibtn_activity_edit_number:   //有数字列项
                    break;
                case R.id.Ibtn_activity_edit_left:     //左对齐
                    break;
                case R.id.Ibtn_activity_edit_right:    //右对齐
                    break;
                case R.id.Ibtn_activity_edit_picture:    //图片
                    if (isVisbile) {
                        //隐藏软键盘
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(EdEdit.getWindowToken(),0);
                        isVisbile = false;
                        HidePicture.setVisibility(View.VISIBLE);//显示布局
                        HideWord.setVisibility(View.GONE);
                    }else {
                        HidePicture.setVisibility(View.GONE);//隐藏布局
                        isVisbile = true;
                    }

                    break;
                case R.id.Ibtn_activity_edit_word:      //字体颜色
                    if (isVisbile) {
                        //隐藏软键盘
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(EdEdit.getWindowToken(),0);
                        isVisbile = false;
                        HideWord.setVisibility(View.VISIBLE);//显示布局
                        HidePicture.setVisibility(View.GONE);
                    }else {
                        HideWord.setVisibility(View.GONE);//隐藏布局
                        isVisbile = true;
                    }
                    break;
                case R.id.activity_edit_picture:
                    // 激活系统图库，选择一张图片
                    intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    // 开启一个带有返回值的Activity，请求码为LOCAL_IMAGE_CODE
                    startActivityForResult(intent, LOCAL_IMAGE_CODE);
                    break;
                case R.id.activity_edit_camera:
                    // TODO Auto-generated method stub
                    // 使用意图 直接调用安装在手机上的照相机
                    // 直接开发Camera硬件
                    intent = new Intent(
                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_IMAGE_CODE);// 打开照相机
                    break;
            }
        }
    }
}
