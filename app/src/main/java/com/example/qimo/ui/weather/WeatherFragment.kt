package com.example.qimo.ui.weather

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.qimo.R
import com.example.qimo.ui.watch.WatchViewModel
import kotlinx.android.synthetic.main.weather_fragment.*


class WeatherFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherFragment()
    }

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(WeatherViewModel::class.java)

        viewModel.cities.observe(requireActivity(), Observer {
            val cities = it
            val adapter = ArrayAdapter<City>(requireActivity(), android.R.layout.simple_list_item_1, cities)
            listView_weather1.adapter = adapter
            listView_weather1.setOnItemClickListener { _, _, position, _ ->
                val cityCode = cities[position].city_code
                val intent = Intent(requireActivity(), Weather_2::class.java)
                intent.putExtra("city_code", cityCode)
                startActivity(intent)
            }
        })
    }
}