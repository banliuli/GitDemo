package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.suishouji.R;

import java.util.ArrayList;

import activity.Mfile;

/**
 * Created by Administrator on 2016/12/12.
 */

public class MoveAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Mfile> files = new ArrayList<Mfile>();

    public MoveAdapter(Context context, ArrayList<Mfile> files) {
        this.context = context;
        this.files = files;
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int i) {
        return files.get(i);
    }

    @Override
    public long getItemId(int i) {
        return files.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        //加载视图页面
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movelist,null);
        }
        //绘制视图中的某一项
        ImageView IvCon = (ImageView) view.findViewById(R.id.Iv_item_movefile_file);
        IvCon.setImageResource(files.get(i).getUrl());

        TextView TvName = (TextView) view.findViewById(R.id.Tv_item_movefile_name);
        TvName.setText(files.get(i).getName());

        TextView TvTime = (TextView) view.findViewById(R.id.Tv_item_movefile_time);
        TvTime.setText(files.get(i).getTime());
        return view;
    }
}
