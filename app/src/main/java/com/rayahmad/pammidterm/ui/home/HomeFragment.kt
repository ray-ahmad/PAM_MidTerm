package com.rayahmad.pammidterm.ui.home

import ApiService
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas1pam.UsersAdapter
import com.rayahmad.pammidterm.databinding.FragmentHomeBinding
import com.rayahmad.pammidterm.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        loadData()

        return root
    }

    private fun loadData() {
        apiService.getUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val users = response.body()?.data ?: emptyList()
                    val adapter = UsersAdapter(users)
                    binding.rvUsers.adapter = adapter

//                    binding.searchUsers.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//                        androidx.appcompat.widget.SearchView.OnQueryTextListener {
//                        override fun onQueryTextSubmit(query: String?): Boolean {
//                            return false
//                        }
//
//                        override fun onQueryTextChange(query: String?): Boolean {
//                            startSearch(query, adapter)
//                            return true
//                        }
//                    })

                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

//    private fun startSearch(query: String?, adapter: UsersAdapter) {
//        if (query != null) {
//            val filteredList = adapter.users.filter {
//                it.firstName.contains(query, ignoreCase = true) ||
//                        it.lastName.contains(query, ignoreCase = true) ||
//                        it.email.contains(query, ignoreCase = true)
//            }
//            adapter.updateList(filteredList)
//        } else {
//            loadData()
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
