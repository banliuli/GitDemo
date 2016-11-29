package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/11/28.
 */
public class DeleteFileActivity extends AppCompatActivity {
    private Button suishouji;
    private Button mine;
    private Button edit;
    private List<File> lf = new ArrayList<File>();
    private DeleteFileAdapter myadapter;
    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_filelist);
        initID();
        setListener();
        getData();
        myadapter = new DeleteFileAdapter(this,lf);
        lv = (ListView)findViewById(R.id.Lv_delete_item_file);
        lv.setAdapter(myadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String j = String.valueOf(i);
                if (null!= j){
                    Intent k = new Intent();
                    k.setClass(DeleteFileActivity.this,TextListActivity.class);
                    startActivity(k);
                }
            }
        });
    }

    private void getData() {
        lf.add(new File(0L,"来自手机"));
        lf.add(new File(1L,"来自手机"));
        lf.add(new File(2L,"来自手机"));
        lf.add(new File(3L,"来自手机"));
        lf.add(new File(4L,"来自手机"));
        lf.add(new File(5L,"来自手机"));
        lf.add(new File(6L,"来自手机"));
        lf.add(new File(7L,"来自手机"));
    }


    private void initID() {
        suishouji = (Button)findViewById(R.id.btn_delete_filelist_suishouji1);
        mine = (Button)findViewById(R.id.btn_delete_filelist_mine);
        edit = (Button)findViewById(R.id.btn_delete_filelist_edit1);
    }
    private void setListener() {
        MyListener listener = new MyListener();
        suishouji.setOnClickListener(listener);
        mine.setOnClickListener(listener);
        edit.setOnClickListener(listener);

    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){
                case R.id.btn_delete_filelist_suishouji1:
                    i.setClass(DeleteFileActivity.this,MainActivity.class);
                    break;
                case R.id.btn_delete_filelist_edit1:
                    i.setClass(DeleteFileActivity.this,EditAddActivity.class);
                    break;
                case R.id.btn_delete_filelist_mine:
                    i.setClass(DeleteFileActivity.this,MineActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
