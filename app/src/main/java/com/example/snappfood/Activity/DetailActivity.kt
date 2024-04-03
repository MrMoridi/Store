package com.example.snappfood.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.snappfood.Activity.BaseActivity
import com.example.snappfood.Domain.Foods
import com.example.snappfood.Helper.ManagmentCart
import com.example.snappfood.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var food: Foods
    private var num = 1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart = ManagmentCart(this)
        getBundleExtra()
        setVariable()
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
        Glide.with(this)
            .load(food.imagePath)
            .into(binding.pic)

        binding.priceTxt.text = "$${food.Price}"
        binding.titleTxt.text = food.Title
        binding.descriptionTxt.text = food.description
        binding.ratingTxt.text = "${food.star} Rating"
        binding.ratingBar.rating = food.star.toFloat()
        binding.totalTxt.text = "${num * food.Price} $"

        binding.plusBtn.setOnClickListener {
            num++
            binding.numTxt.text = "$num "
            binding.totalTxt.text = "${num * food.Price} $"
        }

        binding.minusBtn.setOnClickListener {
            if (num > 1) {
                num--
                binding.numTxt.text = "$num "
                binding.totalTxt.text = "${num * food.Price} $"
            }
        }

        binding.addBtn.setOnClickListener {
            food.numberInCart = num
            managmentCart.insertFood(food)
            Toast.makeText(this, "Added to your Cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getBundleExtra() {
        if (intent != null && intent.hasExtra("object")) {
            food = intent.getSerializableExtra("object") as Foods
        } else {
            // handle null case
        }
    }
}