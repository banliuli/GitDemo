package activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.List;

import adapter.TextListAdapter;

/**
 * Created by lenovo on 2016/11/28.
 */
public class TextListActivity extends AppCompatActivity{
    private Button back;
    private Button add;
    private List<ItemText> lit = new ArrayList<ItemText>();
    private TextListAdapter myadapter;
    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textlist);
        initID();
        setListener();
        getData();
        myadapter = new TextListAdapter(this,lit);
        lv = (ListView)findViewById(R.id.Lv_activtiy_textlist);
        lv.setAdapter(myadapter);
        //给ListView设置item点击监听器，实现点击效果
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String j = String.valueOf(i);
                Intent ii=new Intent(TextListActivity.this,EditHomeActivity.class);
                startActivity(ii);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {

                // TODO 自动生成的方法存根
                Button btn= (Button) arg1.findViewById(R.id.btn_item_textlist_delete);
                if(btn.getVisibility() == View.INVISIBLE){
                    btn.setVisibility(View.VISIBLE);
                }
                else{
                    btn.setVisibility(View.INVISIBLE);
                }
                return true;//关于返回值，若返回False，则是当长按时，既调用onItemLongClick，同时调用onItemLongClick后
                //还会调用onItemClick，就是说会同时调用onItemLongClick，和onItemClick，
                //若返回true，则只调用onItemLongClick
            }
        });
    }

    private void getData() {
        lit.add(new ItemText(0L,"来自手机","来自手机"));
    }

    private void initID() {
        back = (Button)findViewById(R.id.btn_activtiy_textlist_return);
        add=(Button)findViewById(R.id.btn_activtiy_textlist_add);
    }
    private void setListener() {
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        add.setOnClickListener(listener);
    }
    private void dialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(TextListActivity.this);
        builder.setTitle("请输入文件名");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        final EditText editText = new EditText(this);
        builder.setView(editText);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input = editText.getText().toString();
                lit.add(new ItemText(1L,input," "));
                myadapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create();
        builder.show();
    }
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_activtiy_textlist_return:
                    Intent i = new Intent();
                    i.setClass(TextListActivity.this,MainActivity.class);
                    startActivity(i);
                    break;
                case R.id.btn_activtiy_textlist_add:
                    dialog();
                    break;

            }

        }
    }
}
