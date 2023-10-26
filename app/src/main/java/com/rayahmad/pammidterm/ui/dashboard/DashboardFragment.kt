package com.rayahmad.pammidterm.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rayahmad.pammidterm.databinding.FragmentDashboardBinding
import com.rayahmad.pammidterm.repository.UserRepository
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
//        val logoutButton = findViewById<Button>(R.id.logoutButton)
        binding.logoutButton.setOnClickListener {
            lifecycleScope.launch {
                Toast.makeText(requireContext(), "Anda telah logout", Toast.LENGTH_SHORT).show()
                userRepository.logout()
                // Anda bisa menambahkan logika untuk berpindah ke halaman login atau halaman lain setelah logout
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}