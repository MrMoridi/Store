package com.example.snappfood.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.snappfood.Domain.Category
import com.example.snappfood.R
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur


class CategoryAdapter(private val items: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleTxt.text = currentItem.name

        val radius = 10f
        val rootView =
            (context as Activity).window.decorView.findViewById<ViewGroup>(android.R.id.content)
        val windowBackground = (context as Activity).window.decorView.background

        holder.blurView.setupWith(rootView, RenderScriptBlur(context))
            .setFrameClearDrawable(windowBackground) // Optional
            .setBlurRadius(radius)
        holder.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        holder.blurView.clipToOutline = true

        val drawableResourceId = context.resources.getIdentifier(
            currentItem.imagePath,
            "drawable",
            context.packageName
        )
        Glide.with(context)
            .load(drawableResourceId)
            .into(holder.pic)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.titleCatTxt)
        val pic: ImageView = itemView.findViewById(R.id.imgCat)
        val blurView: BlurView = itemView.findViewById(R.id.blurView)
    }
}