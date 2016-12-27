package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.suishouji.R;

/**
 * Created by lenovo on 2016/12/6.
 */
public class ConcealPwd extends Activity {
    private Button mfinish;
    private EditText Et_set,Et_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_conceal_pwd);
        getView();
        MyListener();
    }
    private void getView(){
        mfinish=(Button)findViewById(R.id.btn_concealpwd_activity_finish);
        Et_set=(EditText)findViewById(R.id.Et_concealpwd_set);
        Et_sure=(EditText)findViewById(R.id.Et_concealpwd_sure);
    }
    private void MyListener(){
        MyListener listener=new MyListener();
        mfinish.setOnClickListener(listener);
    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_concealpwd_activity_finish:
                    Intent i = new Intent(ConcealPwd.this, ConcealActivity.class);
                    i.putExtra("PWD",Et_set.getText().toString());
                    i.putExtra("PWD_SURE",Et_sure.getText().toString());
                    String pwd=i.getStringExtra("PWD");
                    String surepwd=i.getStringExtra("PWD_SURE");
                    if(pwd.equals(surepwd)&&pwd.length()==6) {
                        Toast.makeText(ConcealPwd.this, "设置成功!", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                    else if(pwd.length()<6&&pwd.length()>=0){
                        Toast.makeText(ConcealPwd.this, "长度不为6!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ConcealPwd.this, "密码输入不一致!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

}
