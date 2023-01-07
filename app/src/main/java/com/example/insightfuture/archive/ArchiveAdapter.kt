package com.example.insightfuture

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.insightfuture.roomDatabase.SibillaDatabase
import com.example.roomdatabase.AppDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ArchiveAdapter(private var list: ArrayList<SibillaDatabase>,
)  : RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder>(){

   // private lateinit var appDB : AppDatabase

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ArchiveViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_archive_item, parent, false)
        return ArchiveViewHolder(view)
    }

    fun filterList(filterlist: ArrayList<SibillaDatabase>) {
        list = filterlist
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArchiveViewHolder, position: Int) {
        val stringQuestion =  list.get(position).question + " " +  list.get(position).name + " " +  list.get(position).surname + " " +  list.get(position).bornPlace
        holder.data.text =  list.get(position).data
        holder.question.text = stringQuestion
        holder.response.text = list.get(position).response

        holder.deleteBtn.setOnClickListener {
         //   list.removeAt(position) funziona, ma non toglie da room database solo recycler

          /*  ----------------- non funziona  ---------------------------
          GlobalScope.launch {
                appDB.sibillaDao().deleteItem(list.get(position).id)
            }
            -------------------------------------------------------------- */

            notifyDataSetChanged()
            Log.d("deletePosition", "ho cancellato l'elemento dalla lista "  +  list.get(position).id)
        }

        holder.updateBtn.setOnClickListener {
            notifyDataSetChanged()
            Log.d("updatePosition", "serve per modificare il responso")
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ArchiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val data : TextView = itemView.findViewById(R.id.dataText)
        val question : TextView = itemView.findViewById(R.id.questionText)
        val response : TextView = itemView.findViewById(R.id.responseText)
        val deleteBtn : Button = itemView.findViewById(R.id.deleteBtn)
        val updateBtn : Button = itemView.findViewById(R.id.updateBtn)
    }



}

