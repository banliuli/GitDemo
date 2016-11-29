package activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/11/28.
 */
public class FileAdapter extends BaseAdapter {
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

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (null==view){
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_suishouji_file,null);
        }
        TextView TvTitle = (TextView) view.findViewById(R.id.Tv_layout_suishouji_file_Title);
        TvTitle.setText(lfile.get(i).getFilename());
        return view;
    }
}
