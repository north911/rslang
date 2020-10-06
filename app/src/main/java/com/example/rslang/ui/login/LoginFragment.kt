package com.example.rslang.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.rslang.MainActivity
import com.example.rslang.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

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
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val loginButton = login
        val loadingProgressBar = loading
        loginButton.setOnClickListener {
            viewModelScope.launch {
                loadingProgressBar.visibility = VISIBLE
                val response = loginViewModel.sendLoginRequest(
                    username.text.toString(),
                    password.text.toString()
                )
                val appContext = context?.applicationContext
                loginButton.isEnabled=false
                var loginResponse = response.await().body()
                val intent = Intent(appContext, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun updateUiWithUser() {
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, "asd", Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}