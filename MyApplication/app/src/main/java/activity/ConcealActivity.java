package activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import uil.ConcealPwd;
import com.example.administrator.suishouji.R;
import uil.ToggleStatus;

public class ConcealActivity extends Activity {
    private ImageView mIv_back;
    private ToggleButton mTogBtn;
    ToggleStatus status = new ToggleStatus();

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private boolean first;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_conceal);
        getView();
        MyListener();
        setData();
        mTogBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    mTogBtn.setBackgroundResource(R.drawable.switch_btn);
                    Intent i1 = new Intent(ConcealActivity.this, ConcealPwd.class);
                    startActivity(i1);
                    status.setOne(isChecked);
                }
                else{
                    mTogBtn.setBackgroundResource(R.drawable.switch_btn);
                    status.setOne(false);
                }
            }
        });// 添加监听事件
    }
    private void getView() {
        mIv_back = (ImageView) findViewById(R.id.img_activity_conceal_back);
        mTogBtn = (ToggleButton) findViewById(R.id.mTogBtn); // 获取到控件
    }
    private void MyListener() {
        MyListener listener = new MyListener();
        mIv_back.setOnClickListener(listener);
        mTogBtn.setOnClickListener(listener);
    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_activity_conceal_back:
                    Intent i = new Intent(ConcealActivity.this, SettingActivity.class);
                    startActivity(i);
                    break;
            }
        }
    }
    private void setData(){
        preferences = getSharedPreferences("togglebuttonstatus", Context.MODE_PRIVATE);
		/*
		 * 判断是不是第一次运行该程序
		 * （因为第一次运行时，SharedPreferences是没有保存"first"的，
		 * "first"不存在即为null，默认返回自己设置的参数true）
		 *
		 */
        first = preferences.getBoolean("first", true);
        editor = preferences.edit();
        if(first){
            getStatus();
        }
        else{
            status.one = preferences.getBoolean("s_one", false);
            setToggButonStatus(status);
        }
    }
    /*
	 * 根据保存的参数设置每个ToggleButton的状态
	 */
    private void setToggButonStatus(ToggleStatus data){
        mTogBtn.setChecked(data.one);
    }
    /*
	 * 获取每个ToggleButton的状态，并保存在status里面
	 */
    private void getStatus(){
        status.one = mTogBtn.isChecked();
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(first){
            editor.putBoolean("first", false);
        }
        //关闭之前把数据写进去
        editor.putBoolean("s_one", status.one);
        editor.commit();
    }
}

