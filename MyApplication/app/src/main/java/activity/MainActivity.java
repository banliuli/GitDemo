package activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
<<<<<<< HEAD

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.List;
=======
import java.util.ArrayList;
import java.util.List;
import com.example.administrator.suishouji.R;
<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> f98e0b6d55d46f88f20afc919b55300939a1cd11
>>>>>>> 69eb3d94983f904f320574ae467880da912cbae1




<<<<<<< HEAD
=======

<<<<<<< HEAD
>>>>>>> 760ff737f9eb6be546fe6e0ed9a8b66c75d8b227

=======
public class MainActivity extends AppCompatActivity {
>>>>>>> f98e0b6d55d46f88f20afc919b55300939a1cd11

>>>>>>> 69eb3d94983f904f320574ae467880da912cbae1
    private Button btn_add;
    private Button bianji;
    private Button mine;
    private FileAdapter myadapter;
    private ListView lv;
    private List<File> lf = new ArrayList<File>();
    private Button edit;
    private Button login;

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
        login=(Button)findViewById(R.id.btn_activtiy_main_login);
        bianji = (Button)findViewById(R.id.btn_activtiy_main_edit);
        mine = (Button)findViewById(R.id.btn_activtiy_main_mine);
        edit = (Button)findViewById(R.id.btn_activtiy_main_edit1);
        btn_add=(Button)findViewById(R.id.btn_activtiy_main_add);

    }
    private void setListener() {
        MyListener mylistener = new MyListener();
        login.setOnClickListener(mylistener);
        bianji.setOnClickListener(mylistener);
        mine.setOnClickListener(mylistener);
        edit.setOnClickListener(mylistener);
        btn_add.setOnClickListener(mylistener);
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
                case R.id.btn_activtiy_main_login:
                    i.setClass(MainActivity.this,LoginActivity.class);
                    break;
                case R.id.btn_activtiy_main_mine:
                    i.setClass(MainActivity.this,MineActivity.class);
                    break;
                case R.id.btn_activtiy_main_edit:
                    i.setClass(MainActivity.this,DeleteFileActivity.class);
                    break;
                case R.id.btn_activtiy_main_add:
                    i.setClass(MainActivity.this, AddfilesActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
