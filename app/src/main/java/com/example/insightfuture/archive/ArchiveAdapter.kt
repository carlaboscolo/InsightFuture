package com.example.insightfuture

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.insightfuture.roomDatabase.SibillaDatabase
import com.example.roomdatabase.AppDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArchiveAdapter(private val archives : ArrayList<SibillaDatabase>,
                     private val context: Context, private var appDB: AppDatabase)
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

        lateinit var sibilla: SibillaDatabase
        appDB = AppDatabase.getDatabase(this)

        GlobalScope.launch {
            sibilla = appDB.SibillaDao().getAll()

            val dataText = holder.view.findViewById<TextView>(R.id.dataText)
            dataText.text = sibilla.data

            val string = sibilla.question + sibilla.name + sibilla.surname + sibilla.bornPlace + sibilla.response
            val responseText = holder.view.findViewById<TextView>(R.id.responseText)
            responseText.text = string

        }

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