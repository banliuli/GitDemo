package activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.suishouji.R;
<<<<<<< HEAD
=======

>>>>>>> aab5674432bd3a697b4b4601c8933639dbfac263
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private Button bianji;
    private Button mine;
    private Button btn_add;
    private FileAdapter myadapter;
    private ListView lv;
    private List<File> lf = new ArrayList<File>();
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
    }


    private void getId() {
        bianji = (Button)findViewById(R.id.btn_activtiy_main_edit);
        mine = (Button)findViewById(R.id.btn_activtiy_main_mine);
        btn_add=(Button)findViewById(R.id.btn_activtiy_main_add);
    }
    private void setListener() {
        MyListener mylistener = new MyListener();
        bianji.setOnClickListener(mylistener);
<<<<<<< HEAD
        mine.setOnClickListener(mylistener);
    }
    //数据获取
    private void getData() {
        lf.add(new File(0L,"来自手机"));
    }
=======
            mine.setOnClickListener(mylistener);
            btn_add.setOnClickListener(mylistener);
        }
        //数据获取
        private void getData() {
            lf.add(new File(0L,"来自手机","2011-02-01"));
        }
>>>>>>> aab5674432bd3a697b4b4601c8933639dbfac263

        //更改界面
        private class MyListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
            switch (v.getId()){
                case R.id.btn_activtiy_main_mine:
                    i.setClass(MainActivity.this,MineActivity.class);
                    break;
<<<<<<< HEAD
                case R.id.btn_activtiy_main_edit:
                    i.setClass(MainActivity.this,DeleteFileActivity.class);
=======
                case R.id.btn_activtiy_main_add:
                    i.setClass(MainActivity.this,AddfiesActivity.class);
>>>>>>> aab5674432bd3a697b4b4601c8933639dbfac263
                    break;
            }
            startActivity(i);
        }
    }
}
