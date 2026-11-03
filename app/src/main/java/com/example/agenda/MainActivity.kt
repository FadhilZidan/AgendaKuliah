package com.example.agenda

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            bottomNavigationView.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_jadwal -> {
                    switchTab(JadwalFragment())
                    true
                }
                R.id.menu_tugas -> {
                    switchTab(TugasFragment())
                    true
                }
                R.id.menu_profil -> {
                    switchTab(ProfilFragment())
                    true
                }
                else -> false
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            updateBottomNavSelection()
        }

        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.menu_jadwal
        }
    }

    private fun switchTab(fragment: Fragment) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (currentFragment != null && currentFragment::class.java == fragment::class.java && supportFragmentManager.backStackEntryCount == 0) {
            return
        }
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun updateBottomNavSelection() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        when (currentFragment) {
            is JadwalFragment -> bottomNavigationView.menu.findItem(R.id.menu_jadwal).isChecked = true
            is TugasFragment, is DetailTugasFragment -> bottomNavigationView.menu.findItem(R.id.menu_tugas).isChecked = true
            is ProfilFragment -> bottomNavigationView.menu.findItem(R.id.menu_profil).isChecked = true
        }
    }
}
