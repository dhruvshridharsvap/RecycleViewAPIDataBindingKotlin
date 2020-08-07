package `in`.blogspot.tecnopandit.recycleviewapidatabindingkotlin

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class Users(private var name: String, private var desc: String, private var avatar: String) {
    fun getName (): String{
        return name
    }

    fun getDesc():String{
        return desc
    }

    fun getAvatar(): String{
        return avatar
    }
    companion object{
        @JvmStatic
        @BindingAdapter("android:imageUrl")
        fun loadUrl(view: View,imageurl: String){
            var imageView : ImageView= view as ImageView
            Glide.with(view.context).asDrawable().load(imageurl).into(imageView)

        }
    }


}