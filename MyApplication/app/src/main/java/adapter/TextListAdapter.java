package adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.suishouji.R;

import java.util.List;

public class TextListAdapter extends BaseAdapter {
    private Context context;
    private List<String> listItems;
    private List<String> listItemTimes;
    private LayoutInflater inflater;
    public TextListAdapter(Context context, List<String> listItems, List<String> listItemTimes) {
        this.context = context;
        this.listItems = listItems;
        this.listItemTimes = listItemTimes;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /*
    * 往列表添加条目
    * @param item
    * */
    public void addListItem(String item, String time) {
        listItems.add(item);
        listItemTimes.add(time);

    }

    /**
     * 删除指定位置的数据
     *
     * @param position
     */
    public void removeListItem(int position) {
        listItems.remove(position);
        listItemTimes.remove(position);
    }

    /**
     * 获取列表的数量
     */
    @Override
    public int getCount() {
        return listItems.size();
    }

    /**
     * 根据索引获取列表对应索引的内容
     */

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_item_textlist, null);
        }

        TextView text = (TextView) convertView.findViewById(R.id.Tv_item_textlist_title);
        text.setText(listItems.get(position));

        TextView time = (TextView) convertView.findViewById(R.id.Tv_item_textlist_date);
        String datetime = DateFormat.format("yyyy-MM-dd kk:mm:ss",
                Long.parseLong(listItemTimes.get(position))).toString();
        time.setText(datetime);

        return convertView;
    }
}
