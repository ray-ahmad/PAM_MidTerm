package com.rayahmad.pammidterm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.rayahmad.pammidterm.databinding.ActivityLoginBinding
import com.rayahmad.pammidterm.repository.UserRepository
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.loginButton.setOnClickListener{
//            val intent = Intent(this@LoginActivity, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//        binding.loginButton.setOnClickListener {
//            val user = binding.username.text.toString()
//            val password = binding.password.text.toString()
//            if(viewModel.login(user, password)) {
//                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
//            }
//        }
        userRepository = UserRepository(applicationContext)
        binding.registerButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            lifecycleScope.launch {
                val isSuccess = userRepository.loginUser(username, password)
                if (isSuccess) {
                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Login Gagal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}