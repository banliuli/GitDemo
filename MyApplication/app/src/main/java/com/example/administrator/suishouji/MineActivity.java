package com.example.administrator.suishouji;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by lenovo on 2016/11/18.
 */
public class MineActivity extends Fragment {
    private LinearLayout llshoucang;
    private LinearLayout lltext;
    private LinearLayout llpicture;
    private LinearLayout llright;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initID();
        setListener();
        return inflater.inflate(R.layout.layout_mine,container,false);
    }
    private void initID() {
        llshoucang = (LinearLayout) getView().findViewById(R.id.llshoucang);
        lltext = (LinearLayout)getView().findViewById(R.id.lltext);
        llpicture = (LinearLayout)getView().findViewById(R.id.llpicture);
        llright = (LinearLayout)getView().findViewById(R.id.llright);


    }
    private void setListener() {
        MyListener listener = new MyListener();
        llshoucang.setOnClickListener(listener);
        lltext.setOnClickListener(listener);
        llpicture.setOnClickListener(listener);
        llright.setOnClickListener(listener);


    }


    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){

            }
        }
    }
}
