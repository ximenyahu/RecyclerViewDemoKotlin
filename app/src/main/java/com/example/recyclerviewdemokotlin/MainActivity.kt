package com.example.recyclerviewdemokotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val arrayList = ArrayList<Model>()
        arrayList.add(Model("Tim Duncan", "This is Tim Duncan", R.drawable.timduncan))
        arrayList.add(Model("Tony Parker", "This is Tony Parker", R.drawable.tonyparker))
        arrayList.add(Model("Manu Ginobili", "This is Manu Ginobili", R.drawable.manu_ginobili))
        arrayList.add(Model("Kawhi Leonard", "This is Kawhi Leonard", R.drawable.kawhi_leonard))
        arrayList.add(Model("Popovich", "This is Popovich", R.drawable.popovich))
        arrayList.add(Model("Boris Diaw", "This is Boris Diaw", R.drawable.boris_diaw))
        arrayList.add(Model("Patty Mills", "This is Patty Mills", R.drawable.patty_mills))
        arrayList.add(Model("Tiago Splitter", "This is Tiago Splitter", R.drawable.tiago_splitter))
        arrayList.add(
            Model(
                "Stephen Jackson",
                "This is Stephen Jackson",
                R.drawable.stephen_jackson
            )
        )
        arrayList.add(Model("Matty Bonner", "This is Matty Bonner", R.drawable.matt_bonner))
        arrayList.add(Model("Nando De Colo", "This is Nando De Colo", R.drawable.nando_de_colo))
        arrayList.add(Model("Cory Joseph", "This is Cory Joseph", R.drawable.cory_joseph))

        val adapter = MyAdapter(this, arrayList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}