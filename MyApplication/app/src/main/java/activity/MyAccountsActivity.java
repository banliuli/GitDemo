package activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.suishouji.CircleImageView;
import com.example.administrator.suishouji.R;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import data.UserDataManager;
import data.UserData;
import data.UserDataManager;

public class MyAccountsActivity extends AppCompatActivity {

    private Button fh;
    private Button gd1;
    private Button save;
    private EditText nickname;
    private EditText sex;
    private EditText area;
    private EditText truename;

    /*请求识别码*/
    protected static final int CHOOSE_PICTURE = 0;//从相册中选择图片
    protected static final int TAKE_PICTURE = 1;//拍照
    //private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private ImageView iv_personal_icon;
    private static final String IMAGE_FILE_NAME = "img.jpg";// 头像文件
    private CircleImageView faceImage;
    private static final int RESULT_REQUEST_CODE = 2;
    private CircleImageView headImage;
    private Button cancle;
    private ActionBar.Tab etname;
    private ActionBar.Tab etpwd;
    private UserDataManager mUserDataManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_accounts);
        SharedPreferences spf = getSharedPreferences("MYAPP",MODE_PRIVATE);
        //获取控件
        initId();
        //设置监听
        setListener();
        //获取用户信息
        //getUserData();
        //保存用户信息
      // setUsername();
        setUnickname();
        setUsex();
        setArea();
        setUtruename();

    }

    private void initId() {
        fh = (Button) findViewById(R.id.btn_my_accounts_fh);
        save = (Button) findViewById(R.id.btn_my_accounts_save);
        gd1 = (Button) findViewById(R.id.btn_my_accounts_gd1);
        nickname = (EditText) findViewById(R.id.et_my_accounts_nickname);
        sex = (EditText) findViewById(R.id.et_my_accounts_sex);
        area = (EditText) findViewById(R.id.et_my_accounts_area);
        truename = (EditText) findViewById(R.id.et_my_accounts_truename);
    }

    private void setListener() {
        MyListener listener = new MyListener();
        fh.setOnClickListener(listener);
        gd1.setOnClickListener(listener);
        save.setOnClickListener(listener);
        nickname.setOnClickListener(listener);
        sex.setOnClickListener(listener);
        area.setOnClickListener(listener);
        truename.setOnClickListener(listener);

    }
    class MyListener implements View.OnClickListener {
        private UserData userData;
        String username;
        private Context context;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_my_accounts_fh:            //返回键
                    finish();
                    break;
                case R.id.btn_my_accounts_gd1:
                    /*修改头像*/
                    //显示修改头像的对话框
                    showChoosePicDialog();
                    break;

                case R.id.btn_my_accounts_save:         //保存个人资料的修改
                    SharedPreferences spf = getSharedPreferences("UNAME_EDIT", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = spf.edit();
                   // editor.putString("USERNAME", nickname.getText().toString());
                    editor.putString("UNAME", nickname.getText().toString());
                    editor.putString("SEX", sex.getText().toString());
                    editor.putString("AREA", area.getText().toString());
                    editor.putString("TRUENAME", truename.getText().toString());
                    editor.commit();
                    Toast.makeText(MyAccountsActivity.this, "个人资料修改并保存成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MyAccountsActivity.this, MineActivity.class);
                    startActivity(intent);
                    break;

                //清空用户名EditText
                case R.id.et_my_accounts_nickname:
                    nickname.setText("");
                    break;

                //清空性别EditText
                case R.id.et_my_accounts_sex:
                    sex.setText("");
                    break;

                //清空地区EditText
                case R.id.et_my_accounts_area:
                    area.setText("");
                    break;

                //清空真实姓名EditText
                case R.id.et_my_accounts_truename:
                    truename.setText("");
                    break;
            }
        }
    }

    /**
     * 获取用户信息
     */
    private void getUserData() {
        SharedPreferences spf = getSharedPreferences("UNAME_EDIT", Context.MODE_PRIVATE);
        String Username = spf.getString("USERNAME", "");
        String Unickname = spf.getString("UNAME", "");
        String Usex = spf.getString("SEX", "");
        String Uarea = spf.getString("AREA", "");
        String Utruename = spf.getString("TRUENAME", "");

        nickname.setText(Unickname );
        sex.setText(Usex );
        area.setText(Uarea );
        truename.setText(Utruename );
    }

    /**
     * 保存用户信息的修改
     */
