package activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.List;

import adapter.TextListAdapter;

/**
 * Created by lenovo on 2016/11/28.
 */
public class FileAdapter extends BaseAdapter {
    private ListView lv_data;
    private Context context;
    private List<File> lfile = new ArrayList<>();


    public FileAdapter(Context context, List<File> lfile) {
        this.context = context;
        this.lfile = lfile;
    }

    @Override
    public int getCount() {
        return lfile.size();
    }

    @Override
    public Object getItem(int i) {
        return lfile.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lfile.get(i).getId();
    }
    public class ViewHolder{
        public TextView TvFilename;
        public Button delete;
    }
    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (null == view){
            viewHolder=new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_suishouji_file,null);
            viewHolder.TvFilename = (TextView) view.findViewById(R.id.Tv_layout_suishouji_file_Title);
            viewHolder.delete = (Button) view.findViewById(R.id.btn_item_file_delete);
            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)view.getTag();
        }
        File currItem=lfile.get(i);
        viewHolder.TvFilename.setText(currItem.getFilename());
        viewHolder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
                lfile.remove(i);
                notifyDataSetChanged();
            }}
        );
        return view;
    }
}
