package com.example.insightfuture

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.insightfuture.roomDatabase.SibillaDatabase


class ArchiveAdapter : RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder>(){
    private var list = mutableListOf<SibillaDatabase>()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ArchiveViewHolder{
        val view = LayoutInflater.from(parent.context) .inflate(R.layout.view_archive_item, parent, false)
        return ArchiveViewHolder(view)
    }

    fun filterList(filterlist: ArrayList<SibillaDatabase>) {
        list = filterlist
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArchiveViewHolder, position: Int) {
        val archive = list[position]
        holder.data.text = archive.data
        val stringQuestion = archive.question + " " + archive.name + " " + archive.surname + " " + archive.bornPlace
        holder.question.text = stringQuestion
        holder.response.text = archive.response
    }

    override fun getItemCount() = list.size



    fun setData(data : List<SibillaDatabase>){
        list.apply {
            clear()
            addAll(data)
        }
    }

    class ArchiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val data : TextView = itemView.findViewById(R.id.dataText)
        val question : TextView = itemView.findViewById(R.id.questionText)
        val response : TextView = itemView.findViewById(R.id.responseText)
    }
}

