package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.suishouji.R;

public class RegisterActivity extends AppCompatActivity {

    private Button fh;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        initId();
        setListener();
    }

    private void  initId(){
        fh =(Button)findViewById(R.id.btn_register_page_fh);
        register =(Button)findViewById(R.id.btn_register_page_register);
    }

    private void setListener(){
        MyListener listener = new MyListener();
        fh.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent i= new Intent();
            switch (v.getId()){
                case R.id.btn_register_page_fh:
                    i.setClass(RegisterActivity.this, LoginActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
