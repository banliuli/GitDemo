package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.suishouji.R;

public class EditAddActivity extends AppCompatActivity {

    private Button back;
    private Button error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_add);

        initId();
        setListener();
    }
    private void  initId(){
        back =(Button)findViewById(R.id.btn_edit_add_back);
        error =(Button)findViewById(R.id.btn_edit_add_error);
    }

    private void setListener(){
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        error.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent i= new Intent();
            switch (v.getId()){
                case R.id.btn_edit_add_back:
                    i.setClass(EditAddActivity.this,MainActivity.class);
                    break;
                case R.id.btn_edit_add_error:
                    i.setClass(EditAddActivity.this,PaperBgActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
