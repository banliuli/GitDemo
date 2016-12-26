package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.suishouji.R;

public class CheckUpdateActivity extends AppCompatActivity {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_update);

        initId();
        setListener();
    }
    private void  initId(){
        back =(Button)findViewById(R.id.btn_check_update_back);

    }

    private void setListener(){
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent i= new Intent();
            switch (v.getId()){
                case R.id.btn_check_update_back:
                    i.setClass(CheckUpdateActivity.this,AboutActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
