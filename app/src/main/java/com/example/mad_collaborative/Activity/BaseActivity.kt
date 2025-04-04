package com.example.mad_collaborative.Activity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.mad_collaborative.Adapter.CategoryAdapter
import com.example.mad_collaborative.Adapter.PopularAdapter
import com.example.mad_collaborative.Adapter.RecommendedAdapter
import com.example.mad_collaborative.Adapter.SliderAdapter
import com.example.mad_collaborative.Domain.Category
import com.example.mad_collaborative.Domain.ItemDomain
import com.example.mad_collaborative.Domain.Location
import com.example.mad_collaborative.Domain.SliderItems
import com.example.mad_collaborative.R
import com.example.mad_collaborative.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class BaseActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLocation()
        initBanner()
        initCategory()
        initRecommended()
        initPopular()
    }

    private fun initPopular() {
        val myRef: DatabaseReference = database.getReference("Popular")
        binding.progressBarPopular.visibility = View.VISIBLE

        val list = ArrayList<ItemDomain>()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(ItemDomain::class.java)!!)
                    }
                    if (list.isNotEmpty()) {
                        binding.recyclerViewPopular.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                        val adapter = PopularAdapter(list)
                        binding.recyclerViewPopular.adapter = adapter
                    }
                    binding.progressBarPopular.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun initRecommended() {
        val myRef: DatabaseReference = database.getReference("Item")
        binding.progressBarRecommended.visibility = View.VISIBLE

        val list = ArrayList<ItemDomain>()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(ItemDomain::class.java)!!)
                    }
                    if (list.isNotEmpty()) {
                        binding.recyclerViewRecommended.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                        val adapter = RecommendedAdapter(list)
                        binding.recyclerViewRecommended.adapter = adapter
                    }
                    binding.progressBarRecommended.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun initCategory() {
        val myRef: DatabaseReference = database.getReference("Category")
        binding.progressBarCategory.visibility = View.VISIBLE
        val list = ArrayList<Category>()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Category::class.java)!!)
                    }
                    if (list.isNotEmpty()) {
                        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                        val adapter = CategoryAdapter(list)
                        binding.recyclerViewCategory.adapter = adapter
                    }
                    binding.progressBarCategory.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun initLocation() {
        val myRef: DatabaseReference = database.getReference("Location")
        val list = ArrayList<Location>()
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Location::class.java)!!)
                    }
                    val adapter = ArrayAdapter(this@MainActivity, R.layout.sp_item, list)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.locationSp.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun banners(items: ArrayList<SliderItems>) {
        binding.viewPagerSlider.adapter = SliderAdapter(items, binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.offscreenPageLimit = 3
        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
    }

    private fun initBanner() {
        val myRef: DatabaseReference = database.getReference("Banner")
        binding.progressBarBanner.visibility = View.VISIBLE
        val items = ArrayList<SliderItems>()
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        items.add(issue.getValue(SliderItems::class.java)!!)
                    }
                    banners(items)
                    binding.progressBarBanner.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}