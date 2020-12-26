package com.example.qimo.ui.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.qimo.R
import kotlinx.android.synthetic.main.fragment_hello.*

class HelloFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hello,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.button_1)
        button.setOnClickListener {
            textView_hello.text = resources.getString(R.string.clicked)
        }
    }

    companion object{
        fun newInstance() : HelloFragment = HelloFragment()
    }
}