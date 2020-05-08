package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    @SerializedName("city")
    public String cityName;//城市名

    @SerializedName("id")
    public String weatherId;//天气编号

    public Update update;//更新天气状况

    public class Update{

        @SerializedName("loc")
        public String updateTime;//更新时间
    }
}
