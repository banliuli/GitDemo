package activity;

/**
 * Created by lenovo on 2016/11/28.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.suishouji.R;
import data.UserDataManager;

public class Login extends AppCompatActivity {                 //登录界面活动

    public int pwdresetFlag=0;
    private Button login;
    private Button register;
    private EditText etpwd;
    private EditText etname;
    private CheckBox box;
    private SharedPreferences login_sp;
    private String userNameValue,passwordValue;

    private View loginView;                           //登录
    private View loginSuccessView;
    private TextView loginSuccessShow;
    private TextView mChangepwdText;
    private UserDataManager mUserDataManager;         //用户数据管理类
    private Button resetpwd;
    private Button cancle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        //通过id找到相应的控件
        initId();
        //设置监听
        setListener();
        //设置密码为隐藏
        etpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

        login_sp = getSharedPreferences("userInfo", 0);
        String name=login_sp.getString("USER_NAME", "");
        String pwd =login_sp.getString("PASSWORD", "");
        boolean choseRemember =login_sp.getBoolean("box", false);
        //boolean choseAutoLogin =login_sp.getBoolean("mAutologinCheck", false);
        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
        if(choseRemember){
            etname.setText(name);
            etpwd.setText(pwd);
            box.setChecked(true);
        }
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
    }

//    获取监听控件
    private void initId() {

        login = (Button) findViewById(R.id.btn_login_page_login);
        register = (Button) findViewById(R.id.btn_login_page_register);
        resetpwd = (Button) findViewById(R.id.btn_login_page_resetpwd);
        etname = (EditText) findViewById(R.id.et_login_page_Etname);
        etpwd = (EditText) findViewById(R.id.et_login_page_Etpwd);
        cancle = (Button) findViewById(R.id.btn_login_page_cancle);
        box = (CheckBox) findViewById(R.id.cb_login_page_box);
    }
//    设置监听
    private void setListener() {
//        fh.setOnClickListener(mylistener);
        login.setOnClickListener(mylistener);
        register.setOnClickListener(mylistener);
        resetpwd.setOnClickListener(mylistener);
        etname.setOnClickListener(mylistener);
        etpwd.setOnClickListener(mylistener);
        box.setOnClickListener(mylistener);
        cancle.setOnClickListener(mylistener);
    }
    OnClickListener mylistener = new OnClickListener() {                  //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.btn_login_page_fh:                             //登录界面的注销按钮
//                    Intent fh = new Intent(Login.this,TextListActivity.class) ;    //切换Login Activity至User Activity
//                    startActivity(fh);
//                    break;
                case R.id.btn_login_page_register:                            //登录界面的注册按钮
                    Intent intent_Login_to_Register = new Intent(Login.this,RegisterActivity.class) ;    //切换Login Activity至User Activity
                    startActivity(intent_Login_to_Register);
                    finish();
                    break;
                case R.id.btn_login_page_login:                              //登录界面的登录按钮
                    login();
                    break;

                case R.id.btn_login_page_resetpwd:                             //登录界面的注销按钮
                    Intent intent_Login_to_reset = new Intent(Login.this,ResetpwdActivity.class) ;    //切换Login Activity至User Activity
                    startActivity(intent_Login_to_reset);
                    finish();
                    break;
                case R.id.btn_login_page_cancle:        //登录界面的注销按钮
                    cancel();
                    break;
            }
        }
    };

    public void login() {                                              //登录按钮监听事件
        if (isUserNameAndPwdValid()) {
            String userName = etname.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = etpwd.getText().toString().trim();
            SharedPreferences.Editor editor =login_sp.edit();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result==1){                                             //返回1说明用户名和密码均正确
                //保存用户名和密码
                editor.putString("USER_NAME", userName);
                editor.putString("PASSWORD", userPwd);

                //是否记住密码
                if(box.isChecked()){
                    editor.putBoolean("box", true);
                }else{
                    editor.putBoolean("box", false);
                }
                editor.commit();

                Intent intent = new Intent(Login.this,TextListActivity.class) ;    //切换Login Activity至User Activity
                startActivity(intent);
                finish();
                Toast.makeText(this, "登陆成功",Toast.LENGTH_SHORT).show();//登录成功提示
            }else if(result==0){
                Toast.makeText(this, "登录失败",Toast.LENGTH_SHORT).show();  //登录失败提示
            }
        }
    }


    public void cancel() {           //注销
        if (isUserNameAndPwdValid()) {
            String userName = etname.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = etpwd.getText().toString().trim();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result==1){                                             //返回1说明用户名和密码均正确
                Toast.makeText(this, "注销成功提示",Toast.LENGTH_SHORT).show();//注销成功提示
                etname.setText("");
                etpwd.setText("");
                mUserDataManager.deleteUserDatabyname(userName);
            }else if(result==0){
                Toast.makeText(this, "注销失败提示",Toast.LENGTH_SHORT).show();  //注销失败提示
            }
        }

    }
    public boolean isUserNameAndPwdValid() {
        if (etname.getText().toString().trim().equals("")) {
            Toast.makeText(this, "账户为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (etpwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    protected void onResume() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }
}