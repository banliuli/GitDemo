package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.suishouji.R;

public class OpinionActivity extends Activity {
    private ImageView mIv_back1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_opinion);
        mIv_back1=(ImageView)findViewById(R.id.img_activity_opinion_back);
        mIv_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OpinionActivity.this,SettingActivity.class);
                startActivity(i);
            }
        });
    }
}
