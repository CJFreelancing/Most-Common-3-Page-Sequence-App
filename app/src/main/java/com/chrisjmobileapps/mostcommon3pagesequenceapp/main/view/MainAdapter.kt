package com.chrisjmobileapps.mostcommon3pagesequenceapp.main.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chrisjmobileapps.mostcommon3pagesequenceapp.R
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.PageSequenceFrequency

class MainAdapter() : RecyclerView.Adapter<MainAdapter.MainAdapterHolder>() {
    private var pageSequenceFrequencyList:  List<PageSequenceFrequency>? = null

    override fun getItemCount(): Int {
        if(pageSequenceFrequencyList == null) {
            return 0
        } else {
            return pageSequenceFrequencyList!!.size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pagefrequency_item,
                parent, false)

        return MainAdapterHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainAdapterHolder, position: Int) {
        if(pageSequenceFrequencyList != null) {
            val pageSequenceFrequency = pageSequenceFrequencyList!![position]
            val pageNameList = pageSequenceFrequency.pageNameList
            holder.pagename1.setText(pageNameList[0])
            holder.pagename2.setText(pageNameList[1])
            holder.pagename3.setText(pageNameList[2])
            holder.pagecount.setText(pageSequenceFrequency.count.toString())
        }
    }

    fun setPageSequenceFrequencyList(pageSequenceFrequencyList:  List<PageSequenceFrequency>?) {
        this.pageSequenceFrequencyList = pageSequenceFrequencyList
        notifyDataSetChanged()
    }

    inner class MainAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var pagename1: TextView = itemView.findViewById(R.id.pagename_1_fromcall)
        internal var pagename2: TextView = itemView.findViewById(R.id.pagename_2_fromcall)
        internal var pagename3: TextView = itemView.findViewById(R.id.pagename_3_fromcall)
        internal var pagecount: TextView = itemView.findViewById(R.id.pagecount_fromcall)

    }
}