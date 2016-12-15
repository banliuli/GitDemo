package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.List;

import adapter.DeleteTextListAdapter;
import adapter.TextListAdapter;


public class DeleteTextListActivity extends Activity {
    private Button back;
    private List<ItemText> data = new ArrayList<ItemText>();
    private DeleteTextListAdapter myadapter;
    private ListView lv;
    private Button delete;
    private CheckBox check_all;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_textlist);
        myadapter = new DeleteTextListAdapter(this,data);
        lv = (ListView)findViewById(R.id.Lv_activtiy_delete_textlist);
        lv.setAdapter(myadapter);
        initID();
        initData();
        initListener();
        setListener();
    }

    private void initData() {
        data.add(new ItemText(0L,"weight用法","均分?",false));
        data.add(new ItemText(1L,"weight用法","均分?",false));
        data.add(new ItemText(2L,"weight用法","均分?",false));
        data.add(new ItemText(3L,"weight用法","均分?",false));
        data.add(new ItemText(4L,"weight用法","均分?",false));
        data.add(new ItemText(5L,"weight用法","均分?",false));
        data.add(new ItemText(6L,"weight用法","均分?",false));
        data.add(new ItemText(7L,"weight用法","均分?",false));
    }
    private void initID() {
        back = (Button)findViewById(R.id.btn_activtiy_delete_textlist_back);
        delete = (Button)findViewById(R.id.btn_delete_textlist_delete);
        check_all=(CheckBox)findViewById(R.id.check_all);
    }
    private void setListener() {
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        delete.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_activtiy_delete_textlist_back:
                    Intent i = new Intent();
                    i.setClass(DeleteTextListActivity.this,TextListActivity.class);
                    startActivity(i);
                    break;
                case R.id.btn_delete_textlist_delete:
                    //创建一个要删除内容的集合，不能直接在数据源data集合中直接进行操作，否则会报异常
                    //创建一个要删除内容的集合，不能直接在数据源data集合中直接进行操作，否则会报异常
                    List<ItemText> deleSelect = new ArrayList<ItemText>();
                    //把选中的条目要删除的条目放在deleSelect这个集合中
                    for (int j = 0; j < data.size(); j++) {
                        if (data.get(j).getChecked()) {
                            deleSelect.add(data.get(j));
                        }
                    }
                    //判断用户是否选中要删除的数据及是否有数据
                    if (deleSelect.size() != 0 && data.size() != 0) {
                        //从数据源data中删除数据
                        data.removeAll(deleSelect);
                        //把deleSelect集合中的数据清空
                        deleSelect.clear();
                        //把全选复选框设置为false
                        check_all.setChecked(false);
                        //通知适配器更新UI
                        myadapter.notifyDataSetChanged();
                    } else if (data.size() == 0) {
                        Toast.makeText(DeleteTextListActivity.this, "没有要删除的数据", Toast.LENGTH_SHORT).show();
                    } else if (deleSelect.size() == 0) {
                        Toast.makeText(DeleteTextListActivity.this, "请选中要删除的数据", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
    private void initListener(){
        /**
         * 全选复选框设置事件监听
         */
        check_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (data.size()!=0) {//判断列表中是否有数据
                    if (isChecked) {
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setChecked(true);
                        }
                        //通知适配器更新UI
                        myadapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setChecked(false);
                        }
                        //通知适配器更新UI
                        myadapter.notifyDataSetChanged();
                    }
                }else{//若列表中没有数据则隐藏全选复选框
                    check_all.setVisibility(View.GONE);
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeleteTextListAdapter.ViewHodler viewHodler= (DeleteTextListAdapter.ViewHodler) view.getTag();
                //切换条目上复选框的选中状态
                viewHodler.ch_delete.toggle();
                data.get(position).setChecked(viewHodler.ch_delete.isChecked());
                parent.getItemAtPosition(position);
            }
        });
    }
}
