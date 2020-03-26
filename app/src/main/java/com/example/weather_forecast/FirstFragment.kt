package com.example.weather_forecast

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.beust.klaxon.Klaxon
import com.example.weather_forecast.model.Forecast
import com.example.weather_forecast.model.Main
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.text_view_description
import kotlinx.android.synthetic.main.fragment_first.text_view_temperature
import kotlinx.android.synthetic.main.fragment_first.weather_icon
import kotlinx.android.synthetic.main.fragment_second.*
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var visible = false

        text_view_name.visibility = View.INVISIBLE
        cardView.visibility = View.INVISIBLE
        cardViewDetails.visibility = View.INVISIBLE
        progressBar.visibility = View.INVISIBLE

        view.findViewById<CardView>(R.id.cardView).setOnClickListener {
            if(visible){
                cardViewDetails.visibility = View.INVISIBLE
                visible = !visible
            } else {
                cardViewDetails.visibility = View.VISIBLE
                visible = !visible
            }
        }
    }
}
