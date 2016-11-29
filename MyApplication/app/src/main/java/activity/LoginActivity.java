package activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.suishouji.R;

public class LoginActivity extends AppCompatActivity {

    private Button fh;
    private Button login;
    private Button register;
    private EditText etpwd;
    private EditText etname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        initId();
        setListener();
        Log.e("log","onCreate");
    }

    private void  initId(){
        fh =(Button)findViewById(R.id.btn_login_page_fh);
        login =(Button)findViewById(R.id.btn_login_page_login);
        register =(Button)findViewById(R.id.btn_login_page_register);
        etname = (EditText) findViewById(R.id.et_login_page_Etname);
        etpwd = (EditText) findViewById(R.id.et_login_page_Etpwd);
    }

   private void setListener(){
       MyListener listener = new MyListener();
       fh.setOnClickListener(listener);
       login.setOnClickListener(listener);
       register.setOnClickListener(listener);
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            Toast.makeText(LoginActivity.this,"用户名密码正确",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(LoginActivity.this,"用户名密码错误",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("log","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("log","onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("log","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("log","onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("log","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("log","onRestart");
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent i= new Intent();
            switch (v.getId()){
                case R.id.btn_login_page_fh:
                    i.setClass(LoginActivity.this, MainActivity.class);
                    break;
                case R.id.btn_login_page_register:
                    i.setClass(LoginActivity.this, RegisterActivity.class);
                    break;

            }
            startActivity(i);
        }
    }
}
