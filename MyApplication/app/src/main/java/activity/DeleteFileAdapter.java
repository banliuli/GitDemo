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
 * Created by lenovo on 2016/11/29.
 */
public class DeleteFileAdapter extends BaseAdapter {
    private Context context;
    private List<File> ldf = new ArrayList<>();

    public DeleteFileAdapter(Context context, List<File> ldf) {
        this.context = context;
        this.ldf = ldf;
    }

    @Override
    public int getCount() {
        return ldf.size();
    }

    @Override
    public Object getItem(int i) {
        return ldf.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ldf.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (null==view){
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_delete_file,null);
        }
        TextView TvTitle = (TextView) view.findViewById(R.id.Tv_item_delete_file_Title);
        TvTitle.setText(ldf.get(i).getFilename());
        return view;
    }
}
