package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.suishouji.R;
import data.UserData;
import data.UserDataManager;

public class RegisterActivity extends AppCompatActivity {

    private Button fh;
    private Button register;
    private EditText uname;
    private EditText pwd;
    private EditText spwd;
    private UserDataManager mUserDataManager;         //用户数据管理类
    private Animation scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        initId();
        setListener();

       // scale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scaleanim);
        //建立本地数据库
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
    }

    private void initId() {
        fh = (Button) findViewById(R.id.btn_register_page_fh);
        register = (Button) findViewById(R.id.btn_register_page_register);
        uname = (EditText) findViewById(R.id.et_register_page_uname);
        pwd = (EditText) findViewById(R.id.et_register_page_pwd);
        spwd = (EditText) findViewById(R.id.et_register_page_spwd);
    }
    private void setListener(){
        MyListener listener = new MyListener();
        register.setOnClickListener(listener);
        fh.setOnClickListener(listener);
    }


    //绑定注册监听器

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent i= new Intent();
            switch (v.getId()){

                case R.id.btn_register_page_fh:
                    i.setClass(RegisterActivity.this,Login.class);
                    startActivity(i);
                    break;

            }
            register_check();
        }
    }


    //确认按钮的监听事件
    public void register_check(){
        if(isUserNameAndPwdValid()){
            String username=uname.getText().toString().trim();
            String pwd1=pwd.getText().toString().trim();
            String pwd2=spwd.getText().toString().trim();
            //检查用户是否存在
            int count=mUserDataManager.findUserByName(username);
            //用户已经存在时返回，给出提示文字
            if (count>0){
                Toast.makeText(this,"用户名已存在！", Toast.LENGTH_SHORT).show();
                return;
            }
            //两次密码输入不一样
            if (pwd1.equals(pwd2)==false){
                Toast.makeText(this,"密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                return;
            }else {
                UserData mUser= new UserData(username,pwd1);
                mUserDataManager.openDataBase();
                long flag = mUserDataManager.insertUserData(mUser);//新建用户信息
                if(flag == -1){
                    Toast.makeText(this,"注册失败", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this,"注册成功", Toast.LENGTH_SHORT).show();

                    Intent i =new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }

        }

    }
    public boolean isUserNameAndPwdValid(){
        if (uname.getText().toString().trim().equals("")){
            Toast.makeText(this,"用户名为空，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }else if(pwd.getText().toString().trim().equals("")){
            Toast.makeText(this,"密码为空，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }else if (spwd.getText().toString().trim().equals("")){
            Toast.makeText(this,"确认密码为空，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
