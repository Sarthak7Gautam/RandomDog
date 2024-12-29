package gautam.sarthak.composechatapp.data

import retrofit2.http.GET

interface DogsInterface {

    @GET("/randomdog")
    suspend fun getRandomDog(): DogsDetail

    companion object{
        const val BASE_URL = "http://000.000.0.00:8080"
    }
}
