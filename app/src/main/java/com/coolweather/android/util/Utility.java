package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /*
     * 解析和处理服务器返回的省级数据
     * */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {//数据非空
            try {
                JSONArray allProvinces = new JSONArray(response);//创建一个用于解析的JSON数组
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);//获取每一个JSON对象
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));//将JSON对象中的name字段赋值给ProvinceName
                    province.setProvinceCode(provinceObject.getInt("id"));//将JSON对象中的id字段赋值给ProvinceCode
                    province.save();//将数据保存到数据库中
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     * 解析和处理服务器返回的市级数据
     * */
    public static boolean handleCityResponse(String response,int provinceId) {
        if (!TextUtils.isEmpty(response)) {//数据非空
            try {//下面的allcity和书上的allCities不一样，后续检查代码注意
                JSONArray allCity = new JSONArray(response);//创建一个用于解析的JSON数组
                for (int i = 0; i < allCity.length(); i++) {
                    JSONObject cityObject = allCity.getJSONObject(i);//获取每一个JSON对象
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));//将JSON对象中的name字段赋值给ProvinceName
                    city.setCityCode(cityObject.getInt("id"));//将JSON对象中的id字段赋值给ProvinceCode
                    city.setProvinceId(provinceId);
                    city.save();//将数据保存到数据库中
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     * 解析和处理服务器返回的市级数据
     * */
    public static boolean handleCountyResponse(String response,int cityId) {
        if (!TextUtils.isEmpty(response)) {//数据非空
            try {
                JSONArray allCounties = new JSONArray(response);//创建一个用于解析的JSON数组
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);//获取每一个JSON对象
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));//将JSON对象中的name字段赋值给ProvinceName
                    county.setWeatherId(countyObject.getString("weather_id"));//将JSON对象中的id字段赋值给ProvinceCode
                    county.setCityId(cityId);
                    county.save();//将数据保存到数据库中
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    * 将返回的JSON数据解析成Weather实体类
    * */
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return  new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}