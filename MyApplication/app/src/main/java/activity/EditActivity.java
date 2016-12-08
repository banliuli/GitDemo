package activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.suishouji.R;

import jp.wasabeef.richeditor.RichEditor;


public class EditActivity extends Activity {


    private ImageView IvBack;
    private TextView TvFinish;
    private RichEditor mEditor;

    private ImageButton IBtnBullet,IBtnNumber,IBtnLeft,IBtnRight,IBtnPicture,IBtnWord;

    private LinearLayout HideWord,HidePicture;
    private RelativeLayout addPicture,addCamera;

    private ImageView IvAdd;
    private static final int CAMERA_IMAGE_CODE = 1;// 拍照
    private static final int LOCAL_IMAGE_CODE = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    private boolean isVisbile = true;
    int flag = 0;

    private ImageView IvBlack,IvGray,IvBlue,IvRed,IvYellow,IvGreen;    //字体颜色
    private ImageView IvBold,IvItalic,IvUnderline,IvStrikethrough;    //字体格式
    private ImageView IvLeft1,IvRight1,IvCenter1;    //文本对齐

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();
        //获取RichEditor界面
        getEditor();
    }

    /**
     * 获取RichEditor界面
     */
    private void getEditor() {
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(20);
        mEditor.setEditorFontColor(Color.BLACK);
        mEditor.setPadding(10,10,10,10);
        mEditor.setPlaceholder("欢迎使用随手记......");
    }

    /**
     * 获取界面控件
     */
    private void getView() {
        IvBack = (ImageView) findViewById(R.id.Iv_activity_edit_back);
        TvFinish = (TextView) findViewById(R.id.Tv_activity_edit_finish);

        IvAdd = (ImageView)findViewById(R.id.Iv_activity_add);
        EdEdit = (EditText) findViewById(R.id.Ed_activity_edit_content);

        IBtnBullet = (ImageButton) findViewById(R.id.Ibtn_activity_edit_bullet);
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


        mEditor = (RichEditor) findViewById(R.id.activity_edit_editor);

        //字体颜色
        IvBlack = (ImageView) findViewById(R.id.Iv_activity_edit_black);
        IvGray = (ImageView) findViewById(R.id.Iv_activity_edit_gray);
        IvBlue = (ImageView) findViewById(R.id.Iv_activity_edit_blue);
        IvRed = (ImageView) findViewById(R.id.Iv_activity_edit_red);
        IvYellow = (ImageView) findViewById(R.id.Iv_activity_edit_yellow);
        IvGreen = (ImageView) findViewById(R.id.Iv_activity_edit_green);

        //字体格式
        IvBold = (ImageView) findViewById(R.id.Iv_activity_edit_bold);
        IvItalic = (ImageView) findViewById(R.id.Iv_activity_edit_italic);
        IvUnderline = (ImageView) findViewById(R.id.Iv_activity_edit_underline);
        IvStrikethrough = (ImageView) findViewById(R.id.Iv_activity_edit_strikethrough);

        //字体对齐
        IvLeft1 = (ImageView) findViewById(R.id.Iv_activity_edit_left1);
        IvRight1 = (ImageView) findViewById(R.id.Iv_activity_edit_right1);
        IvCenter1 = (ImageView) findViewById(R.id.Iv_activity_edit_center1);
    }

    /**
     * 注册监听事件
     */
    private void setListener() {
        EditActivity.MyListener listener = new EditActivity.MyListener();
        IvBack.setOnClickListener(listener);
        TvFinish.setOnClickListener(listener);

        IBtnBullet.setOnClickListener(listener);
        IBtnNumber.setOnClickListener(listener);
        IBtnLeft.setOnClickListener(listener);
        IBtnRight.setOnClickListener(listener);
        IBtnPicture.setOnClickListener(listener);
        IBtnWord.setOnClickListener(listener);
        addPicture.setOnClickListener(listener);
        addCamera.setOnClickListener(listener);
        IvAdd.setOnClickListener(listener);
    }
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
                case R.id.Tv_activity_edit_finish:    //完成
                    Intent intent2 = new Intent();
                    intent2.setClass(getApplication(),EditHomeActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.Ibtn_activity_edit_bullet:       //无数字列项
                    mEditor.setBullets();

                    if (flag == 0) {
                        IBtnBullet.setImageResource(0);
                        IBtnBullet.setImageResource(R.drawable.bullet1);
                        flag = 1;
                    }else {
                        IBtnBullet.setImageResource(0);
                        IBtnBullet.setImageResource(R.drawable.bullet);
                        flag = 0;
                    }
                    break;
                case R.id.Ibtn_activity_edit_number:   //有数字列项
                    mEditor.setNumbers();

                    if (flag == 0) {
                        IBtnNumber.setImageResource(0);
                        IBtnNumber.setImageResource(R.drawable.number1);
                        flag = 1;
                    }else {
                        IBtnNumber.setImageResource(0);
                        IBtnNumber.setImageResource(R.drawable.number);
                        flag = 0;
                    }
                    break;
                case R.id.Ibtn_activity_edit_left:     //左对齐
                    mEditor.setAlignLeft();

                    break;
                case R.id.Ibtn_activity_edit_right:    //右对齐
                    mEditor.setAlignRight();

                    break;
                case R.id.Ibtn_activity_edit_picture:    //图片
                    if (flag == 0) {
                        IBtnPicture.setImageResource(0);
                        IBtnPicture.setImageResource(R.drawable.epicture1);
                        flag = 1;
                    }else {
                        IBtnPicture.setImageResource(0);
                        IBtnPicture.setImageResource(R.drawable.epicture);
                        flag = 0;
                    }

                    if (isVisbile) {
                        //隐藏软键盘
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(mEditor.getWindowToken(),0);
                        isVisbile = false;
                        HidePicture.setVisibility(View.VISIBLE);//显示布局
                        HideWord.setVisibility(View.GONE);
                    }else {
                        HidePicture.setVisibility(View.GONE);//隐藏布局
                        isVisbile = true;
                    }

                    break;
                case R.id.Ibtn_activity_edit_word:      //字体格式
                    if (flag == 0) {
                        IBtnWord.setImageResource(0);
                        IBtnWord.setImageResource(R.drawable.word1);
                        flag = 1;
                    }else {
                        IBtnWord.setImageResource(0);
                        IBtnWord.setImageResource(R.drawable.word);
                        flag = 0;
                    }

                    if (isVisbile) {
                        //隐藏软键盘
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(mEditor.getWindowToken(),0);
                        isVisbile = false;
                        HideWord.setVisibility(View.VISIBLE);//显示布局
                        HidePicture.setVisibility(View.GONE);
                    }else {
                        HideWord.setVisibility(View.GONE);//隐藏布局
                        isVisbile = true;
                    }

                    //加粗
                    IvBold.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setBold();
                            if (flag == 0) {
                                IvBold.setImageResource(0);
                                IvBold.setImageResource(R.drawable.bold1);
                                flag = 1;
                            }else {
                                IvBold.setImageResource(0);
                                IvBold.setImageResource(R.drawable.bold);
                                flag = 0;
                            }
                        }
                    });

                    //斜体
                   IvItalic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setItalic();
                            if (flag == 0) {
                                IvItalic.setImageResource(0);
                                IvItalic.setImageResource(R.drawable.italic1);
                                flag = 1;
                            }else {
                                IvItalic.setImageResource(0);
                                IvItalic.setImageResource(R.drawable.italic);
                                flag = 0;
                            }
                        }
                    });

                    //下划线
                    IvUnderline.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setUnderline();
                            if (flag == 0) {
                                IvUnderline.setImageResource(0);
                                IvUnderline.setImageResource(R.drawable.underline1);
                                flag = 1;
                            }else {
                                IvUnderline.setImageResource(0);
                                IvUnderline.setImageResource(R.drawable.underline);
                                flag = 0;
                            }
                        }
                    });

                    //中划线
                   IvStrikethrough.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setStrikeThrough();
                            if (flag == 0) {
                                IvStrikethrough.setImageResource(0);
                                IvStrikethrough.setImageResource(R.drawable.strikethrough1);
                                flag = 1;
                            }else {
                                IvStrikethrough.setImageResource(0);
                                IvStrikethrough.setImageResource(R.drawable.strikethrough);
                                flag = 0;
                            }
                        }
                    });

                    //左对齐
                    IvLeft1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setAlignLeft();
                            if (flag == 0) {
                                IvLeft1.setImageResource(0);
                                IvLeft1.setImageResource(R.drawable.left11);
                                flag = 1;
                            }else {
                                IvLeft1.setImageResource(0);
                                IvLeft1.setImageResource(R.drawable.left1);
                                flag = 0;
                            }
                        }
                    });

                    //居中
                    IvCenter1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setAlignCenter();
                            if (flag == 0) {
                                IvCenter1.setImageResource(0);
                                IvCenter1.setImageResource(R.drawable.center11);
                                flag = 1;
                            }else {
                                IvCenter1.setImageResource(0);
                                IvCenter1.setImageResource(R.drawable.center1);
                                flag = 0;
                            }
                        }
                    });

                    //右对齐
                    IvRight1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setAlignRight();
                            if (flag == 0) {
                                IvRight1.setImageResource(0);
                                IvRight1.setImageResource(R.drawable.right11);
                                flag = 1;
                            }else {
                                IvRight1.setImageResource(0);
                                IvRight1.setImageResource(R.drawable.right1);
                                flag = 0;
                            }
                        }
                    });


                    //字体大小H1
                    findViewById(R.id.Iv_activity_edit_h1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setHeading(1);
                        }
                    });

                    //字体大小H2
                    findViewById(R.id.Iv_activity_edit_h2).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setHeading(2);
                        }
                    });

                    //字体大小H3
                    findViewById(R.id.Iv_activity_edit_h3).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setHeading(3);
                        }
                    });

                    //字体大小H4
                    findViewById(R.id.Iv_activity_edit_h4).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setHeading(4);
                        }
                    });

                    //字体大小H5
                    findViewById(R.id.Iv_activity_edit_h5).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setHeading(5);
                        }
                    });

                    //字体大小H6
                    findViewById(R.id.Iv_activity_edit_h6).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setHeading(6);
                        }
                    });


                    //字体颜色为黑色
                     IvBlack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setTextColor(Color.BLACK);
                            if (flag == 0) {
                                IvBlack.setImageResource(0);
                                IvBlack.setImageResource(R.drawable.color11);
                                flag = 1;
                            }else {
                                IvBlack.setImageResource(0);
                                IvBlack.setImageResource(R.drawable.color1);
                                flag = 0;
                            }
                        }
                    });

                    //字体颜色为灰色
                    IvGray.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setTextColor(Color.GRAY);
                            if (flag == 0) {
                                IvGray.setImageResource(0);
                                IvGray.setImageResource(R.drawable.color22);
                                flag = 1;
                            }else {
                                IvGray.setImageResource(0);
                                IvGray.setImageResource(R.drawable.color2);
                                flag = 0;
                            }
                        }
                    });

                    //字体颜色为蓝色
                    IvBlue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setTextColor(Color.BLUE);
                            if (flag == 0) {
                                IvBlue.setImageResource(0);
                                IvBlue.setImageResource(R.drawable.color33);
                                flag = 1;
                            }else {
                                IvBlue.setImageResource(0);
                                IvBlue.setImageResource(R.drawable.color3);
                                flag = 0;
                            }
                        }
                    });

                    //字体颜色为红色
                    IvRed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setTextColor(Color.RED);
                            if (flag == 0) {
                                IvRed.setImageResource(0);
                                IvRed.setImageResource(R.drawable.color44);
                                flag = 1;
                            }else {
                                IvRed.setImageResource(0);
                                IvRed.setImageResource(R.drawable.color4);
                                flag = 0;
                            }
                        }
                    });

                    //字体颜色为黄色
                    IvYellow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setTextColor(Color.YELLOW);
                            if (flag == 0) {
                                IvYellow.setImageResource(0);
                                IvYellow.setImageResource(R.drawable.color55);
                                flag = 1;
                            }else {
                                IvYellow.setImageResource(0);
                                IvYellow.setImageResource(R.drawable.color5);
                                flag = 0;
                            }
                        }
                    });

                    //字体颜色为绿色
                    IvGreen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditor.setTextColor(Color.GREEN);
                            if (flag == 0) {
                                IvGreen.setImageResource(0);
                                IvGreen.setImageResource(R.drawable.color66);
                                flag = 1;
                            }else {
                                IvGreen.setImageResource(0);
                                IvGreen.setImageResource(R.drawable.color6);
                                flag = 0;
                            }
                        }
                    });

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
