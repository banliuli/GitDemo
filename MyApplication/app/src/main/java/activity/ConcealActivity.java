package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.suishouji.R;

public class ConcealActivity extends Activity {
    private ImageView mIv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_conceal);
        mIv_back=(ImageView)findViewById(R.id.img_activity_conceal_back);
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ConcealActivity.this,SettingActivity.class);
                startActivity(i);
            }
        });
    }
}
