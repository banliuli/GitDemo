package activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
=======
>>>>>>> eca444ff2d41dc03deaba6405ce4fffab06a2c62
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.CheckBox;
import android.widget.CompoundButton;
=======
>>>>>>> eca444ff2d41dc03deaba6405ce4fffab06a2c62
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.suishouji.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


<<<<<<< HEAD
import DBSql.DBAdapter;
=======
>>>>>>> eca444ff2d41dc03deaba6405ce4fffab06a2c62
import DBSql.DBManager;


import DBSql.DBManager;
import DBSql.HtmlManager;


import adapter.TextListAdapter;

/**
 * Created by lenovo on 2016/11/28.
 */
public class TextListActivity extends AppCompatActivity{

    private ListView lv;
    private ImageView login;
    private Button mine;
    private ImageView refresh;
    private Button add;
    private DBManager dm;
    private Cursor cursor;
    public Cursor cursor2;
    private TextListAdapter adapter;
    public static final String CHECK_STATE = "0";
    public static final String EDIT_STATE = "1";
    public static final String ALERT_STATE = "2";
    private HtmlManager HM;
    private int start=0;
    private int end=0;
    private int i;
    private String str1=null;
    private String str2="[";
    private String str4="]";
    private String iconname=null;
    private File copyfromfile=null;
    private File copyintofile=null;
    private FileOutputStream copyinto=null;
    private FileInputStream copyfrom=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textlist);
        initID();
        setListener();


        dm = new DBManager(this);
        initAdapter();
        lv.setAdapter(adapter);

<<<<<<< HEAD

=======
        dm = new DBManager(this);
        initAdapter();
        lv.setAdapter(adapter);
