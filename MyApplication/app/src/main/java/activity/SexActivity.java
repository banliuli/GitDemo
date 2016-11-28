package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.suishouji.R;

public class SexActivity extends AppCompatActivity {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sex_page);

        initId();
        setListener();
    }
    private void  initId(){
        back =(Button)findViewById(R.id.btn_sex_page_back);

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
                case R.id.btn_sex_page_back:
                    i.setClass(SexActivity.this, MyAccountsActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
