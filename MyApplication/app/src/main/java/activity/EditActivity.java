package activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.administrator.suishouji.R;


public class EditActivity extends Activity {


    private ImageView IvBack;

    private ImageButton IBtnSpot,IBtnNumber,IBtnLeft,IBtnRight,IBtnWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();
    }

    private void getView() {
        IvBack = (ImageView) findViewById(R.id.Iv_back);

        IBtnSpot = (ImageButton) findViewById(R.id.Ibtn_spot);
        IBtnNumber = (ImageButton) findViewById(R.id.Ibtn_number);
        IBtnLeft = (ImageButton) findViewById(R.id.Ibtn_left);
        IBtnRight = (ImageButton) findViewById(R.id.Ibtn_right);
        IBtnWord = (ImageButton) findViewById(R.id.Ibtn_word);
    }

    private void setListener() {
        EditActivity.MyListener listener = new EditActivity.MyListener();
        IBtnSpot.setOnClickListener(listener);
        IBtnNumber.setOnClickListener(listener);
        IBtnLeft.setOnClickListener(listener);
        IBtnRight.setOnClickListener(listener);
        IBtnWord.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Ibtn_spot:
                    break;
                case R.id.Ibtn_number:
                    break;
                case R.id.Ibtn_left:
                    break;
                case R.id.Ibtn_right:
                    break;
                case R.id.Ibtn_word:
                    break;
            }
        }
    }
}
