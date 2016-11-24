package com.example.administrator.suishouji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EditActivity extends AppCompatActivity {

    private ImageView IvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //获取界面控件
        getView();
        //注册监听事件
        setListener();
    }

    private void getView() {
        IvBack = (ImageView) findViewById(R.id.Iv_back);
    }

    private void setListener() {
        IvBack.setOnClickListener(backListener);
    }

    //“返回”点击事件
    View.OnClickListener backListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getApplication(),EditHomeActivity.class);
            startActivity(intent);
        }
    };
}
