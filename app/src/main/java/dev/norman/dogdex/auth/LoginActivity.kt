package dev.norman.dogdex.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import dev.norman.dogdex.MainActivity
import dev.norman.dogdex.R
import dev.norman.dogdex.api.ApiResponseStatus
import dev.norman.dogdex.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginFragment.LoginFragmentActions,
    SignUpFragment.SignUpFragmentActions{

    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.status.observe(this) {
                status ->

            when(status) {
                is ApiResponseStatus.Error -> {
                    binding.loadingWheel.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }
                is ApiResponseStatus.Loading -> binding.loadingWheel.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> binding.loadingWheel.visibility = View.GONE
            }
        }

        viewModel.user.observe(this) {
                user ->
            if (user != null) {
                startMainActivity()
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showErrorDialog(messageId: Int) {
        AlertDialog.Builder(this)
            .setTitle(R.string.there_was_an_error)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ -> /** Dismiss dialog **/ }
            .create()
            .show()
    }

    override fun onRegisterButtonClick() {
        findNavController(R.id.nav_host_fragment)
            .navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
    }

    override fun onSignUpFieldsValidated(
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        viewModel.signUp(email, password, passwordConfirmation)
    }
}