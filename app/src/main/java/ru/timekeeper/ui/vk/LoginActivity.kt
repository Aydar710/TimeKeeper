package ru.timekeeper.ui.vk

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_login.*
import ru.timekeeper.App
import ru.timekeeper.R
import ru.timekeeper.data.repository.SharedPrefWrapper
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Suppress("LateinitUsage")
    @Inject
    lateinit var sPref: SharedPrefWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        App.component.inject(this)

        img_login_vk.setOnClickListener {
            VK.login(this, arrayListOf(VKScope.WALL, VKScope.GROUPS))
        }

        if (sPref.isTokenValid())
            startContainerActivity()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization
                sPref.saveToken(token.accessToken)
                sPref.saveUserId(token.userId)
                sPref.setTokenValidation(true)
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
