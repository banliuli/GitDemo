package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.List;

import adapter.DeleteTextListAdapter;
import adapter.TextListAdapter;


public class DeleteTextListActivity extends AppCompatActivity{
    private Button back;
    private List<ItemText> lit = new ArrayList<ItemText>();
    private DeleteTextListAdapter myadapter;
    private ListView lv;
    private Button delete;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_textlist);
        initID();
        setListener();
        getData();
        myadapter = new DeleteTextListAdapter(this,lit);
        lv = (ListView)findViewById(R.id.Lv_activtiy_delete_textlist);
        lv.setAdapter(myadapter);
        //给ListView设置item点击监听器，实现点击效果
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

            }
        });
    }

    private void getData() {
        lit.add(new ItemText(0L,"weight用法","均分?"));
        lit.add(new ItemText(1L,"weight用法","均分?"));
        lit.add(new ItemText(2L,"weight用法","均分?"));
        lit.add(new ItemText(3L,"weight用法","均分?"));
        lit.add(new ItemText(4L,"weight用法","均分?"));
        lit.add(new ItemText(5L,"weight用法","均分?"));
        lit.add(new ItemText(6L,"weight用法","均分?"));
        lit.add(new ItemText(7L,"weight用法","均分?"));
    }

    private void initID() {
        back = (Button)findViewById(R.id.btn_activtiy_delete_textlist_back);
        delete = (Button)findViewById(R.id.btn_item_delete_textlist_delete);
    }
    private void setListener() {
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);

    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){
                case R.id.btn_activtiy_delete_textlist_back:
                    i.setClass(DeleteTextListActivity.this,MainActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
