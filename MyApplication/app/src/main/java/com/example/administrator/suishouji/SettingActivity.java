package com.example.administrator.suishouji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingActivity extends Activity {
    private TextView mTv_conceal,mTv_opinion,mTv_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting);
        mTv_conceal=(TextView)findViewById(R.id.Tv_conceal);
        mTv_opinion=(TextView)findViewById(R.id.Tv_opinion);
        mTv_about=(TextView)findViewById(R.id.Tv_about);

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
                Intent i=new Intent(SettingActivity.this,AboutActivity.class);
                startActivity(i);
            }
        });

    }
}
