package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.suishouji.R;

public class SettingActivity extends Activity {
    private TextView mTv_conceal,mTv_opinion,mTv_about;
    private ImageView mIv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting);
        mTv_conceal=(TextView)findViewById(R.id.Tv_conceal);
        mTv_opinion=(TextView)findViewById(R.id.Tv_opinion);
        mTv_about=(TextView)findViewById(R.id.Tv_about);
        mIv_back=(ImageView)findViewById(R.id.img_activity_setting_back);

        mTv_conceal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingActivity.this,ConcealActivity.class);
                startActivity(i);
            }
        });
        mTv_opinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingActivity.this,OpinionActivity.class);
                startActivity(i);
            }
        });
        mTv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingActivity.this,MineActivity.class);
                startActivity(i);
            }
        });
    }
}
