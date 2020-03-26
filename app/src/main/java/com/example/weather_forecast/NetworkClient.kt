import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkClient {
    const val BASE_URL = "http://api.openweathermap.org"
    var retrofit: Retrofit? = null//This is the only mandatory call on Builder object.
    // Convertor library used to convert response into POJO
//Defining the Retrofit using Builder//If condition to ensure we don't create multiple retrofit instances in a single application
    /*
    This public static method will return Retrofit client
    anywhere in the appplication
    */
    val retrofitClient: Retrofit?
        get() { //If condition to ensure we don't create multiple retrofit instances in a single application
            if (retrofit == null) { //Defining the Retrofit using Builder
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL) //This is the only mandatory call on Builder object.
                    .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                    .build()
            }
            return retrofit
        }
}