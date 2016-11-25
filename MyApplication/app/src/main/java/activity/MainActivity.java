package activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.suishouji.R;


public class MainActivity extends AppCompatActivity {

    private Button bianji;
    private Button mine;
    private LinearLayout ll;
    //声明fragment属性
    private SuishoujiActivity Suishouji;
    private MineActivity Mine;
    private Button suishouji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getId();
        setListener();
    }

    private void getId() {
        ll = (LinearLayout)findViewById(R.id.ll);
        suishouji = (Button)findViewById(R.id.suishouji1);
        bianji = (Button)findViewById(R.id.bianji1);
        mine = (Button)findViewById(R.id.mine);
    }
    private void setListener() {
        MyListener mylistener = new MyListener();
        suishouji.setOnClickListener(mylistener);
        bianji.setOnClickListener(mylistener);
        mine.setOnClickListener(mylistener);
    }
    //更改界面
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //获取fragmentmanager对象
            android.app.FragmentManager fm = getFragmentManager();
            //获取fragmenttratransaction对象
            android.app.FragmentTransaction transaction = fm.beginTransaction();
            switch (v.getId()){
                case R.id.suishouji1:
                    if (Suishouji==null){
                        Suishouji = new SuishoujiActivity();
                    }
                    //设置页面
                    transaction.replace(R.id.contaner,Suishouji);
                    break;
                case R.id.mine:
                    if (Mine==null){
                        Mine = new MineActivity();
                    }
                    //设置页面
                    transaction.replace(R.id.contaner,Mine);;
                    break;
            }
            transaction.commit();
            ll.invalidate();
        }
    }
}
