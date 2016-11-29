package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (null == view){
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_textlist,null);
        }
        TextView TvTitle = (TextView) view.findViewById(R.id.Tv_item_textlist_title);
        TvTitle.setText(lit.get(i).getTitle());
        TextView TvContent = (TextView) view.findViewById(R.id.Tv_item_textlist_content);
        TvContent.setText(lit.get(i).getContent());
        return view;
    }
}
