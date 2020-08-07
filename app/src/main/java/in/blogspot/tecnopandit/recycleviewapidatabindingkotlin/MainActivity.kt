 package `in`.blogspot.tecnopandit.recycleviewapidatabindingkotlin

import `in`.blogspot.tecnopandit.recycleviewapidatabindingkotlin.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class MainActivity : AppCompatActivity() {
     var users: ArrayList<Users> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        var  activityMainBinding: ActivityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        activityMainBinding.rcv.layoutManager=LinearLayoutManager(this)
        activityMainBinding.rcv.setHasFixedSize(true)
        var myAdapter: MyAdapter= MyAdapter(this,users)
        activityMainBinding.rcv.adapter=myAdapter
        var retrofit: Retrofit=Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build()
        var placeHolder:PlaceHolder=retrofit.create(PlaceHolder::class.java)
        var call: Call<List<UserData>> = placeHolder.getUsers()
        call.enqueue(object : Callback<List<UserData>>{
            override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<UserData>>,
                response: Response<List<UserData>>
            ) {
                if(response.isSuccessful) {
                    val temp : List<UserData>? = response.body()

                    if (temp != null) {
                        for (i in temp) {
                            users.add(Users(i.getLogin(), i.getHtmlUrl(), i.getAvatarUrl()))
                            Log.i("User Data::",i.getLogin()+" "+i.getHtmlUrl())
                        }
                    }
                    myAdapter.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "In if", Toast.LENGTH_LONG).show()

                }
                else{
                    Toast.makeText(applicationContext,"Response unsuccessful",Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}