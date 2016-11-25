package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.suishouji.R;

public class EditHomeActivity extends AppCompatActivity {

    private Button BtnCollect;
    private Button BtnMove;
    private Button BtnEdit;
    private Button BtnMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edithome);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();
    }

    private void getView() {
        BtnCollect = (Button) findViewById(R.id.btn_collect);
        BtnMove = (Button) findViewById(R.id.btn_move);
        BtnEdit = (Button) findViewById(R.id.btn_edit);
        BtnMore = (Button) findViewById(R.id.btn_more);
    }

    private void setListener() {
        MyListener listener = new MyListener();
        BtnCollect.setOnClickListener(listener);
        BtnMove.setOnClickListener(listener);
        BtnEdit.setOnClickListener(listener);
        BtnMore.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_move:
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(),MoveActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_edit:
                    Intent intent2 = new Intent();
                    intent2.setClass(getApplicationContext(),EditActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.btn_more:
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(),v);
                    MenuInflater inflater = popupMenu.getMenuInflater();
                    //添加单击事件
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            return false;
                        }
                    });
                    break;
            }
        }
    }
}
