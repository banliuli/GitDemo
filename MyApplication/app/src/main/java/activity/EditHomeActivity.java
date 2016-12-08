package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.administrator.suishouji.R;


public class EditHomeActivity extends Activity {

    private Button BtnCollect;
    private Button BtnMove;
    private Button BtnEdit;
    private Button BtnMore;
    private ImageView mIv_back;

    private PopupWindow popupWindow;
    private View view;

    private RelativeLayout Rlayout1,Rlayout2,Rlayout3,Rlayout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edithome);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();
    }

    //获取界面控件
    private void getView() {
        BtnCollect = (Button) findViewById(R.id.btn_activity_edithome_collect);
        BtnMove = (Button) findViewById(R.id.btn_activity_edithome_move);
        BtnEdit = (Button) findViewById(R.id.btn_activity_edithome_edit);
        BtnMore = (Button) findViewById(R.id.btn_activity_edithome_more);
        mIv_back=(ImageView)findViewById(R.id.Iv_activity_edithome_back);
    }

    //注册监听事件
    private void setListener() {
        MyListener listener = new MyListener();
        BtnCollect.setOnClickListener(listener);
        BtnMove.setOnClickListener(listener);
        BtnEdit.setOnClickListener(listener);
        BtnMore.setOnClickListener(listener);
        mIv_back.setOnClickListener(listener);
    }
    

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_activity_edithome_collect:   //收藏

                case R.id.btn_activity_edithome_move:      //移动
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(),MoveActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_activity_edithome_edit:     //编辑
                    Intent intent2 = new Intent();
                    intent2.setClass(getApplicationContext(),EditActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.btn_activity_edithome_more:      //更多
                    popup();
                    break;
                case R.id.Iv_activity_edithome_back:
                    Intent i=new Intent(EditHomeActivity.this,TextListActivity.class);
                    startActivity(i);
                    break;
            }
        }

    }

    //“更多”弹框
    private void popup() {
        if (view == null) {
            //装载popup对应的界面布局
            LayoutInflater inflater = LayoutInflater.from(this);
            view = inflater.inflate(R.layout.popup,null);

           //设置popupWindow大小
            popupWindow = new PopupWindow(view, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
            //获取焦点
            popupWindow.setFocusable(true);
            //弹框位置
            popupWindow.showAtLocation(view, Gravity.RIGHT|Gravity.BOTTOM,0,268);
            //点击外面弹窗消失
            popupWindow.setOutsideTouchable(true);

        }
        else {
            //装载popup对应的界面布局
            LayoutInflater inflater = LayoutInflater.from(this);
            view = inflater.inflate(R.layout.popup,null);
            //设置popupWindow大小
            popupWindow = new PopupWindow(view, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
            //获取焦点
            popupWindow.setFocusable(true);
            //弹框位置
            popupWindow.showAtLocation(view, Gravity.RIGHT|Gravity.BOTTOM,0,268);
            //点击外面弹窗消失
            popupWindow.setOutsideTouchable(true);
        }

        //获取"更多"弹框里的控件
        Rlayout1 = (RelativeLayout) view.findViewById(R.id.Rlayout_popup1);
        Rlayout2 = (RelativeLayout) view.findViewById(R.id.Rlayout_popup2);
        Rlayout3 = (RelativeLayout) view.findViewById(R.id.Rlayout_popup3);
        Rlayout4 = (RelativeLayout) view.findViewById(R.id.Rlayout_popup4);

        //获取"更多"弹框里的控件点击事件
        Rlayout1.setOnClickListener(new Listener());
        Rlayout2.setOnClickListener(new Listener());
        Rlayout3.setOnClickListener(new Listener());
        Rlayout4.setOnClickListener(new Listener());
    }

    private void popup1() {
        //装载popup对应的界面布局
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.messagemore,null);
        //设置popupWindow大小
        popupWindow = new PopupWindow(view, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
        //获取焦点
        popupWindow.setFocusable(true);
        //弹框位置
        popupWindow.showAtLocation(view, Gravity.CENTER|Gravity.CENTER,0,0);
        //点击外面弹窗消失
        popupWindow.setOutsideTouchable(true);
    }

    //"更多"弹框里的控件点击事件
    private class Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Rlayout_popup1://纸张背景
                    Intent i1=new Intent(EditHomeActivity.this,PaperBgActivity.class);
                    startActivity(i1);
                    break;
                case R.id.Rlayout_popup2://阅读密码
                    Intent i=new Intent(EditHomeActivity.this,SetpwdActivity.class);
                    startActivity(i);
                    break;
                case R.id.Rlayout_popup3:     //删除
                    break;

                case R.id.Rlayout_popup4:     //详细信息
                    popup1();
                    break;
            }
        }
    }
}
