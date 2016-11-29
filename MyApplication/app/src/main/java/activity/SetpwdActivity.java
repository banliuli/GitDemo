package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.suishouji.R;

/**
 * Created by lenovo on 2016/11/29.
 */
public class SetpwdActivity extends Activity {
    private ImageView mIv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setpwd);
        mIv_back=(ImageView)findViewById(R.id.Iv_back);
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SetpwdActivity.this,EditHomeActivity.class);
                startActivity(i);
            }
        });
    }
}
