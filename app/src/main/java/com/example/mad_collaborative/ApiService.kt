import android.telecom.Call
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("your_endpoint")
    fun getData(@Query("param") param: String): Call<MyDataModel>
}