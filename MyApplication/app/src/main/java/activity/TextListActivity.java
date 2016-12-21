package activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.List;

import DBSql.DBManager;
import adapter.TextListAdapter;

/**
 * Created by lenovo on 2016/11/28.
 */
public class TextListActivity extends AppCompatActivity{
    private Button back;
    private Button edit;
    private ListView lv;
    private DBManager dm;
    private Cursor cursor;
    private TextListAdapter adapter;
    public static final String CHECK_STATE = "0";
    public static final String EDIT_STATE = "1";
    public static final String ALERT_STATE = "2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textlist);
        initID();
        setListener();

        dm = new DBManager(this);
        initAdapter();
        lv.setAdapter(adapter);
        //给ListView设置item点击监听器，实现点击效果
        lv.setOnItemClickListener(new myOnItemClickListener());
        lv.setOnCreateContextMenuListener(new myOnCreateContextMenuListener());



//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
//                String j = String.valueOf(i);
//                Intent ii=new Intent(TextListActivity.this,EditHomeActivity.class);
//                startActivity(ii);
//            }
//        });
    }

    //在listview中显示数据库中的对象
    private void initAdapter() {
        dm.open();//打开数据库操作对象
        cursor = dm.selectAll();//获取所有数据
        cursor.moveToFirst();//将游标移动到第一条数据，使用前必须调用
        int count = cursor.getCount();//个数
        ArrayList<String> items = new ArrayList<String>();
        ArrayList<String> times = new ArrayList<String>();
        for(int i= 0; i < count; i++){
            items.add(cursor.getString(cursor.getColumnIndex("title")));
            times.add(cursor.getString(cursor.getColumnIndex("time")));
            cursor.moveToNext();//将游标指向下一个
        }
        dm.close();//关闭数据操作对象
        adapter = new TextListAdapter(this,items,times);//创建数据源
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
            menu.add(0,2,0,"查看");
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
            case 2://查看
                //	Log.v("show", "chenggong3");
                try{
                    cursor.moveToPosition(menuInfo.position);

                    Intent intent = new Intent();

                    intent.putExtra("id", cursor.getString(cursor.getColumnIndex("_id")));
                    intent.putExtra("title", cursor.getString(cursor.getColumnIndex("title")));
                    intent.putExtra("time", cursor.getString(cursor.getColumnIndex("time")));
                    intent.putExtra("content", cursor.getString(cursor.getColumnIndex("content")));

                    intent.setClass(TextListActivity.this, EditActivity.class);
                    TextListActivity.this.startActivity(intent);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                break;
            default:;
        }
        dm.close();
        return super.onContextItemSelected(item);
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
            intent.setClass(TextListActivity.this, EditHomeActivity.class);
            TextListActivity.this.startActivity(intent);
        }
    }


//    private void getData() {
//        lit.add(new ItemText(0L,"来自手机","来自手机",false));
//    }

    private void initID() {
        back = (Button)findViewById(R.id.btn_activtiy_textlist_return);
        edit = (Button)findViewById(R.id.btn_activtiy_textlist_edit);

        lv = (ListView)findViewById(R.id.Lv_activtiy_textlist);
    }
    private void setListener() {
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        edit.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){
                case R.id.btn_activtiy_textlist_return:
                    i.setClass(TextListActivity.this,MainActivity.class);
                    break;
                case R.id.btn_activtiy_textlist_edit:
                    i.setClass(TextListActivity.this,DeleteTextListActivity.class);
                    break;

            }
            startActivity(i);
        }
    }
}
