package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.suishouji.R;

/**
 * Created by lenovo on 2016/11/18.
 */
public class MineActivity extends Activity {

    private Button btncollect;
    private Button btntext;
    private Button btnpicture;
    private Button btnsatisfy;
    private Button btnsuishouji;
    private Button btnshezhi;
    private Button login2;
    private ImageView login1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mine);
        InitID();
        setListener();

    }
    private void InitID() {
        login1 = (ImageView)findViewById(R.id.Iv_layout_mine_login1);
        login2 = (Button)findViewById(R.id.btn_layout_mine_login2);
        btnshezhi = (Button)findViewById(R.id.btn_mine_shezhi);
        btncollect = (Button)findViewById(R.id.btn_mine_collect);
        btntext = (Button)findViewById(R.id.btn_mine_text);
        btnpicture = (Button)findViewById(R.id.btn_mine_picture);
        btnsatisfy = (Button)findViewById(R.id.btn_mine_satisfy);
        btnsuishouji = (Button)findViewById(R.id.btn_mine_suishouji);
    }



    private void setListener() {
        MyListener listener = new MyListener();
        login1.setOnClickListener(listener);
        login2.setOnClickListener(listener);
        btnshezhi.setOnClickListener(listener);
        btncollect.setOnClickListener(listener);
        btntext.setOnClickListener(listener);
        btnpicture.setOnClickListener(listener);
        btnsatisfy.setOnClickListener(listener);
        btnsuishouji.setOnClickListener(listener);

    }


    private class MyListener implements View.OnClickListener{
        private boolean flag;

        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){
                case R.id.Iv_layout_mine_login1:
                    i.setClass(MineActivity.this,MyAccountsActivity.class);
                    break;
                case R.id.btn_layout_mine_login2:

//                    if(flag==false){
                        i.setClass(MineActivity.this,Login.class);
//                    }else{
//                        i.setClass(MineActivity.this,MyAccountsActivity.class);
//                    }
                    break;
                case R.id.btn_mine_suishouji:
                    i.setClass(MineActivity.this,MainActivity.class);
                    break;
                case R.id.btn_mine_collect:
                    i.setClass(MineActivity.this,CollectionActivity.class);
                    break;
                case R.id.btn_mine_text:
                    i.setClass(MineActivity.this,TextActivity.class);
                    break;
                case R.id.btn_mine_picture:
                    i.setClass(MineActivity.this,PictureActivity.class);
                    break;
                case R.id.btn_mine_satisfy:
                    i.setClass(MineActivity.this,SearchActivity.class);
                    break;
                case R.id.btn_mine_shezhi:
                    i.setClass(MineActivity.this,SettingActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
