package com.example.agenda

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailTugasFragment : Fragment(R.layout.fragment_detail_tugas) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val judul = arguments?.getString(ARG_JUDUL).orEmpty()
        val batas = arguments?.getString(ARG_BATAS).orEmpty()

        val tvJudulTugas = view.findViewById<TextView>(R.id.tvJudulTugas)
        val tvBatasWaktu = view.findViewById<TextView>(R.id.tvBatasWaktu)
        val btnKembali = view.findViewById<Button>(R.id.btnKembali)

        tvJudulTugas.text = judul
        tvBatasWaktu.text = batas

        btnKembali.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        private const val ARG_JUDUL = "judul"
        private const val ARG_BATAS = "batas"

        fun newInstance(judul: String, batas: String): DetailTugasFragment {
            return DetailTugasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_JUDUL, judul)
                    putString(ARG_BATAS, batas)
                }
            }
        }
    }
}
