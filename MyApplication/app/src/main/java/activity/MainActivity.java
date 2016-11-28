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
import java.util.ArrayList;
import java.util.List;

=======
<<<<<<< HEAD
public class MainActivity extends AppCompatActivity {
=======
>>>>>>> 836383a62547711c5a067523ef929415b9e82b26

public class MainActivity extends Activity {
>>>>>>> 9f13dc2cc2451b5a67c17fc2f49ae77187f94d11

    private Button bianji;
    private Button mine;
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
    }
    private void setListener() {
        MyListener mylistener = new MyListener();
        bianji.setOnClickListener(mylistener);
        mine.setOnClickListener(mylistener);
    }
    //数据获取
    private void getData() {
        lf.add(new File(0L,"来自手机","2011-02-01"));
    }

    //更改界面
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){
                case R.id.btn_activtiy_main_mine:
                    i.setClass(MainActivity.this,MineActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
