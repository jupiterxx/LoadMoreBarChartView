package com.jupiterxx.loadmorebarchartviewlib.BarChart;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jupiterxx.loadmorebarchartviewlib.R;


import java.util.List;




/**
 * Created by jupiter on 2017/11/16.
 */

public class BarChartAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private final List<T> list;
    private BarChartView.IGetData<T> iGetData;
    private RecyclerView recyclerView;
    private int itemWidth;
    public float maxValue ;

    public BarChartAdapter(int recyclerViewWidth, RecyclerView recyclerView, List<T> list , BarChartView.IGetData<T> iGetData) {

        super(R.layout.bar_chart_item, list);
        this.recyclerView = recyclerView;
//        this.recyclerView.scrollToPosition(getItemCount() - 7);
        this.list = list;
        this.iGetData= iGetData;

        itemWidth = recyclerViewWidth / 7;

        getMaxValue();


    }

    private void getMaxValue() {
        for (T t :
                list) {
            if (maxValue < iGetData.getValue(t)){
                maxValue = iGetData.getValue(t);
            }
        }
    }

    public void refreshMaxValueAndNotifyData() {
        getMaxValue();
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, T item) {

        ViewGroup.LayoutParams layoutParams = viewHolder.setText(R.id.tv_header,iGetData.getMessage( item))
                .getConvertView().getLayoutParams();
        layoutParams
                .width = itemWidth;
        viewHolder.getConvertView().setLayoutParams(layoutParams);
        ((BarChartItemView) viewHolder.getView(R.id.bar_chart_item_view)).setProgress(iGetData.getValue(item) / maxValue * 100);


    }




    public void refreshVisibleMaxValue() {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //判断是当前layoutManager是否为LinearLayoutManager
        // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
        float maxValue = 0;
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
            //获取最后一个可见view的位置
            int lastItemPosition = linearManager.findLastVisibleItemPosition();
            //获取第一个可见view的位置
            int firstItemPosition = linearManager.findFirstVisibleItemPosition();

            for (int i = 1; i < lastItemPosition - firstItemPosition + 1; i++) {
                if (iGetData.getValue(list.get(lastItemPosition - i)) > maxValue)
                    maxValue = iGetData.getValue(list.get(lastItemPosition - i));
            }
        }

//        newVisibleMaxValue = maxValue;
        notifyDataSetChanged();
    }
}
