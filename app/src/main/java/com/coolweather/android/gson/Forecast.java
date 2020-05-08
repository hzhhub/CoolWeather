package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.lang.ref.SoftReference;

public class Forecast {
    public String date;//预报日期

    @SerializedName("tmp")
    public Temperature temperature;//预报气温

    @SerializedName("cond")
    public More more;//更多信息

    public class Temperature{
        public String max;
        public String min;
    }
    public class More{
        @SerializedName("txt_d")
        public String info;//预测天气信息
    }
}