>>>>>>> eca444ff2d41dc03deaba6405ce4fffab06a2c62

        //给ListView设置item点击监听器，实现点击效果
        lv.setOnItemClickListener(new myOnItemClickListener());
        //实现长按出现菜单
        lv.setOnCreateContextMenuListener(new myOnCreateContextMenuListener());

    }

    //在listview中显示数据库中的对象
    private void initAdapter() {
        dm.open();//打开数据库操作对象
        cursor = dm.selectAll();//获取所有数据
        cursor.moveToFirst();//将游标移动到第一条数据，使用前必须调用
        int count = cursor.getCount();//个数
        ArrayList<String> title = new ArrayList<String>();

        ArrayList<String> time = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            title.add(cursor.getString(cursor.getColumnIndex("title")));
            time.add(cursor.getString(cursor.getColumnIndex("time")));
            cursor.moveToNext();//将游标指向下一个
        }
        dm.close();//关闭数据操作对象
        adapter = new TextListAdapter(this,title, time);//创建数据源


    }


    //选择菜单
    public class myOnCreateContextMenuListener implements View.OnCreateContextMenuListener {

        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // TODO Auto-generated method stub
            final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle("");
            //设置选项
            Log.i("log", "chooseing menu");
            menu.add(0,0,0,"删除");
            menu.add(0,1,0,"修改");
<<<<<<< HEAD
            menu.add(0,2,0,"导出到SD卡");
=======
            menu.add(0,2,0,"查看");
            menu.add(0,3,0,"导出到SD卡");
>>>>>>> eca444ff2d41dc03deaba6405ce4fffab06a2c62
        }

    }

    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        dm.open();
        switch(item.getItemId()){
            case 0://删除
                Log.i("log", "selectItem---->"+item.getItemId());
                try{
                    Log.i("log", "cursor ready move ");
                    Log.i("log", "menuInfo position "+menuInfo.position);
                    cursor.moveToPosition(menuInfo.position);
                    Log.i("log", "cursor move success");
                    int i = dm.delete(Long.parseLong(cursor.getString(cursor.getColumnIndex("_id"))));//删除数据
                    adapter.removeListItem(menuInfo.position);//删除数据
                    adapter.notifyDataSetChanged();//通知数据源，数据已经改变，刷新界面
                    dm.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
            case 1://修改
                //	Log.v("show", "chenggong2");
                try{
                    cursor.moveToPosition(menuInfo.position);

                    //用于Activity之间的通讯
                    Intent intent = new Intent();
                    //通讯时的数据传送
                    intent.putExtra("id", cursor.getString(cursor.getColumnIndex("_id")));
                    intent.putExtra("state", ALERT_STATE);
                    intent.putExtra("title", cursor.getString(cursor.getColumnIndex("title")));
                    intent.putExtra("time", cursor.getString(cursor.getColumnIndex("time")));
                    intent.putExtra("content", cursor.getString(cursor.getColumnIndex("content")));
                    //设置并启动另一个指定的Activity
                    intent.setClass(TextListActivity.this, EditActivity.class);
                    TextListActivity.this.startActivity(intent);
                    finish();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
<<<<<<< HEAD
            case 2://导出
=======
            case 2://查看
                //	Log.v("show", "chenggong3");
                try{
                    cursor.moveToPosition(menuInfo.position);

                    Intent intent = new Intent();

                    intent.putExtra("id", cursor.getString(cursor.getColumnIndex("_id")));
                    intent.putExtra("title", cursor.getString(cursor.getColumnIndex("title")));
                    intent.putExtra("time", cursor.getString(cursor.getColumnIndex("time")));
                    intent.putExtra("content", cursor.getString(cursor.getColumnIndex("content")));

                    intent.setClass(TextListActivity.this, EditHomeActivity.class);
                    TextListActivity.this.startActivity(intent);

                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
            case 3://导出
>>>>>>> eca444ff2d41dc03deaba6405ce4fffab06a2c62
                try{
                    cursor.moveToPosition(menuInfo.position);
                    String itemtitle=cursor.getString(cursor.getColumnIndex("title"));
                    String itemcontent=cursor.getString(cursor.getColumnIndex("content"));
                    //HM.htmlmanager(itemtitle, itemcontent);
                    copyicon(itemtitle,itemcontent);
                    Toast.makeText(TextListActivity.this, "导出成功，快去SD卡的\" 随手记 \"里找出来分享吧！！", Toast.LENGTH_LONG).show();
                }catch(Exception ex){
                    Toast.makeText(TextListActivity.this, "Sorry!!!导出失败！！", Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
                break;

            default:;
        }
        dm.close();
        return super.onContextItemSelected(item);


    }


    public int copyicon(String title1,String content1){
        for(i=0;i<content1.length();i++)
        {
            str1=content1.substring(i, i+1);
            if(str1.equals(str2))
            {
                start=i+1;
            }
            if(str1.equals(str4))
            {
                end=i;
                iconname=content1.substring(start, end);
                Log.i("log", iconname);
                cursor2=dm.selcetPathByName(iconname);
                cursor2.moveToFirst();
                String iconpath2=cursor2.getString(cursor2.getColumnIndex("path"));
                Log.i("log", iconpath2);
                cursor2.close();
                copyfromfile=new File(iconpath2);
                String copyintopath="/mnt/sdcard/随手记/"+title1+"/"+iconname;
                Log.i("log", copyintopath);
                copyfile(iconpath2,copyintopath);
            }
        }
        return 0;

    }
    public int copyfile(String from,String into){
        try
        {
            copyfrom=new FileInputStream(from);
            copyinto=new FileOutputStream(into);
            Log.i("log", "fuck you");
            byte[] bt = new byte[1024];
            int c;
            while((c=copyfrom.read(bt)) > 0){
                copyinto.write(bt,0,c);
            }
            copyfrom.close();
            copyinto.close();
            Log.i("log", "copy success");
            return 1;
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;



        }
    }



    //短按，即点击
    public class myOnItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
            // TODO Auto-generated method stub
            //super.onListItemClick(l, v, position, id);
            Log.i("log", "position--->"+position);
            cursor.moveToPosition(position);
            Intent intent = new Intent();
            intent.putExtra("state", CHECK_STATE);
            intent.putExtra("id", cursor.getString(cursor.getColumnIndex("_id")));
            intent.putExtra("title", cursor.getString(cursor.getColumnIndex("title")));
            intent.putExtra("content", cursor.getString(cursor.getColumnIndex("content")));
            intent.putExtra("time", cursor.getString(cursor.getColumnIndex("time")));
<<<<<<< HEAD
            if(id==1){
                dialog();
            }
            else{
                intent.setClass(TextListActivity.this, EditHomeActivity.class);
                TextListActivity.this.startActivity(intent);
            }
        }
    }
    private void dialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(TextListActivity.this);
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
                DBAdapter da = new DBAdapter(TextListActivity.this);

                //db.close();
                if(!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(ensure)){
                    //进入用户手机防盗模块
                    if(pwd.equals(ensure)) {
                        Intent intent = new Intent();
                        intent.putExtra("state", CHECK_STATE);
                        intent.putExtra("id", cursor.getString(cursor.getColumnIndex("_id")));
                        intent.putExtra("title", cursor.getString(cursor.getColumnIndex("title")));
                        intent.putExtra("content", cursor.getString(cursor.getColumnIndex("content")));
                        intent.putExtra("time", cursor.getString(cursor.getColumnIndex("time")));
                        intent.setClass(TextListActivity.this, EditHomeActivity.class);
                        TextListActivity.this.startActivity(intent);


                        //跳转到新的界面以后需要去隐藏对话框
                        dialog.dismiss();
                        da.close();
                    } else {
                        Toast.makeText(TextListActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    //提示用户密码输入为空的情况
                    Toast.makeText(TextListActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
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
=======
            intent.setClass(TextListActivity.this, EditHomeActivity.class);
            TextListActivity.this.startActivity(intent);
        }
    }

>>>>>>> eca444ff2d41dc03deaba6405ce4fffab06a2c62
    private void initID() {

        lv = (ListView)findViewById(R.id.Lv_activtiy_textlist);
        login=(ImageView) findViewById(R.id.iv_activtiy_main_login);
        mine = (Button)findViewById(R.id.btn_activtiy_main_mine);
        add=(Button)findViewById(R.id.btn_activtiy_main_add);
        refresh = (ImageView) findViewById(R.id.iv_activtiy_main_refresh);

    }
    private void setListener() {
        MyListener listener = new MyListener();
        login.setOnClickListener(listener);
        mine.setOnClickListener(listener);
       add.setOnClickListener(listener);
        refresh.setOnClickListener(listener);

    }


    private class MyListener implements View.OnClickListener{
        private int flag;

        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){

                case R.id.iv_activtiy_main_login:
//                    if(flag==0){
                        i.setClass(TextListActivity.this,Login.class);
//                        flag=1;
//                    }else{
//                        i.setClass(TextListActivity.this,MyAccountsActivity.class);
//                        flag=0;
//                    }
                   startActivity(i);
                    break;
                case R.id.btn_activtiy_main_mine:
                    i.setClass(TextListActivity.this,MineActivity.class);
                  startActivity(i);
                    break;
                case R.id.iv_activtiy_main_refresh:
                    finish();
                    i.setClass(TextListActivity.this,TextListActivity.class);
                    startActivity(i);
                    break;
                case R.id.btn_activtiy_main_add:
                    Intent intent = new Intent(TextListActivity.this,EditActivity.class) ;    //切换Login Activity至User Activity
                    intent.putExtra("state", EDIT_STATE);
                    startActivity(intent);


            }

        }
    }
}
