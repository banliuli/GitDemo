package activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.suishouji.R;

/**
 * Created by lenovo on 2016/11/18.
 */
public class MineActivity extends AppCompatActivity {

    private Button btnshezhi;
    private Button btncollect;
    private Button btntext;
    private Button btnpicture;
    private Button btnsatisfy;
    private Button btnsuishouji;
    private Button btnshezhi;
    private LinearLayout llshoucang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mine);
        InitID();
        setListener();
    }
    private void InitID() {
        btnshezhi = (Button)findViewById(R.id.btn_mine_shezhi);
        btncollect = (Button)findViewById(R.id.btn_mine_collect);
        btntext = (Button)findViewById(R.id.btn_mine_text);
        btnpicture = (Button)findViewById(R.id.btn_mine_picture);
        btnsatisfy = (Button)findViewById(R.id.btn_mine_satisfy);
        btnsuishouji = (Button)findViewById(R.id.btn_mine_suishouji);



    private void setListener() {
        MyListener listener = new MyListener();
        btnshezhi.setOnClickListener(listener);
        btncollect.setOnClickListener(listener);
        btntext.setOnClickListener(listener);
        btnpicture.setOnClickListener(listener);
        btnsatisfy.setOnClickListener(listener);

        btnsuishouji.setOnClickListener(listener);
        llshoucang.setOnClickListener(listener);

    }


    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){
                case R.id.btn_mine_suishouji:

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
