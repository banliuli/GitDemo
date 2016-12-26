package activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ActionBarOverlayLayout;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;

import android.text.Html;
import android.text.format.DateFormat;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.ContextMenu;
import android.view.MenuItem;

import com.example.administrator.suishouji.R;
import com.example.administrator.suishouji.ToggleStatus;

import DBSql.DBAdapter;
import DBSql.DBCollect;
import DBSql.DBManager;
import adapter.CollectionAdapter;
import jp.wasabeef.richeditor.RichEditor;


public class EditHomeActivity extends Activity {

    private ToggleButton BtnCollect;
    ToggleStatus status = new ToggleStatus();
    private ToggleButton BtnMove;
    private ToggleButton BtnEdit;
    private ToggleButton BtnMore;
    private ImageView mIv_back;
    private DBAdapter da=null;
    private PopupWindow popupWindow;
    private View view;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private boolean first;
    private RelativeLayout Rlayout1,Rlayout2,Rlayout3,Rlayout4;
    private TextView TvTitle;
    private TextView TvTime;
    private TextView TvContent;
    private DBCollect dm = null;

    private String idString;
    private int state = -1;
    private EditText EtTitle;
    private String title;
    private String text;
    private String time;
    private int id2;
    public Cursor cursor=null;
    public String namestr="";
    private String path = null;
    private String mtime;
    private ActionBar.Tab mPreview;
    private AdapterView.AdapterContextMenuInfo menuInfo;
    private CollectionAdapter item;
    private CollectionAdapter adapter;
    private int position;
    private View collect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edithome);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();
        setData();
        dm = new DBCollect(this);
        da=new DBAdapter(this);
        Intent intent = getIntent();
        state = Integer.parseInt(intent.getStringExtra("state"));
        Log.i("log", "state---->"+state);
        if (state==2) {
            idString = intent.getStringExtra("id");
            Log.i("log", "id---->" + idString);
            id2 = Integer.parseInt(idString);
            title = intent.getStringExtra("title");
            text = intent.getStringExtra("content");
            time = intent.getStringExtra("time");
            EtTitle.setText(title);
            dm.open();
            int i = 0;
            int start = 0;
            int end = 0;
            String str1 = null;
            String str2 = "[";
            String str4 = "]";
            String iconname=null;
            SpannableString travelsSpan = new SpannableString(text);
            for (i = 0; i < text.length(); i++) {
                str1 = text.substring(i, i + 1);
                //travelsString+=str1;
                Log.i("log", str1);
                if (str1.equals(str2)) {
                    start = i + 1;
                }
                if (str1.equals(str4)) {
                    end = i;
                    namestr = text.substring(start, end);
                    Log.i("log", namestr);
                    cursor = dm.selcetPathByName(namestr);
                    cursor.moveToFirst();
                    path = cursor.getString(cursor.getColumnIndex("path"));
                    cursor.close();
                    namestr = null;
                    Log.i("log", path);

                    if (!(cursor == null)) {
                        int count = cursor.getCount();
                        Log.i("log", "count----->" + count);
                    } else {
                        Log.i("log", "insert icon faile");
                    }
                }

            }
            dm.close();
        }

        BtnCollect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    getData();
//                    collect.setBackgroundResource(R.drawable.collect);
                    Toast.makeText(EditHomeActivity.this, "收藏成功!", Toast.LENGTH_SHORT).show();
                    status.setOne(isChecked);
                }
                else{
                    DeleteData();
//                    collect.setBackgroundResource(R.drawable.collect2);
                    Toast.makeText(EditHomeActivity.this, "取消收藏!", Toast.LENGTH_SHORT).show();
                    status.setOne(false);
                }
            }
        });// 添加监听事件
    }


    private void setData(){
        Intent intent = getIntent();//获取启动该Activity的intent对象
        String title= intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String time= intent.getStringExtra("time");
        long t = Long.parseLong(time);
       String datetime = DateFormat.format("yyyy-MM-dd kk:mm:ss", t).toString();
        this.TvTitle.setText(title);
        this.TvTime.setText(datetime);
        this.TvContent.setText(content);
        this.TvContent.setText(Html.fromHtml(content));
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
//        BtnMove = (ToggleButton) findViewById(R.id.btn_activity_edithome_move);
//        BtnEdit = (ToggleButton) findViewById(R.id.btn_activity_edithome_edit);
        BtnMore = (ToggleButton) findViewById(R.id.btn_activity_edithome_more);
        mIv_back=(ImageView)findViewById(R.id.Iv_activity_edithome_back);
//        collect=(ImageView)findViewById(R.id.edit_collect);
        TvTitle = (TextView)findViewById(R.id.Tv_activity_edithome_title);
        TvTime = (TextView)findViewById(R.id.Tv_acyivity_edithome_time);
        TvContent = (TextView)findViewById(R.id.Tv_activity_edithome_content);
    }

    //注册监听事件
    private void setListener() {
        MyListener listener = new MyListener();
        BtnCollect.setOnClickListener(listener);
     //   BtnMove.setOnClickListener(listener);
//        BtnEdit.setOnClickListener(listener);
        BtnMore.setOnClickListener(listener);
        mIv_back.setOnClickListener(listener);
//        collect.setOnClickListener(listener);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("");
        //设置选项
        Log.i("log", "chooseing menu");
        menu.add(0,0,0,"删除");
    }

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

