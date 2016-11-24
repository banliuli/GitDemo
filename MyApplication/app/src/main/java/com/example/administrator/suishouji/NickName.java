package com.example.administrator.suishouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NickName extends AppCompatActivity {

    private Button fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nickname);

        initId();
        setListener();
    }
    private void  initId(){
        fh =(Button)findViewById(R.id.btn_nickname_fh);

    }

    private void setListener(){
        MyListener listener = new MyListener();
        fh.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent i= new Intent();
            switch (v.getId()){
                case R.id.btn_nickname_fh:
                    i.setClass(NickName.this,MyAccounts.class);
                    break;
            }
            startActivity(i);
        }
    }
}
