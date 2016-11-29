package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.suishouji.R;

public class MoveActivity extends AppCompatActivity {


    private ImageView IvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();
    }

    //获取界面控件
    private void getView() {
        IvBack = (ImageView) findViewById(R.id.Iv_activity_move_back);
    }

    //注册监听事件
    private void setListener() {
        IvBack.setOnClickListener(backListener);
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
}
