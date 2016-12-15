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
    private Button text;
    private Button photo;
    private Button video;

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
        text = (Button)findViewById(R.id.btn_text);
        photo = (Button)findViewById(R.id.btn_photo);
        video = (Button)findViewById(R.id.btn_shipin);
    }

    private void setListener(){
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        error.setOnClickListener(listener);
        text.setOnClickListener(listener);
        photo.setOnClickListener(listener);
        video.setOnClickListener(listener);
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
                    i.setClass(EditAddActivity.this,MainActivity.class);
                    break;
                case R.id.btn_text:
                    i.setClass(EditAddActivity.this,EditActivity.class);
                    break;
                case R.id.btn_photo:
                    i.setClass(EditAddActivity.this,EditActivity.class);
                    break;
                case R.id.btn_shipin:
                    i.setClass(EditAddActivity.this,EditActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
