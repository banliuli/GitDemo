package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.suishouji.R;

public class MoveActivity extends AppCompatActivity {


    private ImageView IvBack;
    private Button BtnNew, BtnThis;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();

    }


    /**
     * 获取界面控件
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
            intent.setClass(getApplication(), EditHomeActivity.class);
            startActivity(intent);
        }
    };

    /**
     * "新建文件夹"点击事件
     */
    View.OnClickListener newListenenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //建立文件夹弹框
            final EditText inputSever = new EditText(getApplication());
            inputSever.setFocusable(true);

            AlertDialog.Builder buidler = new AlertDialog.Builder(getApplication());
            buidler.setTitle("新建文件夹");
            buidler.setView(inputSever);
            buidler.setPositiveButton("确定", null);
            buidler.setNegativeButton("取消", null);
            AlertDialog dialog = buidler.create();
            dialog.show();

        }
    };

    /**
     * “选择此处”点击事件
     */
    View.OnClickListener thisListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getApplication(), EditHomeActivity.class);
            startActivity(intent);
        }
    };
}

