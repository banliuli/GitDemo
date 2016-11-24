package com.example.administrator.suishouji;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by lenovo on 2016/11/18.
 */
public class MineActivity extends Fragment {

    private View view;
    private Context context;
    private Button btncollect;
    private Button btntext;
    private Button btnpicture;
    private Button btnsatisfy;
    private Button btnshezhi;
    private LinearLayout llshoucang;

    public Context getContext(){
        return context;

    }
    public void setContext(Context context) {
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.layout_mine,container,false);
        initID();
        setListener();
        return view;

    }



    private void initID() {

        btncollect = (Button) view.findViewById(R.id.collect);
        btntext = (Button)view.findViewById(R.id.text);
        btnpicture = (Button)view.findViewById(R.id.picture);
        btnsatisfy = (Button)view.findViewById(R.id.satisfy);
        btnshezhi=(Button)view.findViewById(R.id.btnshezhi);
        llshoucang=(LinearLayout)view.findViewById(R.id.llshoucang);

    }
    private void setListener() {
        MyListener listener = new MyListener();
        btncollect.setOnClickListener(listener);
        btntext.setOnClickListener(listener);
        btnpicture.setOnClickListener(listener);
        btnsatisfy.setOnClickListener(listener);
        llshoucang.setOnClickListener(listener);

    }


    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){
                case R.id.collect:

                    break;
                case R.id.text:
                    i.setClass(getActivity(),TextActivity.class);
                    break;
                case R.id.picture:
                    i.setClass(getActivity(),PictureActivity.class);
                    break;
                case R.id.satisfy:
                    i.setClass(getActivity(),SearchActivity.class);
                    break;
                case R.id.btnshezhi:
                    i.setClass(getActivity(),SettingActivity.class);
                    break;
                case R.id.llshoucang:
                    i.setClass(getActivity(),CollectionActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
