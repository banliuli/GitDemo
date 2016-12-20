package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.List;

import activity.ItemText;

public class TextListAdapter extends BaseAdapter {
    private Context context;
    private List<ItemText> lit = new ArrayList<>();

    public TextListAdapter(Context context, List<ItemText> lit) {
        this.context = context;
        this.lit = lit;
    }

    @Override
    public int getCount() {
        return lit.size();
    }
    @Override
    public Object getItem(int i) {
        return lit.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lit.get(i).getId();
    }
    public class ViewHolder{
        public TextView TvTitle;
        public TextView TvContent;
        public Button delete;

    }
    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (null == view){
            viewHolder=new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_textlist,null);
            viewHolder.TvTitle = (TextView) view.findViewById(R.id.Tv_item_textlist_title);
            viewHolder.delete = (Button) view.findViewById(R.id.btn_item_textlist_delete);
            viewHolder.TvContent = (TextView) view.findViewById(R.id.Tv_item_textlist_content);
            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)view.getTag();
        }
        ItemText currItem=lit.get(i);
        viewHolder.TvTitle.setText(currItem.getTitle());
        viewHolder.TvContent.setText(currItem.getContent());
        viewHolder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
                lit.remove(i);
                notifyDataSetChanged();
            }}
        );
        return view;
    }
}
