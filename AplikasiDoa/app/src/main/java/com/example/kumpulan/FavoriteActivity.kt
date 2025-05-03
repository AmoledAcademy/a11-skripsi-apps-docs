package com.example.kumpulan

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class FavoriteActivity : AppCompatActivity() {

    private lateinit var doaAdapter: DoaAdapter
    private lateinit var doaList: ArrayList<Doa>
    private lateinit var rvDoa: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorite)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        rvDoa = findViewById(R.id.rvFavorite)
        doaList = ArrayList()
        doaAdapter = DoaAdapter(doaList)
        rvDoa.adapter = doaAdapter
        doaList.sortBy { it.no ?: Int.MIN_VALUE }
        doaAdapter.setOnItemClickListener(object : DoaAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra("doa", doaList[position])
                startActivity(intent)
            }
        })
    }

}