//    private void setUsername() {
//        SharedPreferences spf = getSharedPreferences("UNAME_EDIT", Context.MODE_PRIVATE);
//        String Username = spf.getString("USERNAME", "");
//
//        username.setText(Username);
//    }
    private void setUnickname() {
        SharedPreferences spf = getSharedPreferences("UNAME_EDIT", Context.MODE_PRIVATE);
        String Unickname = spf.getString("UNAME", "");

        nickname.setText( Unickname);
    }
    private void setUsex() {
        SharedPreferences spf = getSharedPreferences("UNAME_EDIT", Context.MODE_PRIVATE);
        String  Usex = spf.getString("SEX", "");

        sex.setText( Usex);
    }
    private void setArea() {
        SharedPreferences spf = getSharedPreferences("UNAME_EDIT", Context.MODE_PRIVATE);
        String Area = spf.getString("AREA", "");

        area.setText( Area);
    }
    private void setUtruename() {
        SharedPreferences spf = getSharedPreferences("UNAME_EDIT", Context.MODE_PRIVATE);
        String Utruename = spf.getString("TRUENAME", "");

        truename.setText( Utruename);
    }

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    /*从本地相册选取图片作为头像*/
                    case CHOOSE_PICTURE:
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("img/*");// 设置文件类型
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;

                    /*启动手机相机拍摄照片作为头像*/
                    case TAKE_PICTURE:
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 判断存储卡是否可用，存储照片文件
                        if (hasSdcard()) {
                            tempUri = Uri.fromFile(new File(Environment
                                    .getExternalStorageDirectory(), IMAGE_FILE_NAME));
                            // 指定照片保存路径（SD卡），IMAGE_FILE_NAME image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                            startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        } else {
                            Toast.makeText(MyAccountsActivity.this, "请插入sd卡", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                //拍照
                case TAKE_PICTURE:
                    if (hasSdcard()) {
                        File tempFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
                        startPhotoZoom(Uri.fromFile(tempFile)); // 对图片进行裁剪处理
                    } else {
                        Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_SHORT).show();
                    }
                    break;

                //相册
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 对图片进行裁剪处理
                    break;

                //case CROP_SMALL_PICTURE:
                case RESULT_REQUEST_CODE: //图片缩放完成后  
                    if (data != null) {
                      setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪原始图片方法的实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     *
     * @param data
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = toRoundBitmap(photo);//将头像设置成圆形
            Drawable drawable = new BitmapDrawable(photo);
            iv_personal_icon.setImageDrawable(drawable);
        }
    }



    /**
     * 把bitmap转成圆形
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int r = 0;
        //取最短边做边长
        if (width < height) {
            r = width;
        } else {
            r = height;
        }
        //构建一个bitmap

        Bitmap backgroundBm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBm);
        Paint p = new Paint();
        //设置边缘光滑，去掉锯齿
        p.setAntiAlias(true);
        RectF rect = new RectF(0, 0, r, r);
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        //且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r / 2, r / 2, p);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, p);

        return backgroundBm;
    }


    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            headImage.setImageBitmap(photo);
            //新建文件夹 先选好路径 再调用mkdir函数 现在是根目录下面的Ask文件夹
            File nf = new File(Environment.getExternalStorageDirectory()+"/Ask");
            nf.mkdir();
            //在根目录下面的ASk文件夹下 创建okkk.jpg文件
            File f = new File(Environment.getExternalStorageDirectory()+"/Ask", "okkk.jpg");

            FileOutputStream out = null;
            try { //打开输出流 将图片数据填入文件中
                out = new FileOutputStream(f);
                photo.compress(Bitmap.CompressFormat.PNG, 90, out);

                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
    /**
     * 是否存在SD卡
     *
     * @return
     */
    private boolean hasSdcard() {
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onResume() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }
}
