package com.rifki18090020.toko.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import com.rifki18090020.toko.DetailActivity
import com.adhe18090088.toko.R
import com.rifki18090020.toko.models.Barang

class BarangAdapter(private var data: List<Barang>, private var context: Context):RecyclerView.Adapter<BarangAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )
    }
    override fun onBindViewHolder(holder: MyHolder, position: Int)=holder.bind(data[position],context)
    override fun getItemCount()=data.size
    class MyHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(barang: Barang, context: Context){
            itemView.name.text = barang.name
            itemView.location.text = barang.location
            itemView.description.text = barang.description
            itemView.setOnClickListener {
                context.startActivity(Intent(context,
                DetailActivity::class.java).apply {
                    putExtra("barang",barang)
                })
            }
        }
    }
}