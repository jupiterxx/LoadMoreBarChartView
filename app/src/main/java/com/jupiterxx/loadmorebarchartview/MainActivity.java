package com.jupiterxx.loadmorebarchartview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jupiterxx.loadmorebarchartviewlib.BarChart.BarChartAdapter;
import com.jupiterxx.loadmorebarchartviewlib.BarChart.BarChartView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BarChartView<BarChartBean> bcv;
    private List<BarChartBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bcv = (BarChartView<BarChartBean>) findViewById(R.id.bcv);

        list = new ArrayList<BarChartBean>() {
            {
                add(new BarChartBean("今天", 0));
                add(new BarChartBean("今天", 10));
                add(new BarChartBean("今天", 20));
                add(new BarChartBean("今天", 0));
                add(new BarChartBean("今天", 100));
                add(new BarChartBean("今天", 50));
                add(new BarChartBean("今天", 1));
                add(new BarChartBean("今天", 2));
                add(new BarChartBean("今天", 3));
                add(new BarChartBean("今天", 5));
                add(new BarChartBean("今天", 0));
                add(new BarChartBean("今天", 10));
                add(new BarChartBean("今天", 20));
                add(new BarChartBean("今天", 0));
                add(new BarChartBean("今天", 100));
                add(new BarChartBean("今天", 50));
                add(new BarChartBean("今天", 1));
                add(new BarChartBean("今天", 2));
                add(new BarChartBean("今天", 3));
                add(new BarChartBean("今天", 5));
                add(new BarChartBean("今天", 0));
                add(new BarChartBean("今天", 10));
                add(new BarChartBean("今天", 20));
                add(new BarChartBean("今天", 0));
                add(new BarChartBean("今天", 100));
                add(new BarChartBean("今天", 50));
                add(new BarChartBean("今天", 1));
                add(new BarChartBean("今天", 2));
                add(new BarChartBean("今天", 3));
                add(new BarChartBean("今天", 5));
                add(new BarChartBean("今天", 0));
                add(new BarChartBean("今天", 10));
                add(new BarChartBean("今天", 20));
                add(new BarChartBean("今天", 0));
                add(new BarChartBean("今天", 100));
                add(new BarChartBean("今天", 50));
                add(new BarChartBean("今天", 1));
                add(new BarChartBean("今天", 2));
                add(new BarChartBean("今天", 3));
                add(new BarChartBean("今天", 5));


            }
        };

        bcv.init(list, new BarChartView.IGetData<BarChartBean>() {
            @Override
            public String getMessage(BarChartBean data) {
                return data.getText();
            }

            @Override
            public float getValue(BarChartBean data) {
                return data.getValue();
            }
        },new BarChartView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                bcv.post(new Runnable() {
                    @Override
                    public void run() {
                        list.add(new BarChartBean("昨天", 200));
                        list.add(new BarChartBean("昨天", 300));
                        bcv.refreshMaxValueAndNotifyData();
                        bcv.loadMoreComplete();
                    }
                });
            }


        });
//        bcv.setOnLoadMoreListener();
    }
}
