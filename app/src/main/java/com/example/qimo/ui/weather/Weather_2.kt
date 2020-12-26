package com.example.qimo.ui.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.qimo.R
import com.example.qimo.ui.weather.weather.Forecast
import com.example.qimo.ui.weather.weather.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_weather_2.*

class Weather_2 : AppCompatActivity() {
    val baseURL = "http://t.weather.itboy.net/api/weather/city/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_2)
        val cityCode = intent.getStringExtra("city_code")
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(baseURL+cityCode,{
            val gson = Gson()
            val WeatherType = object :TypeToken<Weather>() {}.type
            val weather = gson.fromJson<Weather>(it,WeatherType)
            textView_city.text = weather.cityInfo.city
            textView_province.text = weather.cityInfo.parent
            textView_wendu.text = weather.data.wendu
            textView_shidu.text = weather.data.shidu
            val firstDay = weather.data.forecast.first()
            when(firstDay.type){
                "晴" -> imageView.setImageResource(R.drawable.sun)
                "阴" -> imageView.setImageResource(R.drawable.cloud)
                "多云" -> imageView.setImageResource(R.drawable.mcloud)
                "阵雨" -> imageView.setImageResource(R.drawable.rain)
                else -> imageView.setImageResource(R.drawable.thunder)
            }
            val adapter = ArrayAdapter<Forecast>(this,android.R.layout.simple_list_item_1,weather.data.forecast)
            listView_weather2.adapter = adapter

            Log.d("MainActivity","${weather.cityInfo.city} ${weather.cityInfo.parent}")
        },{
            Log.d("MainActivity2","$it")
        })
        queue.add(stringRequest)
    }
}