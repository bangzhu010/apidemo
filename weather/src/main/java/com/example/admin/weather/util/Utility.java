package com.example.admin.weather.util;

import android.text.TextUtils;

import com.example.admin.weather.db.CoolWeatherDB;
import com.example.admin.weather.model.position.City;
import com.example.admin.weather.model.position.County;
import com.example.admin.weather.model.position.Province;

/**
 * Created by admin on 2016/4/27.
 */
public class Utility {
    /**
     * 解析从服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(
            CoolWeatherDB coolWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String province :
                        allProvinces) {
                    String[] array = province.split("\\|");
                    Province p = new Province();
                    p.setProvinceCode(array[0]);
                    p.setProvinceName(array[1]);

                    coolWeatherDB.saveProvince(p);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 保存城市到数据库
     */

    public synchronized boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c :
                        allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityName(array[1]);
                    city.setCityCode(array[0]);
                    city.setProvinceId(provinceId);

                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }

        return false;
    }

    public synchronized boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,
                                                       String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String C :
                        allCounties) {
                    String[] array = C.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    coolWeatherDB.saveCounty(county);
                    return true;
                }
            }
        }
        return false;
    }
}
