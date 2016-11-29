package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.suishouji.R;

public class TextActivity extends Activity {

    private ImageView mIv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        getView();
        setListener();
    }
    private void getView(){
        mIv_back=(ImageView)findViewById(R.id.img_activity_text_back);
    }
    private void setListener(){
        MyListener listener=new MyListener();
        mIv_back.setOnClickListener(listener);
    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_activity_text_back:
                    Intent i=new Intent(TextActivity.this,MineActivity.class);
                    startActivity(i);
                    break;
            }
        }
    }
}
