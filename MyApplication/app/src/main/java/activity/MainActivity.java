package activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.administrator.suishouji.R;

public class MainActivity extends AppCompatActivity {

    private Button bianji;
    private Button mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getId();
        setListener();
    }

    private void getId() {
        bianji = (Button)findViewById(R.id.bianji);
        mine = (Button)findViewById(R.id.mine);
    }
    private void setListener() {
        MyListener mylistener = new MyListener();
        bianji.setOnClickListener(mylistener);
        mine.setOnClickListener(mylistener);
    }
    //更改界面
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){
                case R.id.mine:
                    i.setClass(MainActivity.this,MineActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
