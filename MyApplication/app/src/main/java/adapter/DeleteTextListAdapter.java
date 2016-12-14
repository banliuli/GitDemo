package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import activity.DeleteTextListActivity;
import activity.ItemText;

public class DeleteTextListAdapter extends BaseAdapter {
    private Context context;
    private List<ItemText> data = new ArrayList<>();
    private ListView lv;

    public DeleteTextListAdapter(Context context, List<ItemText> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }
    public class ViewHodler{
        TextView TvTitle;
        TextView TvConcent;
        public CheckBox ch_delete;
    }
    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        ViewHodler viewHodler=null;
        if (null == view){
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_delete_textlist,null);
            viewHodler=new ViewHodler();
            viewHodler.TvTitle= (TextView) view.findViewById(R.id.Tv_item_delete_textlist_title);
            viewHodler.TvConcent=(TextView) view.findViewById(R.id.Tv_item_delete_textlist_content);
            viewHodler.ch_delete=(CheckBox)view.findViewById(R.id.ch_item_delete_textlist);
            view.setTag(viewHodler);
        }
        else {
            viewHodler= (ViewHodler) view.getTag();
        }
        ItemText currItem=data.get(i);
        viewHodler.TvTitle.setText(currItem.getTitle());
        viewHodler.TvConcent.setText(currItem.getContent());
        viewHodler.ch_delete.setChecked(data.get(i).getChecked());
        viewHodler.ch_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox)v;
                data.get(i).checked = cb.isChecked();
            }
        });
        return view;
    }
}
