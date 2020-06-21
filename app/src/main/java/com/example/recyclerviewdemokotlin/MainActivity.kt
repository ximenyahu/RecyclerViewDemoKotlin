package com.example.recyclerviewdemokotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    val mArrayList = ArrayList<Model>()
    val mDisplayList = ArrayList<Model>()
    lateinit var myAdapter: MyAdapter
    lateinit var mSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mArrayList.add(Model("Tim Duncan", "This is Tim Duncan", R.drawable.timduncan))
        mArrayList.add(Model("Tony Parker", "This is Tony Parker", R.drawable.tonyparker))
        mArrayList.add(Model("Manu Ginobili", "This is Manu Ginobili", R.drawable.manu_ginobili))
        mArrayList.add(Model("Kawhi Leonard", "This is Kawhi Leonard", R.drawable.kawhi_leonard))
        mArrayList.add(Model("Popovich", "This is Popovich", R.drawable.popovich))
        mArrayList.add(Model("Boris Diaw", "This is Boris Diaw", R.drawable.boris_diaw))
        mArrayList.add(Model("Patty Mills", "This is Patty Mills", R.drawable.patty_mills))
        mArrayList.add(Model("Tiago Splitter", "This is Tiago Splitter", R.drawable.tiago_splitter))
        mArrayList.add(
            Model(
                "Stephen Jackson",
                "This is Stephen Jackson",
                R.drawable.stephen_jackson
            )
        )
        mArrayList.add(Model("Matty Bonner", "This is Matty Bonner", R.drawable.matt_bonner))
        mArrayList.add(Model("Nando De Colo", "This is Nando De Colo", R.drawable.nando_de_colo))
        mArrayList.add(Model("Cory Joseph", "This is Cory Joseph", R.drawable.cory_joseph))
        mDisplayList.addAll(mArrayList)
        myAdapter = MyAdapter(this, mDisplayList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = myAdapter
        mSharedPreferences = getSharedPreferences("my_Pref", Context.MODE_PRIVATE)
        val sortBy = mSharedPreferences.getString("sortBy", "Ascending")
        if (sortBy.equals("Ascending")) {
            sortAscending(myAdapter)
        } else if (sortBy.equals("Descending")) {
            sortDescending(myAdapter)
        }
    }

    private fun sortDescending(myAdapter: MyAdapter) {
        mDisplayList.sortWith(compareBy { it.title })
        mDisplayList.reverse()
        myAdapter.notifyDataSetChanged()
    }

    private fun sortAscending(myAdapter: MyAdapter) {
        mDisplayList.sortWith(compareBy { it.title })
        myAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu!!.findItem(R.id.search)
        if (item != null) {
            val searchView: SearchView = item.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    mDisplayList.clear()
                    if (newText!!.isNotEmpty()) {
                        val search = newText.toLowerCase(Locale.getDefault())
                        mArrayList.forEach {
                            if (it.title.toLowerCase(Locale.getDefault()).contains(search)) {
                                mDisplayList.add(it)
                            }
                        }
                    } else {
                        mDisplayList.addAll(mArrayList)
                    }
                    recycler_view.adapter!!.notifyDataSetChanged()
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort) {
            sortDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortDialog() {
        val options = arrayOf("Ascending", "Descending")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sort by")
        builder.setIcon(R.drawable.ic_action_sort)
        builder.setItems(options) { dialog, which ->
            if (which == 0) {
                val edit: SharedPreferences.Editor = mSharedPreferences.edit()
                edit.putString("sortBy", "Ascending")
                edit.apply()
                sortAscending(myAdapter)
                Toast.makeText(this@MainActivity, "Ascending order", Toast.LENGTH_SHORT).show()
            }

            if (which == 1) {
                val edit: SharedPreferences.Editor = mSharedPreferences.edit()
                edit.putString("sortBy", "Descending")
                edit.apply()
                sortDescending(myAdapter)
                Toast.makeText(this@MainActivity, "Descending order", Toast.LENGTH_SHORT).show()
            }
        }
        builder.create().show()
    }
}