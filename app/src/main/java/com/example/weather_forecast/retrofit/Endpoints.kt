package com.example.weather_forecast.retrofit

import com.example.weather_forecast.model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {
  /*
    Get request to fetch city weather.Takes in two parameter-city name and API key.
    */
  @GET("/data/2.5/weather")
  open fun getForecastByCity(@Query("q") city: String?, @Query("units") units: String?, @Query("appid") apiKey: String?): Call<Forecast>
}