package com.example.weather_forecast.model

/**
 * wind.speed Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
 * wind.deg Wind direction, degrees (meteorological)
 */
data class Wind(
    val deg: Int,
    val speed: Double
)