//                case R.id.btn_activity_edithome_move:      //移动
//                    Intent intent = new Intent();
//                    intent.setClass(getApplicationContext(),MoveActivity.class);
//                    startActivity(intent);
//                    break;
//                case R.id.btn_activity_edithome_edit:     //编辑
//                    Intent intent2 = new Intent();
//                    intent2.setClass(getApplicationContext(),EditActivity.class);
//                    startActivity(intent2);
//                    break;
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

        //获取"更多"弹框里的控件点击事件
        Rlayout1.setOnClickListener(new Listener());
        Rlayout2.setOnClickListener(new Listener());
        Rlayout3.setOnClickListener(new Listener());
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
                case R.id.Rlayout_popup1://阅读密码
                    dialog();
                    break;
                case R.id.Rlayout_popup2:     //删除
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
                case R.id.Rlayout_popup3:     //详细信息
                    popup1();
                    break;

            }
        }
    }
    private void dialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(EditHomeActivity.this);
        final AlertDialog dialog=builder.create();
        final View view=View.inflate(this,R.layout.layout_setpwd,null);
        dialog.setView(view);
        dialog.show();
        Button submit = (Button) view.findViewById(R.id.btn_activity_setpwd_finish);
        Button cancel = (Button) view.findViewById(R.id.btn_activity_setpwd_cancel);
        CheckBox checkBox=(CheckBox)view.findViewById(R.id.Cb_activity_setpwd_check);
        final EditText setpwd = (EditText) view.findViewById(R.id.Et_setpwd_activity_set);
        final EditText enpwd = (EditText) view.findViewById(R.id.Et_setpwd_activity_ensure);
        setpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        enpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        CompoundButton.OnCheckedChangeListener listener=new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    enpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    //如果选中，显示密码
                }else{
                    enpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    setpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //否则隐藏密码
                }
            }

        };
        checkBox.setOnCheckedChangeListener(listener);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText setpwd = (EditText) view.findViewById(R.id.Et_setpwd_activity_set);
                EditText enpwd = (EditText) view.findViewById(R.id.Et_setpwd_activity_ensure);
                String pwd = setpwd.getText().toString();
                String ensure = enpwd.getText().toString();
                DBAdapter da = new DBAdapter(EditHomeActivity.this);

                //db.close();
                if(!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(ensure)){
                    //进入用户手机防盗模块
                    if(pwd.equals(ensure)) {
                        da.open();

                        ContentValues values=new ContentValues();
                        //cursor =da.getId(cursor.getLong(cursor.getColumnIndex("_id")));

                        values.put("id", 1);
                        values.put("password",pwd);
                        values.put("repassword",ensure);

                        da.insert(pwd,ensure);
                        values.clear();
                        Intent i=new Intent();
                        i.setClass(EditHomeActivity.this,TextListActivity.class);
                        startActivity(i);

                        //跳转到新的界面以后需要去隐藏对话框
                        dialog.dismiss();
                        da.close();
                    } else {
                        Toast.makeText(EditHomeActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    //提示用户密码输入为空的情况
                    Toast.makeText(EditHomeActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    public void getData()
    {
        Log.i("log","title---->"+title);
        Log.i("log", "travels---->"+text);
        try{
            dm.open();
            if(state==1) {
                dm.insert(title, text);
            }
            if (state==2) {
                Log.i("log", "ready to alter");
                dm.update(id2, title, text);
            }
            dm.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void DeleteData()
    {
        Log.i("log","title---->"+title);
        Log.i("log", "travels---->"+text);
        try{
            dm.open();
            if(state==2) {
                dm.delete(id2,title,text);
            }
            dm.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
