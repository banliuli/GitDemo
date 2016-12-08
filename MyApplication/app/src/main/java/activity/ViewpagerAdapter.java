package activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class ViewpagerAdapter extends PagerAdapter{

    List<View> viewLists;

    //构造方法
    public ViewpagerAdapter(List<View> lists) {
        viewLists = lists;
    }

    //获得size
    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    //销毁Item
    public void destroyItem(View view,int i,Object object) {
        ((ViewPager) view).removeView(viewLists.get(i));
    }

    //实例化Item
    public Object instantiateItem(View view,int i) {
        ((ViewPager) view).addView(viewLists.get(i));
        return viewLists.get(i);
    }
}
