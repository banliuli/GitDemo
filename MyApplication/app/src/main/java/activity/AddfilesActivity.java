package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.suishouji.R;

/**
 * Created by lenovo on 2016/11/29.
 */
public class AddfilesActivity extends Activity {
    private ImageView mIv_back;
    private Button BtnConfirm,BtnCancel;
    private EditText EdName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setfiles);

        mIv_back=(ImageView)findViewById(R.id.img_activity_addfiles_back);
        BtnConfirm = (Button) findViewById(R.id.btn_layout_setfiles_confirm);
        BtnCancel = (Button) findViewById(R.id.btn_layout_setfiles_cancel);
        EdName = (EditText) findViewById(R.id.Ed_layout_setfiles_name);

        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AddfilesActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        EdName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        BtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AddfilesActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
