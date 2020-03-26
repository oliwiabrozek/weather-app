package com.example.weather_forecast

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import com.example.weather_forecast.model.Forecast
import com.example.weather_forecast.retrofit.Endpoints
import com.example.weather_forecast.retrofit.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.text_view_description
import kotlinx.android.synthetic.main.fragment_first.weather_icon
import kotlinx.android.synthetic.main.fragment_second.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private val API: String = "5eacdd72068f61f0cf47783982d176c4"
    private val UNITS: String = "metric"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun currentDate() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        text_view_date.text = currentDate
    }

    private fun refreshCardView(forecast: Forecast) {
        currentDate()
        text_view_description.text = forecast.weather.get(0).description
        text_view_temperature.text = "${forecast.main.temp.toInt()} °C"
        text_view_humidity.text = "HUMIDITY ${forecast.main.humidity}%"
        text_view_wind.text = "WIND SPEED ${forecast.wind.speed} km/h"
        text_view_clouds.text = "CLOUD COVER ${forecast.clouds.all}%"
        text_view_pressure.text = "PRESSURE ${forecast.main.pressure} hPa"
        text_view_sunrise.text ="SUNRISE ${convertLongToTime(forecast.sys.sunrise.toLong())}"
        text_view_sunset.text = "SUNSET ${convertLongToTime(forecast.sys.sunset.toLong())}"
        weather_icon.setImageResource(getImageRecourceId(forecast.weather.get(0).icon))
    }

    private fun convertLongToTime(time: Long?): String {
        if(time !== null) {
            val date = Date(time*1000)
            val format = SimpleDateFormat("HH:mm")
            return format.format(date)
        }
        return ""
    }

//        fun refreshTable(){
//        text_view_description2.text = this.forecast?.weather?.get(0)?.description
//        text_view_humidity2.text = "HUMIDITY " + this.forecast?.main?.humidity.toString()+" %"
//        text_view_temperature2.text = this.forecast?.main?.temp?.toInt().toString() + " °C"
//        text_view_clouds2.text = this.forecast?.clouds?.all.toString() + " %"
//        text_view_pressure2.text = this.forecast?.main?.pressure.toString() + " hPa"
//        text_view_sunrise2.text = this.convertLongToTime(this.forecast?.sys?.sunrise?.toLong())
//        text_view_sunset2.text = this.convertLongToTime(this.forecast?.sys?.sunset?.toLong())
//
//        weather_icon2.setImageResource(getImageRecourceId(this.forecast?.weather?.get(0)?.icon))
//    }


    fun showForecast(view: View) {
        //toast("show forecast")
        progressBar.visibility = View.VISIBLE
        val city = editText.text.toString()
        text_view_name.text = city.toUpperCase()
        getCurrentData(city)
        cardView.visibility = View.VISIBLE
        text_view_name.visibility = View.VISIBLE
    }

    private fun getCurrentData(city: String) {
        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getForecastByCity(city, UNITS, API)

        call.enqueue(object : Callback<Forecast>{
            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                if (response.isSuccessful){
                    progressBar.visibility = View.GONE
                    refreshCardView(response.body()!!)
                }
            }
            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getImageRecourceId(iconName: String?): Int = when (iconName) {
        "01d" -> R.drawable.i01d
        "02d" -> R.drawable.i02d
        "03d" -> R.drawable.i03d
        "04d" -> R.drawable.i04d
        "09d" -> R.drawable.i09d
        "10d" -> R.drawable.i10d
        "11d" -> R.drawable.i11d
        "13d" -> R.drawable.i13d
        "50d" -> R.drawable.i50d
        "01n" -> R.drawable.i01n
        "02n" -> R.drawable.i02n
        "03n" -> R.drawable.i03n
        "04n" -> R.drawable.i04n
        "09n" -> R.drawable.i09n
        "10n" -> R.drawable.i10n
        "11n" -> R.drawable.i11n
        "13n" -> R.drawable.i13n
        "50n" -> R.drawable.i50n
        else -> throw RuntimeException("no image for the icon specified")
    }
}
