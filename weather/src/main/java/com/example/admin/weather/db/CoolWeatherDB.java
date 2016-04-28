package com.example.admin.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.admin.weather.model.position.City;
import com.example.admin.weather.model.position.County;
import com.example.admin.weather.model.position.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/4/27.
 */
public class CoolWeatherDB {
    /**
     * 数据库名称
     */
    public static final String DB_NAME = "cool_weather";
    /**
     * 数据库版本号
     */
    public static final int VERSION = 1;

    private static CoolWeatherDB mCoolWeatherDB;

    private SQLiteDatabase db;

    private static Context mContext;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (mCoolWeatherDB == null) {
            mCoolWeatherDB = new CoolWeatherDB(context);
        }
        mContext = context;
        return mCoolWeatherDB;
    }

    /**
     * @param province 省份
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }

    }

    /**
     * @return 数据库里所有的省份
     */
    public List<Province> getProvinces() {
        List<Province> provinceList = new ArrayList<>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                provinceList.add(province);

            } while (cursor.moveToNext());
        }
        return provinceList;
    }

    /**
     * @param city 保存城市到数据库
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            Log.i("lwc","saveCity values = " + values);
            db.insert("City", null, values);
        }
    }

    /**
     *
     * @param provinceId 省的ID
     * @return 省的所有城市
     */
    public List<City> getCities(int provinceId) {
        List<City> cityList = new ArrayList<>();
        Cursor cursor = db.query("City",null,"province_id = ?",new String[]{String.valueOf(provinceId)},null,null,null);
        if(cursor.moveToFirst()){
            do{
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                cityList.add(city);
                Log.i("lwc",city.toString());
            } while (cursor.moveToNext());
        }
        Log.i("lwc","getCities cityList = " + cityList);
        return cityList;
    }
    /**
     * @param county 保存县城到数据库
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            Log.i("lwc","values =  " + values.toString());
            db.insert("County", null, values);
        }
    }

    /**
     *
     * @param cityId 市的ID
     * @return 市的所有县城
     */
    public List<County> getCounties(int cityId) {
        List<County> countyList = new ArrayList<>();
        Cursor cursor = db.query("County",null,"city_id = ?",new String[]{String.valueOf(cityId)},null,null,null);
        if(cursor.moveToFirst()){
            do{
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                countyList.add(county);
            } while (cursor.moveToNext());
        }
        Log.i("lwc","getCounties countyList = " + countyList.toString());
        return countyList;
    }

}
