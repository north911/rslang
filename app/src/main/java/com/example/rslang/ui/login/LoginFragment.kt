package com.example.rslang.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.load.engine.GlideException
import com.example.rslang.MainActivity
import com.example.rslang.R
import com.example.rslang.dao.AppDatabase
import com.example.rslang.dao.AppFactory
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private lateinit var db: AppDatabase

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppFactory.createDatabaseObject(context)
        val appContext = context?.applicationContext
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val loginButton = login
        val loadingProgressBar = loading
        loginViewModel.searchForTokenInDB(db)?.let {
            goToNextActivity(appContext, it.token)
        }
        setLoginListener(loginButton, loadingProgressBar, appContext)
    }

    private fun setLoginListener(
        loginButton: Button,
        loadingProgressBar: ProgressBar,
        appContext: Context?
    ) {
        loginButton.setOnClickListener {
            viewModelScope.launch {
                loadingProgressBar.visibility = VISIBLE
                val response = loginViewModel.sendLoginRequest(
                    username.text.toString(),
                    password.text.toString()
                )
                loginButton.isEnabled = false
                val loginResponse = response.await().body()
                loginResponse?.let { loginViewModel.addUserToDb(db, loginResponse) }
                goToNextActivity(appContext, loginResponse?.token)
            }
        }
    }

    private fun goToNextActivity(appContext: Context?, accessToken: String?) {
        val intent = Intent(appContext, MainActivity::class.java)
        intent.putExtra("token", accessToken)
        startActivity(intent)
    }
}