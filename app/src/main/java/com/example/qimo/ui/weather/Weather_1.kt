package com.example.qimo.ui.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.qimo.R
import kotlinx.android.synthetic.main.activity_weather_1.*

class Weather_1 : AppCompatActivity() {
    lateinit var  viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_1)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(WeatherViewModel::class.java)
        viewModel.cities.observe(this, Observer {
            val cities = it
            val adapter = ArrayAdapter<City>(this, android.R.layout.simple_list_item_1, cities)
            listView_weather1.adapter = adapter
            listView_weather1.setOnItemClickListener { _, _, position, _ ->
                val cityCode = cities[position].city_code
                val intent = Intent(this, Weather_2::class.java)
                intent.putExtra("city_code", cityCode)
                startActivity(intent)
            }
        })
    }
}
