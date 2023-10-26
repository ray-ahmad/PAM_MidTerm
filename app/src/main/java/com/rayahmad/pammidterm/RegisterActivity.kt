package com.rayahmad.pammidterm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rayahmad.pammidterm.databinding.ActivityRegisterBinding
import com.rayahmad.pammidterm.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterActivity : ComponentActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        userRepository = UserRepository(applicationContext)

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setOnClickListener{
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.registerButton.setOnClickListener{
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.registerButton.setOnClickListener {
            val username = binding.username.text.toString()
            val githubUsername = binding.githubUsername.text.toString()
            val nim = binding.nim.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            lifecycleScope.launch {
                if (username.isEmpty() || githubUsername.isEmpty() || nim.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Registrasi Gagal, Silakan Isi Semua Isian",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    userRepository.registerUser(username, password, githubUsername, nim, email)
                    Toast.makeText(
                        this@RegisterActivity,
                        "Pendaftaran Berhasil",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Anda bisa menambahkan logika untuk berpindah ke halaman login atau halaman lain setelah pendaftaran berhasil
                }
            }
        }
//        binding.registerButton.setOnClickListener {
//            val user = binding.username.text.toString()
//            val githubUsername = binding.githubUsername.text.toString()
//            val nim = binding.nim.text.toString()
//            val email = binding.email.text.toString()
//            val password = binding.password.text.toString()
//
//            if(user.isEmpty() || githubUsername.isEmpty() || nim.isEmpty() || email.isEmpty() || password.isEmpty()){
//                Toast.makeText(this, "Registrasi Gagal, Silakan Isi Semua Isian", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                val userPreferencesManager = UserPreferencesManager(this)
//                val viewModelFactory = UserViewModelFactory(userPreferencesManager)
//                val viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
//
//                viewModel.register(userPreferencesManager)
//                Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}
