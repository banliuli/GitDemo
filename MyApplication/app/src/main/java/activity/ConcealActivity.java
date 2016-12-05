package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.administrator.suishouji.R;

public class ConcealActivity extends Activity {
    private ImageView mIv_back;
    private ToggleButton mTogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_conceal);
        getView();
        MyListener();


        mTogBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    mTogBtn.setBackgroundResource(R.drawable.switch_btn);


                }else{
                    //未选中
                    mTogBtn.setBackgroundResource(R.drawable.switch_btn);
                }
            }
        });// 添加监听事件
    }
    private void getView(){
        mIv_back=(ImageView)findViewById(R.id.img_activity_conceal_back);
        mTogBtn = (ToggleButton) findViewById(R.id.mTogBtn); // 获取到控件
    }
    private void MyListener(){
        MyListener listener=new MyListener();
        mIv_back.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_activity_conceal_back:
                    Intent i=new Intent(ConcealActivity.this,SettingActivity.class);
                    startActivity(i);
                    break;
            }
        }
    }

}
