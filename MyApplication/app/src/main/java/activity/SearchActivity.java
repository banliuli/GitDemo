package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.suishouji.R;

public class SearchActivity extends Activity {

    private ImageView mIv_back;
    private Button btn_go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getView();
        setListener();
    }
    private void getView(){
        mIv_back=(ImageView)findViewById(R.id.back);
        btn_go =  (Button)findViewById(R.id.activity_search_go);
    }
    private void setListener(){
        MyListener listener=new MyListener();
        mIv_back.setOnClickListener(listener);
        btn_go.setOnClickListener(listener);
    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    Intent i=new Intent(SearchActivity.this,MineActivity.class);
                    startActivity(i);
                    break;
                case R.id.activity_search_go:
                    Toast.makeText(SearchActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}