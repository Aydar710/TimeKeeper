package ru.timekeeper.common.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_login.*
import ru.timekeeper.common.App
import ru.timekeeper.common.SharedPrefWrapper
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    @Suppress("LateinitUsage")
    @Inject
    lateinit var sharedPrefWrapper: SharedPrefWrapper

    private val facebookCallbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ru.timekeeper.R.layout.activity_login)


        App.component.inject(this)

        img_login_vk.setOnClickListener {
            VK.login(this, arrayListOf(VKScope.WALL, VKScope.GROUPS))
        }
        btn_login_facebook.setReadPermissions("email")
        btn_login_facebook.setReadPermissions("publish_pages")
        btn_login_facebook.setReadPermissions("pages_show_list")

        btn_login_facebook.registerCallback(facebookCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                sharedPrefWrapper.saveFacebookToken(loginResult.accessToken.token)
                startContainerActivity()
            }

            override fun onCancel() {
            }

            override fun onError(exception: FacebookException) {
            }
        })
        //startContainerActivity()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization
                sharedPrefWrapper.saveVkToken(token.accessToken)
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
