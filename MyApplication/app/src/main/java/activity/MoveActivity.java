package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;

import adapter.MoveAdapter;

public class MoveActivity extends AppCompatActivity {


    private ImageView IvBack;
    private Button BtnNew,BtnThis;
    private ListView lv;
    private MoveAdapter moveAdapter;
    private ArrayList<Mfile> files = new ArrayList<Mfile>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();

        getData();
        moveAdapter = new MoveAdapter(this,files);
        lv = (ListView) findViewById(R.id.activity_move_lv);
        lv.setAdapter(moveAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

            }
        });
    }


    /**
     *  获取界面控件
     */
    private void getView() {
        IvBack = (ImageView) findViewById(R.id.Iv_activity_move_back);
        BtnNew = (Button) findViewById(R.id.btn_activity_move_newbuilt);
        BtnThis = (Button) findViewById(R.id.btn_activity_move_selectthis);
    }

    /**
     * 注册监听事件
     */
    private void setListener() {
        IvBack.setOnClickListener(backListener);
        BtnNew.setOnClickListener(newListenenr);
        BtnThis.setOnClickListener(thisListener);
    }

    //“返回”点击事件，跳转页面
    View.OnClickListener backListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getApplication(),EditHomeActivity.class);
            startActivity(intent);
        }
    };

    /**
     * "新建文件夹"点击事件
     */
    View.OnClickListener newListenenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            inputDialog();
        }
    };

    /**
     *  “新建文件夹”弹框
     */
    private void inputDialog() {
        final EditText inputSever = new EditText(this);
        inputSever.setFocusable(true);

        AlertDialog.Builder buidler = new AlertDialog.Builder(this);
        buidler.setTitle("新建文件夹");
        buidler.setView(inputSever);
        buidler.setPositiveButton("确定",null);
        buidler.setNegativeButton("取消",null);
        AlertDialog dialog = buidler.create();
        dialog.show();
    }

    /**
     *   “选择此处”点击事件
     */
    View.OnClickListener thisListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getApplication(),EditHomeActivity.class);
            startActivity(intent);
        }
    };

    /**
     * 获取listview数据
     */
    private void getData() {
        files.add(new Mfile(0L,"文件夹1","2016-11-25  22:00",R.drawable.wenjian));
    }

}
