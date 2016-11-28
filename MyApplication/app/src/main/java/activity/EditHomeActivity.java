package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.administrator.suishouji.R;


public class EditHomeActivity extends Activity {

    private Button BtnCollect;
    private Button BtnMove;
    private Button BtnEdit;
    private Button BtnMore;

    private ListView LvPopup;
    private PopupWindow popupWindow;
    private View view;

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
                    popup();
                    break;
            }
        }

    }

    private void popup() {

        if (view == null) {
            //装载popup对应的界面布局
            LayoutInflater inflater = LayoutInflater.from(this);
            view = inflater.inflate(R.layout.popup,null);

            //设置popupWindow大小
            popupWindow = new PopupWindow(view,350, ActionBarOverlayLayout.LayoutParams.WRAP_CONTENT);
            //获取焦点
            popupWindow.setFocusable(true);
            //弹框位置
            popupWindow.showAtLocation(view, Gravity.RIGHT|Gravity.BOTTOM,0,0);
            //点击外面弹窗消失
            popupWindow.setOutsideTouchable(true);

        }


    }

}
