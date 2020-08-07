package `in`.blogspot.tecnopandit.recycleviewapidatabindingkotlin

import retrofit2.http.GET
import retrofit2.Call

public interface PlaceHolder {
    @GET("users")
    fun getUsers() : Call<List<UserData>>
}