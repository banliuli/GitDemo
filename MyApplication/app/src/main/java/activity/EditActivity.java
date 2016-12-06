package activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.suishouji.R;


public class EditActivity extends Activity {


    private ImageView IvBack;
    private EditText EdEdit;

    private ImageButton IBtnSpot,IBtnNumber,IBtnLeft,IBtnRight,IBtnPicture,IBtnWord;

    private LinearLayout HideWord,HidePicture;
    private RelativeLayout addPicture,addCamera;
    private ImageView imageView;
    private static final int LOCAL_IMAGE_CODE = 1;//本地相册
    private static final int CAMERA_IMAGE_CODE = 2;//照相机

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
    }
    //获取相册和相机
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 表示 调用照相机拍照
            case CAMERA_IMAGE_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imageView.setImageBitmap(bitmap);
                }
                break;
            // 选择图片库的图片
            case LOCAL_IMAGE_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    imageView.setImageURI(uri);
                }
                break;
        }
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
                    intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, LOCAL_IMAGE_CODE);// 打开相册
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
