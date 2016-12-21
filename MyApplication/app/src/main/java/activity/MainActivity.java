package activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Button btn_add;
    private Button mine;
    private FileAdapter myadapter;
    private ListView lv;
    private List<File> lf = new ArrayList<File>();
    private Button edit;
    private ImageView login;
    private ImageView refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getId();
        setListener();
        getData();
        myadapter = new FileAdapter(this,lf);
        lv = (ListView)findViewById(R.id.Lv_activtiy_main);
        lv.setAdapter(myadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String j = String.valueOf(i);
                if (null!= j){
                    Intent k = new Intent();
                    k.setClass(MainActivity.this,TextListActivity.class);
                    startActivity(k);
                }
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {

                // TODO 自动生成的方法存根
                Button btn= (Button) arg1.findViewById(R.id.btn_item_file_delete);
                if(btn.getVisibility() == View.INVISIBLE){
                    btn.setVisibility(View.VISIBLE);
                }
                else{
                    btn.setVisibility(View.INVISIBLE);
                }
                return true;//关于返回值，若返回False，则是当长按时，既调用onItemLongClick，同时调用onItemLongClick后
                //还会调用onItemClick，就是说会同时调用onItemLongClick，和onItemClick，
                //若返回true，则只调用onItemLongClick
            }
        });
    }


    private void getId() {
        login=(ImageView) findViewById(R.id.iv_activtiy_main_login);
        mine = (Button)findViewById(R.id.btn_activtiy_main_mine);
        edit = (Button)findViewById(R.id.btn_activity_main_edit1);
        btn_add=(Button)findViewById(R.id.btn_activtiy_main_add);
        refresh = (ImageView) findViewById(R.id.iv_activtiy_main_refresh);
    }
    private void setListener() {
        MyListener mylistener = new MyListener();
        login.setOnClickListener(mylistener);
        mine.setOnClickListener(mylistener);
        edit.setOnClickListener(mylistener);
        btn_add.setOnClickListener(mylistener);
        refresh.setOnClickListener(mylistener);
    }
    //数据获取
    private void getData() {
        lf.add(new File(0L,"来自手机"));
    }
    //更改界面
    private class MyListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
            switch (v.getId()){
                case R.id.iv_activtiy_main_login:
                    i.setClass(MainActivity.this,Login.class);
                    break;
                case R.id.btn_activtiy_main_mine:
                    i.setClass(MainActivity.this,MineActivity.class);
                    break;
                case R.id.btn_activtiy_main_add:
                    i.setClass(MainActivity.this, AddfilesActivity.class);
                    break;
                case R.id.btn_activity_main_edit1:
                    i.setClass(MainActivity.this,EditAddActivity.class);
                    break;
                case R.id.iv_activtiy_main_refresh:
                    finish();
                    i.setClass(MainActivity.this,MainActivity.class);
                    break;
            }
            startActivity(i);
        }
    }


}
