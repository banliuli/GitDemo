package activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.suishouji.R;


public class EditActivity extends Activity {


    private ImageView IvBack;
    private EditText EdEdit;

    private ImageButton IBtnSpot,IBtnNumber,IBtnLeft,IBtnRight,IBtnPicture,IBtnWord;

    private LinearLayout HideWord,HidePicture;

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
            }
        }
    }



}
