package com.jupiterxx.loadmorebarchartview;

/**
 * Created by jupiter on 2017/11/16.
 */

public class BarChartBean {

    private String text ;
    private float value;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public BarChartBean(String text, float value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
