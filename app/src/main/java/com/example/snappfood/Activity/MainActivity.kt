package com.example.snappfood.Activity


import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.snappfood.Adapter.BestFoodAdapter
import com.example.snappfood.Adapter.CategoryAdapter
import com.example.snappfood.Domain.Category
import com.example.snappfood.Domain.Foods
import com.example.snappfood.Domain.Location
import com.example.snappfood.Domain.Price
import com.example.snappfood.Domain.Time
import com.example.snappfood.R
import com.example.snappfood.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLocation()
        initTime()
        initPrice()
        initBestFood()
        initCategory()

    }

    private fun initCategory() {
        val databaseReference = database.getReference("Category")
        binding.progressBarCategory.visibility = View.VISIBLE

        val list = ArrayList<Category>()
        val query = databaseReference.orderByChild("BestFood").equalTo(true)
        query.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                for (dataSnapshot in snapshot.children) {
                    list.add(dataSnapshot.getValue(Category::class.java)!!) // Use !! for non-null assertion
                }
                if (list.isNotEmpty()) {
                    binding.categoryView.layoutManager = LinearLayoutManager(
                        this@MainActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    binding.categoryView.adapter = CategoryAdapter(list)
                }
            }
            binding.progressBarCategory.visibility = View.GONE
        }.addOnFailureListener { error ->
            // Handle database errors here
        }
    }
    private fun initBestFood() {
        val databaseReference = database.getReference("Foods")
        binding.progressBarBestFood.visibility = View.VISIBLE

        val list = ArrayList<Foods>()
        val query = databaseReference.orderByChild("BestFood").equalTo(true)

        query.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                for (dataSnapshot in snapshot.children) {
                    list.add(dataSnapshot.getValue(Foods::class.java)!!) // Use !! for non-null assertion
                }
                if (list.isNotEmpty()) {
                    binding.bestFoodView.layoutManager = LinearLayoutManager(
                        this@MainActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    binding.bestFoodView.adapter = BestFoodAdapter(list)
                }
            }
            binding.progressBarBestFood.visibility = View.GONE
        }.addOnFailureListener { error ->
            // Handle database errors here
        }
    }
    private fun initLocation() {
        val myRef: DatabaseReference = database.getReference("Location")
        val list: ArrayList<Location> = ArrayList()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // عملیات مورد نیاز برای تغییر داده‌ها

                if (snapshot.exists()) {
                    val locationList: MutableList<Location> = mutableListOf()
                    for (issue in snapshot.children) {
                        val location = issue.getValue(Location::class.java)
                        location?.let {
                            locationList.add(it)
                        }
                    }
                    val adapter =
                        ArrayAdapter<Location>(this@MainActivity, R.layout.sp_item, locationList)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.locationSp.adapter = adapter
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // عملیات مورد نیاز برای لغو عملیات
            }
        })
    }
    private fun initTime() {
        val myRef: DatabaseReference = database.getReference("Time")
        val timeList: MutableList<Time> = mutableListOf()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // عملیات مورد نیاز برای تغییر داده‌ها

                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        val time = issue.getValue(Time::class.java)
                        time?.let { timeList.add(it) }
                    }

                    val adapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_list_item_1,
                        timeList
                    )
                    binding.timeSp.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // عملیات مورد نیاز برای لغو عملیات
            }
        })
    }
    private fun initPrice() {
        val myRef: DatabaseReference = database.getReference("Price")
        val priceList: MutableList<Price> = mutableListOf()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // عملیات مورد نیاز برای تغییر داده‌ها

                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        val price = issue.getValue(Price::class.java)
                        price?.let { priceList.add(it) }
                    }

                    val adapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_list_item_1,
                        priceList
                    )
                    binding.priceSp.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // عملیات مورد نیاز برای لغو عملیات
            }
        })
    }
}