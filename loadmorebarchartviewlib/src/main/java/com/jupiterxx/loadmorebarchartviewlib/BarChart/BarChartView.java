package com.jupiterxx.loadmorebarchartviewlib.BarChart;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import java.util.List;


/**
 * Created by jupiter on 2017/11/16.
 */

public class BarChartView<T> extends RecyclerView {


    private int WIDGET_WIDTH;
    private List<T> mList;
    private IGetData mGetData;
    private OnLoadMoreListener mOnLoadMoreListener;

    public BarChartView(Context context) {
        this(context, null);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);

    }

    public void refreshMaxValueAndNotifyData() {
        ((BarChartAdapter) getAdapter()).refreshMaxValueAndNotifyData();
    }

    public void init(List<T> list, IGetData<T> iGetData, OnLoadMoreListener onLoadMoreListener) {
        mList = list;
        mGetData = iGetData;
        mOnLoadMoreListener = onLoadMoreListener;
        post(new Runnable() {
            @Override
            public void run() {
                WIDGET_WIDTH = getMeasuredWidth();
                setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
                setAdapter(new BarChartAdapter(WIDGET_WIDTH, BarChartView.this, mList, mGetData));

                setOnLoadMoreListener();
            }
        });

    }

    @Override
    public void onScrollStateChanged(int state) {

        if (state == SCROLL_STATE_IDLE) {

            if (getChildAt(0).getRight() > WIDGET_WIDTH + getChildAt(0).getWidth() / 2 && getChildAt(0).getRight() < WIDGET_WIDTH + getChildAt(0).getWidth() + 4) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        smoothScrollBy(getChildAt(0).getRight() - WIDGET_WIDTH - getChildAt(0).getWidth(), 0);
                    }
                });
            } else if (getChildAt(0).getRight() < WIDGET_WIDTH + getChildAt(0).getWidth() / 2 && getChildAt(0).getRight() > WIDGET_WIDTH) {
                {

                    post(new Runnable() {
                        @Override
                        public void run() {
                            smoothScrollBy(getChildAt(0).getRight() - WIDGET_WIDTH, 0);
                        }
                    });

                }
            }
        }
    }

    public void setOnLoadMoreListener() {
        if (mOnLoadMoreListener == null)
            return;
        ((BarChartAdapter) getAdapter()).setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mOnLoadMoreListener.onLoadMore();
            }
        });
        ((BarChartAdapter) getAdapter()).setAutoLoadMoreSize(10);

//        ((BarChartAdapter)getAdapter()).setLoadMoreView(null);
        ((BarChartAdapter) getAdapter()).setLoadMoreView(new LoadMoreView() {
            @Override
            public int getLayoutId() {
                return 0;
            }

            @Override
            protected int getLoadingViewId() {
                return 0;
            }

            @Override
            protected int getLoadFailViewId() {
                return 0;
            }

            @Override
            protected int getLoadEndViewId() {
                return 0;
            }
        });

    }

    public void loadMoreComplete() {
        ((BarChartAdapter) getAdapter()).loadMoreComplete();
    }

    public interface IGetData<T> {

        String getMessage(T data);

        float getValue(T data);
    }


    public interface OnLoadMoreListener {

        void onLoadMore();


    }

}


