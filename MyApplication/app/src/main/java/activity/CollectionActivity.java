package activity;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.Collection;

import DBSql.DBCollect;
import DBSql.DBManager;
import adapter.CollectionAdapter;
import adapter.TextListAdapter;


public class CollectionActivity extends Activity {
    private ListView lv;
    private DBCollect dm;
    private Cursor cursor;
    private CollectionAdapter adapter;
    private ImageView mIv_back;
    private TextView mTv_edit;
    public static final String CHECK_STATE = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_collection);
        getView();
        setListener();
        dm = new DBCollect(this);
        initAdapter();
        lv.setAdapter(adapter);
        //给ListView设置item点击监听器，实现点击效果
        lv.setOnItemClickListener(new myOnItemClickListener());
//        //实现长按出现菜单
//        lv.setOnCreateContextMenuListener(new myOnCreateContextMenuListener());
    }
    //在listview中显示数据库中的对象
    private void initAdapter() {
        dm.open();//打开数据库操作对象
        cursor = dm.selectAll();//获取所有数据
        cursor.moveToFirst();//将游标移动到第一条数据，使用前必须调用
        int count = cursor.getCount();//个数
        ArrayList<String> items = new ArrayList<String>();
        ArrayList<String> text = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            items.add(cursor.getString(cursor.getColumnIndex("title")));
            text.add(cursor.getString(cursor.getColumnIndex("content")));
            cursor.moveToNext();//将游标指向下一个
        }
        dm.close();//关闭数据操作对象
        adapter = new CollectionAdapter(this, items, text);//创建数据源
    }
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
            intent.setClass(CollectionActivity.this, EditHomeActivity.class);
            CollectionActivity.this.startActivity(intent);
        }
    }
//    public boolean onContextItemSelected(MenuItem item){
//        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
//        dm.open();
//        switch(item.getItemId()){
//            case 0://删除
//                Log.i("log", "selectItem---->"+item.getItemId());
//                try{
//                    Log.i("log", "cursor ready move ");
//                    Log.i("log", "menuInfo position "+menuInfo.position);
//
//                    cursor.moveToPosition(menuInfo.position);
//
//                    Log.i("log", "cursor move success");
//                    int i = dm.delete(Long.parseLong(cursor.getString(cursor.getColumnIndex("_id"))));//删除数据
//
//                    adapter.removeListItem(menuInfo.position);//删除数据
//                    adapter.notifyDataSetChanged();//通知数据源，数据已经改变，刷新界面
//                    dm.close();
//                }catch(Exception ex){
//                    ex.printStackTrace();
//                }
//                break;
//            default:
//        }
//        dm.close();
//        return super.onContextItemSelected(item);
//    }
//    //选择菜单
//    public class myOnCreateContextMenuListener implements View.OnCreateContextMenuListener {
//
//        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//            // TODO Auto-generated method stub
//            final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//            menu.setHeaderTitle("");
//            //设置选项
//            Log.i("log", "chooseing menu");
//            menu.add(0,0,0,"删除");
//        }
//    }
    private void getView(){
        mIv_back=(ImageView)findViewById(R.id.img_activity_collection_back);
        mTv_edit = (TextView)findViewById(R.id.activity_collection_edit);
        lv = (ListView)findViewById(R.id.Lv_activtiy_collection);
    }
    private void setListener(){
        MyListener listener=new MyListener();
        mIv_back.setOnClickListener(listener);
        mTv_edit.setOnClickListener(listener);
    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_activity_collection_back:
                    Intent i=new Intent(CollectionActivity.this,MineActivity.class);
                    startActivity(i);
                    break;
                case R.id.activity_collection_edit:
//                    Intent i1 = new Intent(CollectionActivity.this,TextListActivity.class);
//                    startActivity(i1);
//                    break;
                case R.id.Lv_activtiy_collection:
                    Intent i2 = new Intent(CollectionActivity.this,EditHomeActivity.class);
                    startActivity(i2);
                    break;
            }
        }
      }
    }
