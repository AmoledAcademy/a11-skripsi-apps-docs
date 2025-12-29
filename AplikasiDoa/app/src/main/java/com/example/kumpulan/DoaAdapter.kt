package com.example.kumpulan

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoaAdapter(private var doaList: ArrayList<Doa>) :
    RecyclerView.Adapter<DoaAdapter.DoaViewHolder>() {

    class DoaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNomor: TextView = itemView.findViewById(R.id.tvNomor)
        val tvJudulDoa: TextView = itemView.findViewById(R.id.tvJudulDoa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_doa, parent, false)
        return DoaViewHolder(itemView)
    }

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: DoaViewHolder, position: Int) {
        val currentItem = doaList[position]
        holder.tvNomor.text = currentItem.no.toString()
        holder.tvJudulDoa.text = currentItem.judulDoa

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = if (context is MainActivity && context.isJadwalSelected()) {
                Intent(context, DetailJadwalActivity::class.java)
            } else {
                Intent(context, DetailActivity::class.java)
            }
            intent.putExtra("doa", doaList[position])
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return doaList.size
    }

    fun updateDoaList(newDoaList: ArrayList<Doa>) {
        this.doaList = newDoaList
        notifyDataSetChanged()
    }


}