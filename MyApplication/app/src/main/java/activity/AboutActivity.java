package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.suishouji.R;

public class AboutActivity extends Activity {

    private ImageView back;
    private Button gd1;
    private Button gd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initId();
        setListener();
    }
    private void initId() {
        back = (ImageView) findViewById(R.id.img_activity_about_back);
        gd1 = (Button) findViewById(R.id.btn_activity_about_gd1);
        gd2 = (Button) findViewById(R.id.btn_activity_about_gd2);
    }
    private void setListener() {
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        gd1.setOnClickListener(listener);
        gd2.setOnClickListener(listener);
    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()) {
                case R.id.img_activity_about_back:
                    i.setClass(AboutActivity.this, SettingActivity.class);
                    break;
                case R.id.btn_activity_about_gd1:
                    i.setClass(AboutActivity.this, CheckUpdateActivity.class);
                    break;
                case R.id.btn_activity_about_gd2:
                    i.setClass(AboutActivity.this, SearchActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
