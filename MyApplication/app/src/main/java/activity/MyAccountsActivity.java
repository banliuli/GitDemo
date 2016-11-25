package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.suishouji.R;

public class MyAccountsActivity extends AppCompatActivity {

    private Button fh;
    private Button qh;
    private Button tc;
    private Button gd1;
    private Button gd2;
    private Button gd3;
    private Button gd4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_accounts);

        initId();
        setListener();
    }
    private void  initId(){
        fh =(Button)findViewById(R.id.btn_my_accounts_fh);
        qh =(Button)findViewById(R.id.btn_my_accounts_qh);
        tc =(Button)findViewById(R.id.btn_my_accounts_tc);
        gd1 =(Button)findViewById(R.id.btn_my_accounts_gd1);
        gd2 =(Button)findViewById(R.id.btn_my_accounts_gd2);
        gd3 =(Button)findViewById(R.id.btn_my_accounts_gd3);
        gd4 =(Button)findViewById(R.id.btn_my_accounts_gd4);
    }

    private void setListener(){
        MyListener listener = new MyListener();
        fh.setOnClickListener(listener);
        qh.setOnClickListener(listener);
        gd1.setOnClickListener(listener);
        gd2.setOnClickListener(listener);
        gd3.setOnClickListener(listener);
        gd4.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent i= new Intent();
            switch (v.getId()){
                case R.id.btn_my_accounts_fh:
                    i.setClass(MyAccountsActivity.this, LoginActivity.class);
                    break;
                case R.id.btn_my_accounts_qh:
                    i.setClass(MyAccountsActivity.this, MyAccountsActivity.class);
                    break;
                case R.id.btn_my_accounts_gd1:
                    i.setClass(MyAccountsActivity.this, NickNameActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
