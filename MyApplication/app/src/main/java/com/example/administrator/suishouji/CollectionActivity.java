package com.example.administrator.suishouji;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CollectionActivity extends Activity {
    private ImageView mIv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_collection);
        getView();
        setListener();
    }
    private void getView(){
        mIv_back=(ImageView)findViewById(R.id.Iv_back);
    }
    private void setListener(){
        MyListener listener=new MyListener();
        mIv_back.setOnClickListener(listener);
    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Iv_back:
                    Intent i=new Intent(CollectionActivity.this,MineActivity.class);
                    startActivity(i);
                    break;
            }
        }
      }
    }
