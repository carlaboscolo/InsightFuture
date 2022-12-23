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
        val view = LayoutInflater.from(parent.context) .inflate(R.layout.view_archive, parent, false)
        return ArchiveViewHolder(view)
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



/*
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.insightfuture.archive.model.Response

class ArchiveAdapter(private val archives: ArrayList<Response>,
                     private val context: Context
)
    : RecyclerView.Adapter<ArchiveAdapter.CustomViewHolder>() {


    class CustomViewHolder(val view: ViewGroup)
        : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_archive, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val archive = archives[position]

        val dataText = holder.view.findViewById<TextView>(R.id.dataText)
        dataText.text = archive.data

        val responseText = holder.view.findViewById<TextView>(R.id.responseText)
        responseText.text = archive.response

        holder.view.setOnClickListener {
            /*val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("name", product.name)
            context.startActivity(intent)*/
            mListener?.selectItem(position)
        }

    }




    override fun getItemCount() = archives.size

    /*
     *
     *       Callback
     *
     * */
    interface AdapterCallback {
        fun selectItem(position: Int)
    }
    private var mListener: AdapterCallback? = null

    fun setOnCallback(mItemClickListener: AdapterCallback) {
        this.mListener = mItemClickListener
    }
}

 */