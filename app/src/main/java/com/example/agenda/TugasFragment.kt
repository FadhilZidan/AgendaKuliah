package com.example.agenda

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class TugasFragment : Fragment(R.layout.fragment_tugas) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnTugas1 = view.findViewById<Button>(R.id.btnTugas1)
        val btnTugas2 = view.findViewById<Button>(R.id.btnTugas2)
        val btnTugas3 = view.findViewById<Button>(R.id.btnTugas3)

        btnTugas1.setOnClickListener {
            openDetail("Laporan Fragment", "Batas: Jumat")
        }

        btnTugas2.setOnClickListener {
            openDetail("Latihan BottomNav", "Batas: Sabtu")
        }

        btnTugas3.setOnClickListener {
            openDetail("Kuis Retrofit", "Batas: Minggu")
        }
    }

    private fun openDetail(judul: String, batas: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DetailTugasFragment.newInstance(judul, batas))
            .addToBackStack("detail")
            .commit()
    }
}
