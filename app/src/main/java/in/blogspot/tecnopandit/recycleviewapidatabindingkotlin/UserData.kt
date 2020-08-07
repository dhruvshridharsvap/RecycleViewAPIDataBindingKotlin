package `in`.blogspot.tecnopandit.recycleviewapidatabindingkotlin

class UserData {
    private lateinit var login:String
    private lateinit var html_url: String
    private lateinit var avatar_url:String

    fun getLogin():String{
        return login
    }

    fun getHtmlUrl():String{
        return html_url
    }

    fun getAvatarUrl():String{
        return avatar_url
    }

}