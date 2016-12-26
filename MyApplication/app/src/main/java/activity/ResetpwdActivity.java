package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.suishouji.R;
import data.UserData;
import data.UserDataManager;

/**
 * Created by lenovo on 2016/11/18.
 */
public class ResetpwdActivity extends Activity {
    private EditText uname;                       //用户名编辑
    private EditText pwd1;                        //密码编辑
    private EditText pwd2;                        //密码编辑
    private EditText spwd;                       //密码编辑
    private Button Sure ;                       //确定按钮
    private Button Cancel ;                     //取消按钮
    private UserDataManager mUserDataManager;         //用户数据管理类



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.resetpwd);
            uname = (EditText) findViewById(R.id.et_resetpwd_uname);
            pwd1 = (EditText) findViewById(R.id.et_resetpwd_pwd1);
            pwd2 = (EditText) findViewById(R.id.et_resetpwd_pwd2);
            spwd = (EditText) findViewById(R.id.et_resetpwd_spwd);
            Sure = (Button) findViewById(R.id.btn_resetpwd_sure);
            Cancel= (Button) findViewById(R.id.btn_resetpwd_cancle);

            Sure .setOnClickListener(m_resetpwd_Listener);      //注册界面两个按钮的监听事件
            Cancel.setOnClickListener(m_resetpwd_Listener);
            if (mUserDataManager == null) {
                mUserDataManager = new UserDataManager(this);
                mUserDataManager.openDataBase();                              //建立本地数据库
            }

        //设置密码为隐藏
        pwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        pwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        spwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        View.OnClickListener m_resetpwd_Listener = new View.OnClickListener() {    //不同按钮按下的监听事件选择
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_resetpwd_sure:                       //确认按钮的监听事件
                        resetpwd_check();
                        break;
                    case R.id.btn_resetpwd_cancle:                     //取消按钮的监听事件,由注册界面返回登录界面
                        Intent intent_Resetpwd_to_Login = new Intent(ResetpwdActivity.this,Login.class) ;    //切换Resetpwd Activity至Login Activity
                        startActivity(intent_Resetpwd_to_Login);
                        finish();
                        break;
                }
            }
        };
        public void resetpwd_check() {                                //确认按钮的监听事件
            if (isUserNameAndPwdValid()) {
                String userName = uname.getText().toString().trim();
                String userPwd_old = pwd1.getText().toString().trim();
                String userPwd_new = pwd2.getText().toString().trim();
                String userPwdCheck = spwd.getText().toString().trim();
                int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd_old);
                if(result==1){                                             //返回1说明用户名和密码均正确,继续后续操作
                    if(userPwd_new.equals(userPwdCheck)==false){           //两次密码输入不一样
                        Toast.makeText(this, "两次密码输入不一样",Toast.LENGTH_SHORT).show();
                        return ;
                    } else {
                        UserData mUser = new UserData(userName, userPwd_new);
                        mUserDataManager.openDataBase();
                        boolean flag = mUserDataManager.updateUserData(mUser);
                        if (flag == false) {
                            Toast.makeText(this, "修改密码失败",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this, "修改密码成功",Toast.LENGTH_SHORT).show();
                            mUser.pwdresetFlag=1;
                            Intent intent_Register_to_Login = new Intent(ResetpwdActivity.this,Login.class) ;    //切换User Activity至Login Activity
                            startActivity(intent_Register_to_Login);
                            finish();
                        }
                    }
                }else if(result==0){                                       //返回0说明用户名和密码不匹配，重新输入
                    Toast.makeText(this, "用户名和密码不匹配，重新输入",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
        public boolean isUserNameAndPwdValid() {
            String userName = uname.getText().toString().trim();
            //检查用户是否存在
            int count=mUserDataManager.findUserByName(userName);
            //用户不存在时返回，给出提示文字
            if(count<=0){
                Toast.makeText(this,  "用户不存在",Toast.LENGTH_SHORT).show();
                return false;
            }
            if (uname.getText().toString().trim().equals("")) {
                Toast.makeText(this,  "用户名为空",Toast.LENGTH_SHORT).show();
                return false;
            } else if (pwd1.getText().toString().trim().equals("")) {
                Toast.makeText(this, "密码为空",Toast.LENGTH_SHORT).show();
                return false;
            } else if (pwd2.getText().toString().trim().equals("")) {
                Toast.makeText(this, "新密码为空",Toast.LENGTH_SHORT).show();
                return false;
            }else if(spwd.getText().toString().trim().equals("")) {
                Toast.makeText(this, "确认密码为空",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }

