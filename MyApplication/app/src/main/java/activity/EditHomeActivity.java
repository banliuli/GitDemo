package activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.administrator.suishouji.R;
import com.example.administrator.suishouji.ToggleStatus;


public class EditHomeActivity extends Activity {

    private ToggleButton BtnCollect;
    ToggleStatus status = new ToggleStatus();
    private ToggleButton BtnMove;
    private ToggleButton BtnEdit;
    private ToggleButton BtnMore;
    private ImageView mIv_back;

    private PopupWindow popupWindow;
    private View view;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private boolean first;
    private RelativeLayout Rlayout1,Rlayout2,Rlayout3,Rlayout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edithome);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();
        setData();
        BtnCollect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Toast.makeText(EditHomeActivity.this, "收藏成功!", Toast.LENGTH_SHORT).show();
                    status.setOne(isChecked);
                }
                else{
                    status.setOne(false);
                }
            }
        });// 添加监听事件
    }

    private void setData(){
        preferences = getSharedPreferences("togglebuttonstatus", Context.MODE_PRIVATE);
		/*
		 * 判断是不是第一次运行该程序
		 * （因为第一次运行时，SharedPreferences是没有保存"first"的，
		 * "first"不存在即为null，默认返回自己设置的参数true）
		 *
		 */
        first = preferences.getBoolean("first", true);
        editor = preferences.edit();
        if(first){
            getStatus();
        }
        else{
            status.one = preferences.getBoolean("s_one", false);
            setToggButonStatus(status);
        }
    }
    /*
	 * 根据保存的参数设置每个ToggleButton的状态
	 */
    private void setToggButonStatus(ToggleStatus data){
        BtnCollect.setChecked(data.one);
    }
    /*
	 * 获取每个ToggleButton的状态，并保存在status里面
	 */
    private void getStatus(){
        status.one = BtnCollect.isChecked();
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(first){
            editor.putBoolean("first", false);
        }
        //关闭之前把数据写进去
        editor.putBoolean("s_one", status.one);
        editor.commit();
    }
    //获取界面控件
    private void getView() {
        BtnCollect = (ToggleButton) findViewById(R.id.btn_activity_edithome_collect);
        BtnMove = (ToggleButton) findViewById(R.id.btn_activity_edithome_move);
        BtnEdit = (ToggleButton) findViewById(R.id.btn_activity_edithome_edit);
        BtnMore = (ToggleButton) findViewById(R.id.btn_activity_edithome_more);
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
                //case R.id.btn_activity_edithome_collect:   //收藏
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
                case R.id.Iv_activity_edithome_back:       //返回
                    Intent i=new Intent(EditHomeActivity.this,TextListActivity.class);
                    startActivity(i);
                    break;
            }
        }

    }

    /**
     * “更多”弹框
     */
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
            popupWindow.showAtLocation(view, Gravity.RIGHT|Gravity.BOTTOM,0,180);
            //点击外面弹窗消失
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

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
            popupWindow.showAtLocation(view, Gravity.RIGHT|Gravity.BOTTOM,0,180);
            //点击外面弹窗消失
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
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

    /**
     * “详细信息”弹框
     */
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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
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
                    new AlertDialog.Builder(EditHomeActivity.this).setTitle("确认删除？")//设置对话框标题
                            .setMessage("删除后无法恢复")//设置显示的内容
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {  //添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {  //添加取消按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                    break;
                case R.id.Rlayout_popup4:     //详细信息
                    popup1();
                    break;

            }
        }
    }
}
