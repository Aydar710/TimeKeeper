package ru.timekeeper.ui.vk

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.stetho.Stetho
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_login.*
import ru.timekeeper.R
import ru.timekeeper.SHARED_PREF_FILENAME
import ru.timekeeper.SHARED_PREF_TOKEN_KEY

class LoginActivity : AppCompatActivity() {

    private lateinit var sPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Stetho.initializeWithDefaults(this)

        img_login_vk.setOnClickListener {
            VK.login(this, arrayListOf(VKScope.WALL, VKScope.GROUPS))
        }

        startContainerActivity()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization
                saveTokenToPreferences(token.accessToken)
                Log.i("Token", token.accessToken)
                startContainerActivity()
            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun startContainerActivity() {
        val intent = Intent(this, ContainerActivity::class.java)
        startActivity(intent)
    }

    fun saveTokenToPreferences(token: String) {
        sPref = getSharedPreferences(SHARED_PREF_FILENAME, MODE_PRIVATE)
        sPref.edit().run {
            putString(SHARED_PREF_TOKEN_KEY, token)
            apply()
        }

    }

    fun getTokenFromPreferences(): String? {
        sPref = getSharedPreferences(SHARED_PREF_FILENAME, MODE_PRIVATE)
        return sPref.getString(SHARED_PREF_TOKEN_KEY, "")
    }
}
